package pl.pjaworski.examplebackend.issuepolicy;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;
import pl.pjaworski.examplebackend.eventstream.CommandHandler;
import pl.pjaworski.examplebackend.eventstream.EventStream;
import pl.pjaworski.examplebackend.infrastructure.AggregateIdSequence;

@RestController
@Component
@Transactional
@RequiredArgsConstructor
public class IssuePolicyHandler implements CommandHandler<IssuePolicyCmd> {

    private final EventStream eventStream;
    private final AggregateIdSequence aggregateIdSequence;
    private final AtomicLong policyOrdinal = new AtomicLong();

    @PostMapping("issue-policy")
    @Override
    public Long handle(@RequestBody IssuePolicyCmd command) {
        var aggregateId = aggregateIdSequence.nextId();
        eventStream.append(List.of(new PolicyIssuedEvent(
                aggregateId,
                command.policyHolder(),
                command.policyCoverage(),
                policyNumber())));
        return aggregateId;
    }

    /**
     * GWT "when issue policy then policy number has next ordinal": the business definition
     * gives POL-1, POL-2 - a fixed prefix plus a monotonic ordinal, so the ordinal is the
     * number of policies already issued. Global, not aggregate-scoped, so it is kept here.
     */
    private String policyNumber() {
        return "POL-" + policyOrdinal.incrementAndGet();
    }

    /** Test hook: resets mutable handler state between specs. */
    public void resetPolicyNumberOrdinal() {
        policyOrdinal.set(0);
    }
}
