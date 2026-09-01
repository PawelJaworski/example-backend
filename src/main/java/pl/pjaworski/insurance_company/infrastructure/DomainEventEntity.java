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
        this.eventJson = serialize(event);
    }

    public DomainEvent toDomainEvent() {
        return eventJson.event();
    }

    private DomainEventSerdeWrapper serialize(DomainEvent event) {
//        switch (event.eventType()) {
//            case FOO -> new FooEventSerdeWrapper(event);
//            case BAR -> new BarEventSerdeWrapper(event);
//        };

        return null;
    }
}
