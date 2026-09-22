// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 1
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.eventstream;

/**
 * A projection that keeps its own table instead of being replayed per request.
 * Declared in readmodels.md with {@code <aggregate>:Key}.
 *
 * <p>On-demand projections ({@code <aggregate>:Id}) hydrate from the stream on every
 * read, so they need no notification. A persisting one spans aggregates and therefore
 * cannot be rebuilt from {@code findAllById}, so the event stream pushes each appended
 * event here, synchronously and inside the appending transaction.
 */
public interface PersistingProjector {
    void project(DomainEvent event);
}
