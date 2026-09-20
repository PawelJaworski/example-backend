package pl.pjaworski.examplebackend.storedproduct;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class StoredProductInMemoryRepository implements StoredProductRepository {

    private final Map<UUID, StoredProductEntity> entities = new LinkedHashMap<>();

    @Override
    public StoredProductEntity save(StoredProductEntity entity) {
        entities.put(entity.getAggregateId(), entity);
        return entity;
    }

    @Override
    public Optional<StoredProductEntity> findById(UUID id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<StoredProductEntity> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public List<StoredProductEntity> findAllBySearch(Map<String, String> search) {
        return findAll();
    }
    @Override
    public void deleteAll() {
        entities.clear();
    }
}
