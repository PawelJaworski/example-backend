package pl.pjaworski.examplebackend.issuedpolicies;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class IssuedPoliciesInMemoryRepository implements IssuedPoliciesRepository {

    private final Map<UUID, IssuedPoliciesEntity> entities = new LinkedHashMap<>();

    @Override
    public IssuedPoliciesEntity save(IssuedPoliciesEntity entity) {
        entities.put(entity.getAggregateId(), entity);
        return entity;
    }

    @Override
    public Optional<IssuedPoliciesEntity> findById(UUID id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<IssuedPoliciesEntity> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public List<IssuedPoliciesEntity> findAllBySearch(Map<String, String> search) {
        return findAll();
    }
    @Override
    public void deleteAll() {
        entities.clear();
    }
}
