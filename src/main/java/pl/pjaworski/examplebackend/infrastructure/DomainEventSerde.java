package pl.pjaworski.examplebackend.infrastructure;

import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;

public final class DomainEventSerde {

    private DomainEventSerde() {
    }

    public static DomainEventSerdeWrapper serialize(DomainEvent event) {
        return switch (event.eventType()) {
            case POLICY_ISSUED -> new PolicyIssuedEventSerdeWrapper((PolicyIssuedEvent) event);
            default -> throw new IllegalArgumentException("No serde wrapper for " + event.eventType());
        };
    }
}
