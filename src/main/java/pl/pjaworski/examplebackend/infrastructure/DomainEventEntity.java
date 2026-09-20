// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 2
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.infrastructure;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import pl.pjaworski.examplebackend.domain.events.DomainEventType;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;

@Entity
@Table(name = "domain_event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DomainEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    private UUID aggregateId;

    @Enumerated(EnumType.STRING)
    private DomainEventType type;

    @NonNull
    @JdbcTypeCode(SqlTypes.JSON)
    private DomainEventSerdeWrapper eventJson;

    public DomainEventEntity(DomainEvent event) {
        this.aggregateId = event.aggregateId();
        this.type = event.eventType();
        // The event -> wrapper switch is GENERATED (DomainEventSerde), so adding an
        // event to events.md wires serialization automatically. Do not inline it here.
        this.eventJson = DomainEventSerde.serialize(event);
    }

    public DomainEvent toDomainEvent() {
        return eventJson.event();
    }
}
