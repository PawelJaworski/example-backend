package pl.pjaworski.examplebackend.eventstream;

import java.util.Collection;
import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;

public interface StateProjector<S> {

    default S hydrate(S state, Collection<DomainEvent> events) {
        return events.stream().reduce(state, this::apply, (_, s2) -> s2);
    }

    private S apply(S state, DomainEvent event) {
        return switch (event.eventType()) {
            case POLICY_ISSUED -> apply(state, (PolicyIssuedEvent) event);
            default -> state;
        };
    }

    default S apply(S state, PolicyIssuedEvent event) {
        return state;
    }
}
