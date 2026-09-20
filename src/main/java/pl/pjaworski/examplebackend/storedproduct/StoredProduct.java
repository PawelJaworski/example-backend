package pl.pjaworski.examplebackend.storedproduct;

import java.util.UUID;

public record StoredProduct(UUID productKey, String productName, String productDescription) {
}
