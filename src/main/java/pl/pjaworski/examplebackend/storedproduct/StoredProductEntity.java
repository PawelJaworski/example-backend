package pl.pjaworski.examplebackend.storedproduct;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stored_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoredProductEntity {

    @Id
    private UUID aggregateId;

    private UUID productKey;
    private String productName;
    private String productDescription;

    public StoredProduct toReadModel() {
        return new StoredProduct(productKey, productName, productDescription);
    }
}
