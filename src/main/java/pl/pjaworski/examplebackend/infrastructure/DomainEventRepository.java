// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 1
// Domain-independent event-sourcing runtime; adapt it freely.
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
