package pl.pjaworski.examplebackend.policydetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PolicyDetailsRepository {
    PolicyDetailsEntity save(PolicyDetailsEntity entity);
    Optional<PolicyDetailsEntity> findById(Long id);
    List<PolicyDetailsEntity> findAll();
    List<PolicyDetailsEntity> findAllBySearch(Map<String, String> search);
    void deleteAll();
}
