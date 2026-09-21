package pl.pjaworski.examplebackend.eventstream;

import java.util.Collection;

public interface StateProjector<S> {
    default S hydrate(S state, Collection<DomainEvent> events) {
        return events.stream().reduce(state, this::apply, (_, s2) -> s2);
    }

    private S apply(S state, DomainEvent event) {
        return state;
    }
}
