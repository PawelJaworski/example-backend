package pl.pjaworski.examplebackend.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DomainEventRepository {
    DomainEventEntity save(DomainEventEntity entity);
    Optional<DomainEventEntity> findById(Long id);
    List<DomainEventEntity> findAllByAggregateId(UUID aggregateId);
    void deleteAll();
}
