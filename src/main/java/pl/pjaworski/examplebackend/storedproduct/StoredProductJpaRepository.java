package pl.pjaworski.examplebackend.storedproduct;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredProductJpaRepository
        extends StoredProductRepository,
                JpaRepository<StoredProductEntity, UUID> {

    @Override
    default List<StoredProductEntity> findAllBySearch(Map<String, String> search) {
        return findAll();
    }
}