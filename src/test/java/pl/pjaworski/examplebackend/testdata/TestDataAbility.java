package pl.pjaworski.examplebackend.testdata;

import pl.pjaworski.examplebackend.domain.PolicyCoverage;
import pl.pjaworski.examplebackend.domain.PolicyHolder;
import pl.pjaworski.examplebackend.issuepolicy.IssuePolicyCmd;

public interface TestDataAbility {

    PolicyHolder TEST_POLICY_HOLDER = new PolicyHolder("John Snow", "Aleja Gwiazd 15/5 Wygwizdow");
    PolicyCoverage TEST_POLICY_COVERAGE = null; // TODO test data: no example in business-definitions-raw.md — create your own

    default IssuePolicyCmd.IssuePolicyCmdBuilder defaultIssuePolicyCmd() {
        return IssuePolicyCmd.builder()
                .policyHolder(TEST_POLICY_HOLDER)
                .policyCoverage(TEST_POLICY_COVERAGE);
    }
}
