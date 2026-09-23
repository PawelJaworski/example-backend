package pl.pjaworski.examplebackend.issuepolicy;

import java.util.function.Consumer;
import pl.pjaworski.examplebackend.eventstream.EventStreamAbility;
import pl.pjaworski.examplebackend.infrastructure.AggregateIdSequenceAbility;
import pl.pjaworski.examplebackend.testdata.TestDataAbility;

// PRESERVED-BY-HAND: Rule 1 — INSTANCE creation wires IssuePolicyHandler's collaborators
// via their abilities: PolicyNumberSequenceAbility.INSTANCE.
public interface IssuePolicyAbility extends TestDataAbility, EventStreamAbility, AggregateIdSequenceAbility {

    IssuePolicyHandler INSTANCE =
            new IssuePolicyHandler(EventStreamAbility.INSTANCE, AggregateIdSequenceAbility.INSTANCE, PolicyNumberSequenceAbility.INSTANCE);

    default IssuePolicyHandler getIssuePolicyHandler() {
        return IssuePolicyAbility.INSTANCE;
    }

    default Long issue_policy(Consumer<IssuePolicyCmd.IssuePolicyCmdBuilder> testCase) {
        var cmd = defaultIssuePolicyCmd();
        testCase.accept(cmd);
        return getIssuePolicyHandler().handle(cmd.build());
    }
}
