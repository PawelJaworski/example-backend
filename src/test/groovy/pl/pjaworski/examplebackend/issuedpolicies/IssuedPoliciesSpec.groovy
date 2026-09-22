package pl.pjaworski.examplebackend.issuedpolicies

import pl.pjaworski.examplebackend.issuepolicy.IssuePolicyAbility
import pl.pjaworski.examplebackend.issuepolicy.PolicyNumberSequenceAbility
import spock.lang.Specification

class IssuedPoliciesSpec extends Specification implements IssuePolicyAbility, IssuedPoliciesProjectorAbility, PolicyNumberSequenceAbility {

    def setup() {
        reset_event_stream()
        reset_policy_number_sequence()
    }

    def "when issue policy then policy number has next ordinal"() {
        given:
        def firstPolicy = issue_policy { }

        when:
        def secondPolicy = issue_policy { }

        then:
        expect_issued_policies { policies ->
            policies.getLast().policyNumber == "POL-2"
        }
    }

    def "policy numbers continue from the sequence when the event stream is cleared"() {
        given: "two policies have been issued, consuming POL-1 and POL-2"
        issue_policy { }
        issue_policy { }

        and: "the event stream is cleared but the sequence is not"
        reset_event_stream()

        when: "a third policy is issued"
        issue_policy { }

        then: "its number comes from the caller-visible sequence, not the event count"
        expect_issued_policies { policies ->
            policies.getLast().policyNumber == "POL-3"
        }
    }
}