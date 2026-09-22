package pl.pjaworski.examplebackend.domain;

import java.util.List;

public record PolicyCoverage(String coveragePeriod, List<String> riskList) {
}
