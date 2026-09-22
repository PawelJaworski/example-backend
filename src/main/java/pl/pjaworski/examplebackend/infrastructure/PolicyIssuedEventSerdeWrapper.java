package pl.pjaworski.examplebackend.infrastructure;

import com.fasterxml.jackson.annotation.JsonTypeName;
import pl.pjaworski.examplebackend.domain.events.DomainEventType;
import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;

@JsonTypeName("POLICY_ISSUED")
public record PolicyIssuedEventSerdeWrapper(PolicyIssuedEvent event) implements DomainEventSerdeWrapper {
    @Override
    public DomainEventType getEventType() {
        return DomainEventType.POLICY_ISSUED;
    }
}
