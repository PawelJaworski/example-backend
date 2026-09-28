package pl.pjaworski.examplebackend.policydetails;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PolicyDetailsInMemoryRepository implements PolicyDetailsRepository {

    private final Map<Long, PolicyDetailsEntity> entities = new LinkedHashMap<>();

    @Override
    public PolicyDetailsEntity save(PolicyDetailsEntity entity) {
        entities.put(entity.getAggregateId(), entity);
        return entity;
    }

    @Override
    public Optional<PolicyDetailsEntity> findById(Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<PolicyDetailsEntity> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public List<PolicyDetailsEntity> findAllBySearch(Map<String, String> search) {
        return findAll();
    }
    @Override
    public void deleteAll() {
        entities.clear();
    }
}
