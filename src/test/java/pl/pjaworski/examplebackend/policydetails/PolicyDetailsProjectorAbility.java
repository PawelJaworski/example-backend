package pl.pjaworski.examplebackend.policydetails;

import java.util.function.Predicate;
import pl.pjaworski.examplebackend.eventstream.EventStreamAbility;

public interface PolicyDetailsProjectorAbility extends EventStreamAbility {

    PolicyDetailsInMemoryRepository POLICY_DETAILS_REPOSITORY = new PolicyDetailsInMemoryRepository();

    PolicyDetailsProjector INSTANCE = EventStreamAbility.register(
            new PolicyDetailsProjector(PolicyDetailsProjectorAbility.POLICY_DETAILS_REPOSITORY),
            PolicyDetailsProjectorAbility.POLICY_DETAILS_REPOSITORY::deleteAll);

    default PolicyDetailsProjector getPolicyDetailsProjector() {
        return PolicyDetailsProjectorAbility.INSTANCE;
    }

    default boolean expect_policy_details(Long aggregateId, Predicate<PolicyDetails> testCase) {
        return testCase.test(getPolicyDetailsProjector().getPolicyDetails(aggregateId));
    }
}
