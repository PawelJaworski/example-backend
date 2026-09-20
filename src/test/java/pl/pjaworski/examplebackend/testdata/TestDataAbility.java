// SCAFFOLDED ONCE by scripts/codegen — this file is YOURS.
// scaffold-version: 1
// Test data for specs: every generated *Ability DSL pre-sets its builder from these
// constants, so a spec overrides only what its scenario cares about. A
// "= null" constant flows as no default: fill it from the business
// definition's examples, invent a value, or opt out with a trailing
// "// no test data" marker.
package pl.pjaworski.examplebackend.testdata;

import java.util.List;
import pl.pjaworski.examplebackend.addproductstostore.AddProductsToStoreCmd;
import pl.pjaworski.examplebackend.domain.Product;

public interface TestDataAbility {

    List<Product> TEST_PRODUCT = List.of(
            new Product("coffee", "arabica beans"),
            new Product("tea", "green leaves")); // invented: no example in business-definitions-raw.md

    default AddProductsToStoreCmd.AddProductsToStoreCmdBuilder defaultAddProductsToStoreCmd() {
        return AddProductsToStoreCmd.builder()
                .product(TEST_PRODUCT);
    }
}
