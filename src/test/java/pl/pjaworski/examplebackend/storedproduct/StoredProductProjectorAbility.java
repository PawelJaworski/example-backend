package pl.pjaworski.examplebackend.storedproduct;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import pl.pjaworski.examplebackend.eventstream.EventStreamAbility;

public interface StoredProductProjectorAbility extends EventStreamAbility {

    StoredProductInMemoryRepository STORED_PRODUCT_REPOSITORY = new StoredProductInMemoryRepository();

    StoredProductProjector INSTANCE = EventStreamAbility.register(
            new StoredProductProjector(StoredProductProjectorAbility.STORED_PRODUCT_REPOSITORY),
            StoredProductProjectorAbility.STORED_PRODUCT_REPOSITORY::deleteAll);

    default StoredProductProjector getStoredProductProjector() {
        return StoredProductProjectorAbility.INSTANCE;
    }

    default boolean expect_stored_product(Predicate<List<StoredProduct>> testCase) {
        return testCase.test(getStoredProductProjector().getStoredProduct(Map.of()));
    }

    default boolean expect_stored_product(Map<String, String> search, Predicate<List<StoredProduct>> testCase) {
        return testCase.test(getStoredProductProjector().getStoredProduct(search));
    }
}
