package pl.pjaworski.examplebackend.eventstream;

import pl.pjaworski.examplebackend.domain.events.DomainEventType;

import java.util.UUID;

public interface DomainEvent {
    UUID aggregateId();
    DomainEventType eventType();
}
