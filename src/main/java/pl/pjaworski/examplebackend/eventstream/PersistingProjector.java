package pl.pjaworski.examplebackend.eventstream;

public interface PersistingProjector {
    void project(DomainEvent event);
}
