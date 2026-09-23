// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 2
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.eventstream;

import pl.pjaworski.examplebackend.domain.events.DomainEventType;

public interface DomainEvent {
    Long aggregateId();
    DomainEventType eventType();
}
