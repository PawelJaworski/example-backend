package pl.pjaworski.examplebackend.infrastructure;

import pl.pjaworski.examplebackend.domain.events.ProductsAddedToStoreEvent;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;

public final class DomainEventSerde {

    private DomainEventSerde() {
    }

    public static DomainEventSerdeWrapper serialize(DomainEvent event) {
        return switch (event.eventType()) {
            case PRODUCTS_ADDED_TO_STORE -> new ProductsAddedToStoreEventSerdeWrapper((ProductsAddedToStoreEvent) event);
            default -> throw new IllegalArgumentException("No serde wrapper for " + event.eventType());
        };
    }
}
