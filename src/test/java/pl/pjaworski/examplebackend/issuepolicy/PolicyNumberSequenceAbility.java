// Rule 2: every Spring component has a corresponding ability — PolicyNumberSequence is
// a Spring component, so this ability exposes its INSTANCE and its reset for specs.
package pl.pjaworski.examplebackend.issuepolicy;

public interface PolicyNumberSequenceAbility {

    PolicyNumberSequence INSTANCE = PolicyNumberSequence.inMemory();

    default void reset_policy_number_sequence() {
        PolicyNumberSequenceAbility.INSTANCE.reset();
    }
}
