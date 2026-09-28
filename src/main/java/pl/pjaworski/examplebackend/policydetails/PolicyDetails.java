package pl.pjaworski.examplebackend.policydetails;

import pl.pjaworski.examplebackend.domain.PolicyCoverage;
import pl.pjaworski.examplebackend.domain.PolicyHolder;

public record PolicyDetails(Long policyKey, PolicyHolder policyHolder, PolicyCoverage policyCoverage, String policyNumber) {
}
