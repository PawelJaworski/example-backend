package pl.pjaworski.examplebackend.policydetails;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyDetailsJpaRepository
        extends PolicyDetailsRepository,
                JpaRepository<PolicyDetailsEntity, Long> {

    @Override
    default List<PolicyDetailsEntity> findAllBySearch(Map<String, String> search) {
        return findAll();
    }
}