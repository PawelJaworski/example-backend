package pl.pjaworski.examplebackend.issuepolicy;

import java.util.function.Consumer;
import pl.pjaworski.examplebackend.eventstream.EventStreamAbility;
import pl.pjaworski.examplebackend.infrastructure.AggregateIdSequenceAbility;
import pl.pjaworski.examplebackend.testdata.TestDataAbility;

public interface IssuePolicyAbility extends TestDataAbility, EventStreamAbility {

    IssuePolicyHandler INSTANCE =
            new IssuePolicyHandler(EventStreamAbility.INSTANCE, AggregateIdSequenceAbility.INSTANCE);

    default IssuePolicyHandler getIssuePolicyHandler() {
        return IssuePolicyAbility.INSTANCE;
    }

    default Long issue_policy(Consumer<IssuePolicyCmd.IssuePolicyCmdBuilder> testCase) {
        var cmd = defaultIssuePolicyCmd();
        testCase.accept(cmd);
        return getIssuePolicyHandler().handle(cmd.build());
    }
}
