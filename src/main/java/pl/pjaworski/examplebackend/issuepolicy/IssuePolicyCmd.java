package pl.pjaworski.examplebackend.issuepolicy;

import lombok.Builder;
import pl.pjaworski.examplebackend.domain.PolicyCoverage;
import pl.pjaworski.examplebackend.domain.PolicyHolder;

@Builder
public record IssuePolicyCmd(PolicyHolder policyHolder, PolicyCoverage policyCoverage) {
}
