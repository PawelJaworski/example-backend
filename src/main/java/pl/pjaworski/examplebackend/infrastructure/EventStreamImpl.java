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

    private final Collection<PersistingProjector> projectors;

    @Override
    public void append(Collection<DomainEvent> events) {
        events.forEach(event -> {
            repository.save(new DomainEventEntity(event));
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
