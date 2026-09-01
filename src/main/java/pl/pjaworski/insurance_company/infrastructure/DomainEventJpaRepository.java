package pl.pjaworski.examplebackend.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainEventJpaRepository extends DomainEventRepository, JpaRepository<DomainEventEntity, Long> {
}
