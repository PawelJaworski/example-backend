package pl.pjaworski.examplebackend.eventstream;

public interface EventHandler<E> {
    void handle(E event);
}
