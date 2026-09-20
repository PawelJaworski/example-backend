// PRESERVED-BY-HAND: batch command explodes into one event per product (option C, docs/questions.md); the single-append template cannot express it
package pl.pjaworski.examplebackend.addproductstostore;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pjaworski.examplebackend.domain.events.ProductsAddedToStoreEvent;
import pl.pjaworski.examplebackend.eventstream.CommandHandler;
import pl.pjaworski.examplebackend.eventstream.EventStream;

@RestController
@Component
@Transactional
@RequiredArgsConstructor
public class AddProductsToStoreHandler implements CommandHandler<AddProductsToStoreCmd> {

    private final EventStream eventStream;

    @PostMapping("add-products-to-store")
    @Override
    public UUID handle(@RequestBody AddProductsToStoreCmd command) {
        // Each product is its own aggregate (product:Id on the event): the batch
        // explodes into one event per element, each with a fresh aggregate id.
        UUID first = null;
        for (var product : command.product()) {
            var aggregateId = UUID.randomUUID();
            if (first == null) {
                first = aggregateId;
            }
            eventStream.append(List.of(new ProductsAddedToStoreEvent(
                    aggregateId,
                    product)));
        }
        return first;
    }
}
