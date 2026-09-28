package pl.pjaworski.examplebackend.domain.events;

import lombok.Builder;
import pl.pjaworski.examplebackend.domain.PolicyCoverage;
import pl.pjaworski.examplebackend.domain.PolicyHolder;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;

@Builder
public record PolicyIssuedEvent(Long aggregateId, PolicyHolder policyHolder, PolicyCoverage policyCoverage, String policyNumber) implements DomainEvent {
    @Override
    public DomainEventType eventType() {
        return DomainEventType.POLICY_ISSUED;
    }
}
