package pl.pjaworski.examplebackend.addproductstostore

import pl.pjaworski.examplebackend.domain.Product
import pl.pjaworski.examplebackend.storedproduct.StoredProductProjectorAbility
import spock.lang.Specification

class AddProductsToStoreSpec extends Specification implements AddProductsToStoreAbility, StoredProductProjectorAbility {

    def setup() {
        reset_event_stream()
    }

    def "products added to the store are stored as one row per product"() {
        when:
        add_products_to_store { cmd ->
            cmd.product([
                    new Product("coffee", "arabica beans"),
                    new Product("tea", "green leaves"),
                    new Product("cocoa", "dark powder"),
            ])
        }

        then:
        expect_stored_product { rows ->
            assert rows.size() == 3
            assert rows*.productName == ["coffee", "tea", "cocoa"]
            assert rows*.productDescription == ["arabica beans", "green leaves", "dark powder"]
            true
        }
    }
}
