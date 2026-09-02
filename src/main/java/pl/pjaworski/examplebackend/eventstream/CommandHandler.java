package pl.pjaworski.examplebackend.eventstream;

import java.util.UUID;

public interface CommandHandler<T> {
    UUID handle(T cmd);
}
