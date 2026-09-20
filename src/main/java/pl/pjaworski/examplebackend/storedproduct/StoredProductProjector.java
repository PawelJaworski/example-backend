package pl.pjaworski.examplebackend.storedproduct;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pjaworski.examplebackend.domain.events.ProductsAddedToStoreEvent;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;
import pl.pjaworski.examplebackend.eventstream.PersistingProjector;
import pl.pjaworski.examplebackend.eventstream.StateProjector;

@RestController
@Component
@RequiredArgsConstructor
public class StoredProductProjector
        implements StateProjector<StoredProduct>, PersistingProjector {

    private final StoredProductRepository repository;

    @GetMapping("stored-product")
    public List<StoredProduct> getStoredProduct(@RequestParam Map<String, String> search) {
        return repository.findAllBySearch(search).stream()
                .map(StoredProductEntity::toReadModel)
                .toList();
    }

    @Override
    public void project(DomainEvent event) {
        var state = repository.findById(event.aggregateId())
                .map(StoredProductEntity::toReadModel)
                .orElse(null);
        hydrate(state, List.of(event));
    }

    @Override
    public StoredProduct apply(StoredProduct state, ProductsAddedToStoreEvent event) {
        var projected = new StoredProduct(
                event.aggregateId(),
                event.product().name(),
                event.product().description());
        repository.save(new StoredProductEntity(event.aggregateId(), projected.productKey(), projected.productName(), projected.productDescription()));
        return projected;
    }
}
