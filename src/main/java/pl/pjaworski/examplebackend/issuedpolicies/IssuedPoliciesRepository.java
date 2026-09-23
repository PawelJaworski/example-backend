package pl.pjaworski.examplebackend.issuedpolicies;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IssuedPoliciesRepository {
    IssuedPoliciesEntity save(IssuedPoliciesEntity entity);
    Optional<IssuedPoliciesEntity> findById(Long id);
    List<IssuedPoliciesEntity> findAll();
    List<IssuedPoliciesEntity> findAllBySearch(Map<String, String> search);
    void deleteAll();
}
