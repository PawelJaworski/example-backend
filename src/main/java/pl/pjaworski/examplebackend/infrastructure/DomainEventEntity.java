// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 2
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.infrastructure;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import pl.pjaworski.examplebackend.domain.events.DomainEventType;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;

import java.util.UUID;

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
