package pl.pjaworski.examplebackend.eventstream;

import java.util.Collection;
import java.util.List;

public interface EventStream {
    void append(Collection<DomainEvent> events);
    List<DomainEvent> findAllById(Long id);
}
