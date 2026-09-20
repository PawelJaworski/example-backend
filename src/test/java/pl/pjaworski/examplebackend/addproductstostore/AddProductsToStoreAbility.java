package pl.pjaworski.examplebackend.addproductstostore;

import java.util.UUID;
import java.util.function.Consumer;
import pl.pjaworski.examplebackend.eventstream.EventStreamAbility;
import pl.pjaworski.examplebackend.testdata.TestDataAbility;

public interface AddProductsToStoreAbility extends TestDataAbility, EventStreamAbility {

    AddProductsToStoreHandler INSTANCE =
            new AddProductsToStoreHandler(EventStreamAbility.INSTANCE);

    default AddProductsToStoreHandler getAddProductsToStoreHandler() {
        return AddProductsToStoreAbility.INSTANCE;
    }

    default UUID add_products_to_store(Consumer<AddProductsToStoreCmd.AddProductsToStoreCmdBuilder> testCase) {
        var cmd = defaultAddProductsToStoreCmd();
        testCase.accept(cmd);
        return getAddProductsToStoreHandler().handle(cmd.build());
    }
}
