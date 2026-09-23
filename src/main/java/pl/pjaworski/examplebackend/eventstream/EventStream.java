// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 2
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.eventstream;

import java.util.Collection;
import java.util.List;

public interface EventStream {
    void append(Collection<DomainEvent> events);
    List<DomainEvent> findAllById(Long id);
}
