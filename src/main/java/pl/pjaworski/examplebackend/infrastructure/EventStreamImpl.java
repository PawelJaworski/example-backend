// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 2
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.infrastructure;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;
import pl.pjaworski.examplebackend.eventstream.EventStream;
import pl.pjaworski.examplebackend.eventstream.PersistingProjector;

@Component
@RequiredArgsConstructor
public class EventStreamImpl implements EventStream {

    private final DomainEventRepository repository;

    /**
     * Held by reference, never copied. Spring injects an immutable list, but a test
     * ability registers its own projector into a shared mutable collection AFTER this
     * stream is constructed — a defensive copy would silently drop those.
     */
    private final Collection<PersistingProjector> projectors;

    @Override
    public void append(Collection<DomainEvent> events) {
        events.forEach(event -> {
            repository.save(new DomainEventEntity(event));
            // Persisting projections (<aggregate>:Key) cannot be replayed per request,
            // so they are advanced here, synchronously, in the same transaction.
            projectors.forEach(projector -> projector.project(event));
        });
    }

    @Override
    public List<DomainEvent> findAllById(UUID id) {
        return repository.findAllByAggregateId(id).stream()
                .map(DomainEventEntity::toDomainEvent)
                .toList();
    }
}
