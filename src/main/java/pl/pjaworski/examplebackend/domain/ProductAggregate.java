// SCAFFOLDED ONCE by scripts/codegen — this file is YOURS.
// scaffold-version: 1
// Plain domain state hydrated only from events carrying this aggregate id.
package pl.pjaworski.examplebackend.domain;

import java.util.UUID;
import pl.pjaworski.examplebackend.domain.events.ProductsAddedToStoreEvent;
import pl.pjaworski.examplebackend.eventstream.StateProjector;

public record ProductAggregate(UUID id) implements StateProjector<ProductAggregate> {

    @Override
    public ProductAggregate apply(ProductAggregate state, ProductsAddedToStoreEvent event) {
        return new ProductAggregate(event.aggregateId());
    }
}
