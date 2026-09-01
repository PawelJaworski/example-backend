package pl.pjaworski.examplebackend.eventstream;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface EventStream {
    void append(Collection<DomainEvent> events);
    List<DomainEvent> findAllById(UUID id);
}
