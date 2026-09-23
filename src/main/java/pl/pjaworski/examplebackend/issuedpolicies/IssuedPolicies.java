package pl.pjaworski.examplebackend.issuedpolicies;

import pl.pjaworski.examplebackend.domain.PolicyCoverage;
import pl.pjaworski.examplebackend.domain.PolicyHolder;

public record IssuedPolicies(Long policyKey, String policyNumber, PolicyHolder policyHolder, PolicyCoverage policyCoverage) {
}
