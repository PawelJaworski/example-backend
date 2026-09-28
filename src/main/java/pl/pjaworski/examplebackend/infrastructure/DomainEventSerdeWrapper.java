package pl.pjaworski.examplebackend.infrastructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.pjaworski.examplebackend.domain.events.DomainEventType;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;

@JsonSubTypes({
        @JsonSubTypes.Type(value = PolicyIssuedEventSerdeWrapper.class, name = "POLICY_ISSUED"),
})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "eventType")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface DomainEventSerdeWrapper {
    DomainEventType getEventType();
    DomainEvent event();
}
