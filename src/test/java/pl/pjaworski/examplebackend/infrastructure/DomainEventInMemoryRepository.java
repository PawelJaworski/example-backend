// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 2
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.infrastructure;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
    public List<DomainEventEntity> findAllByAggregateId(Long aggregateId) {
        return entities.stream()
                .filter(e -> Objects.equals(e.getAggregateId(), aggregateId))
                .toList();
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }
}
