package pl.pjaworski.examplebackend.issuedpolicies;

import java.util.UUID;
import pl.pjaworski.examplebackend.domain.PolicyCoverage;
import pl.pjaworski.examplebackend.domain.PolicyHolder;

public record IssuedPolicies(UUID policyKey, String policyNumber, PolicyHolder policyHolder, PolicyCoverage policyCoverage) {
}
