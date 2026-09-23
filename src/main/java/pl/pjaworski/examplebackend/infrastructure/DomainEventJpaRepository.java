// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 1
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainEventJpaRepository
        extends DomainEventRepository, JpaRepository<DomainEventEntity, Long> {
}
