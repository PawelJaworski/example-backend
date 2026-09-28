package pl.pjaworski.examplebackend.infrastructure;

public interface AggregateIdSequenceAbility {

    AggregateIdSequence INSTANCE = AggregateIdSequence.inMemory();

    default AggregateIdSequence getAggregateIdSequence() {
        return AggregateIdSequenceAbility.INSTANCE;
    }

    default void reset_aggregate_id_sequence() {
        AggregateIdSequenceAbility.INSTANCE.reset();
    }
}
