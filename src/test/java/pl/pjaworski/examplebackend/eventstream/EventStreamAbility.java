package pl.pjaworski.examplebackend.eventstream;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import pl.pjaworski.examplebackend.infrastructure.DomainEventInMemoryRepository;
import pl.pjaworski.examplebackend.infrastructure.EventStreamImpl;

public interface EventStreamAbility {

    List<PersistingProjector> PROJECTORS = new CopyOnWriteArrayList<>();
    List<Runnable> PROJECTION_RESETS = new CopyOnWriteArrayList<>();

    DomainEventInMemoryRepository REPOSITORY = new DomainEventInMemoryRepository();
    EventStream INSTANCE = new EventStreamImpl(REPOSITORY, PROJECTORS);

    static <P extends PersistingProjector> P register(P projector, Runnable reset) {
        EventStreamAbility.PROJECTORS.add(projector);
        EventStreamAbility.PROJECTION_RESETS.add(reset);
        return projector;
    }

    default EventStream getEventStream() {
        return EventStreamAbility.INSTANCE;
    }

    default void reset_event_stream() {
        EventStreamAbility.REPOSITORY.deleteAll();
        EventStreamAbility.PROJECTION_RESETS.forEach(Runnable::run);
    }
}
