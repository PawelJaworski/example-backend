package pl.pjaworski.examplebackend.domain;

import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;
import pl.pjaworski.examplebackend.eventstream.StateProjector;

public record PolicyAggregate(Long id) implements StateProjector<PolicyAggregate> {

    @Override
    public PolicyAggregate apply(PolicyAggregate state, PolicyIssuedEvent event) {
        return new PolicyAggregate(event.aggregateId());
    }
}
