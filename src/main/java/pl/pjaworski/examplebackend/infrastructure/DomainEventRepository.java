package pl.pjaworski.examplebackend.infrastructure;

import java.util.List;
import java.util.Optional;

public interface DomainEventRepository {
    DomainEventEntity save(DomainEventEntity entity);
    Optional<DomainEventEntity> findById(Long id);
    List<DomainEventEntity> findAllByAggregateId(Long aggregateId);
    void deleteAll();
}
