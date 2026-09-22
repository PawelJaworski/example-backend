package pl.pjaworski.examplebackend.issuedpolicies;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedPoliciesJpaRepository
        extends IssuedPoliciesRepository,
                JpaRepository<IssuedPoliciesEntity, UUID> {

    @Override
    default List<IssuedPoliciesEntity> findAllBySearch(Map<String, String> search) {
        return findAll();
    }
}