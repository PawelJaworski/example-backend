package pl.pjaworski.examplebackend.addproductstostore;

import java.util.List;
import lombok.Builder;
import pl.pjaworski.examplebackend.domain.Product;

@Builder
public record AddProductsToStoreCmd(List<Product> product) {
}
