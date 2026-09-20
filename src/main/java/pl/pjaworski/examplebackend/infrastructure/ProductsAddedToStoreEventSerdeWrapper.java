package pl.pjaworski.examplebackend.infrastructure;

import com.fasterxml.jackson.annotation.JsonTypeName;
import pl.pjaworski.examplebackend.domain.events.DomainEventType;
import pl.pjaworski.examplebackend.domain.events.ProductsAddedToStoreEvent;

@JsonTypeName("PRODUCTS_ADDED_TO_STORE")
public record ProductsAddedToStoreEventSerdeWrapper(ProductsAddedToStoreEvent event) implements DomainEventSerdeWrapper {
    @Override
    public DomainEventType getEventType() {
        return DomainEventType.PRODUCTS_ADDED_TO_STORE;
    }
}
