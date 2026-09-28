package pl.pjaworski.examplebackend.issuedpolicies;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import pl.pjaworski.examplebackend.eventstream.EventStreamAbility;

public interface IssuedPoliciesProjectorAbility extends EventStreamAbility {

    IssuedPoliciesInMemoryRepository ISSUED_POLICIES_REPOSITORY = new IssuedPoliciesInMemoryRepository();

    IssuedPoliciesProjector INSTANCE = EventStreamAbility.register(
            new IssuedPoliciesProjector(IssuedPoliciesProjectorAbility.ISSUED_POLICIES_REPOSITORY),
            IssuedPoliciesProjectorAbility.ISSUED_POLICIES_REPOSITORY::deleteAll);

    default IssuedPoliciesProjector getIssuedPoliciesProjector() {
        return IssuedPoliciesProjectorAbility.INSTANCE;
    }

    default boolean expect_issued_policies(Predicate<List<IssuedPolicies>> testCase) {
        return testCase.test(getIssuedPoliciesProjector().getIssuedPolicies(Map.of()));
    }

    default boolean expect_issued_policies(Map<String, String> search, Predicate<List<IssuedPolicies>> testCase) {
        return testCase.test(getIssuedPoliciesProjector().getIssuedPolicies(search));
    }
}
