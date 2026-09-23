package pl.pjaworski.examplebackend.issuepolicy

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PolicyNumberSequenceIntegrationSpec extends Specification {

    @Autowired
    PolicyNumberSequence policyNumberSequence

    def "allocates policy numbers from the database sequence"() {
        when:
        def first = policyNumberSequence.nextNumber()
        def second = policyNumberSequence.nextNumber()

        then:
        first == "POL-1"
        second == "POL-2"
    }
}
