// SCAFFOLDED ONCE by scripts/codegen — this file is YOURS.
// scaffold-version: 1
// Plain domain state hydrated only from events carrying this aggregate id.
package pl.pjaworski.examplebackend.domain;

import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;
import pl.pjaworski.examplebackend.eventstream.StateProjector;

public record PolicyAggregate(Long id) implements StateProjector<PolicyAggregate> {

    @Override
    public PolicyAggregate apply(PolicyAggregate state, PolicyIssuedEvent event) {
        return new PolicyAggregate(event.aggregateId());
    }
}
