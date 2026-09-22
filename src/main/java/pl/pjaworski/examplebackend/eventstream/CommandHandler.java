// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 1
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.eventstream;

import java.util.UUID;

public interface CommandHandler<T> {
    UUID handle(T cmd);
}
