package pl.pjaworski.examplebackend.eventstream;

import pl.pjaworski.examplebackend.domain.events.DomainEventType;

public interface DomainEvent {
    Long aggregateId();
    DomainEventType eventType();
}
