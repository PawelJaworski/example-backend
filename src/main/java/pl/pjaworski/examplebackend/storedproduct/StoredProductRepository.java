package pl.pjaworski.examplebackend.storedproduct;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface StoredProductRepository {
    StoredProductEntity save(StoredProductEntity entity);
    Optional<StoredProductEntity> findById(UUID id);
    List<StoredProductEntity> findAll();
    List<StoredProductEntity> findAllBySearch(Map<String, String> search);
    void deleteAll();
}
