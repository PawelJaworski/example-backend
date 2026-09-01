package pl.pjaworski.examplebackend.infrastructure;

import java.util.*;

public class DomainEventInMemoryRepository implements DomainEventRepository {
    private final Set<DomainEventEntity> entities = new HashSet<>();

    @Override
    public DomainEventEntity save(DomainEventEntity entity) {
        if (entity.getId() == null) {
            var newId = entities.stream().map(DomainEventEntity::getId)
                    .max(Comparator.naturalOrder()).map(it -> it + 1).orElse(1L);
            entity.setId(newId);
        }
        entities.add(entity);
        return entity;
    }

    @Override
    public Optional<DomainEventEntity> findById(Long id) {
        return entities.stream().filter(e -> Objects.equals(e.getId(), id)).findFirst();
    }

    @Override
    public List<DomainEventEntity> findAllByAggregateId(UUID aggregateId) {
        return entities.stream().filter(e -> Objects.equals(e.getAggregateId(), aggregateId)).toList();
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }
}
