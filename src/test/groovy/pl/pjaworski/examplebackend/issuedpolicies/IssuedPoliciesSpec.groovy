package pl.pjaworski.examplebackend.issuedpolicies

import pl.pjaworski.examplebackend.infrastructure.AggregateIdSequenceAbility
import pl.pjaworski.examplebackend.issuepolicy.IssuePolicyAbility
import spock.lang.Specification

class IssuedPoliciesSpec extends Specification implements IssuePolicyAbility, IssuedPoliciesProjectorAbility, AggregateIdSequenceAbility {

    def setup() {
        reset_event_stream()
        reset_aggregate_id_sequence()
        getIssuePolicyHandler().resetPolicyNumberOrdinal()
    }

    def "when issue policy then policy number has next ordinal"() {
        given:
        issue_policy {}

        when:
        issue_policy {}

        then:
        expect_issued_policies { it*.policyNumber() == ["POL-1", "POL-2"] }
    }
}
