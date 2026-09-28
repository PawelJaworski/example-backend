package pl.pjaworski.examplebackend.eventstream;

public interface CommandHandler<T> {
    Long handle(T cmd);
}
