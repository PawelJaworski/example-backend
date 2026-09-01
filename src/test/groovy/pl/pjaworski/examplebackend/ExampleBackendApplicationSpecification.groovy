package pl.pjaworski.examplebackend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import pl.pjaworski.examplebackend.eventstream.EventStream
import spock.lang.Specification

@SpringBootTest
class ExampleBackendApplicationSpecification extends Specification {

    @Autowired
    EventStream eventStream

    def "loads spring context"() {
        expect:
        true
    }
}
