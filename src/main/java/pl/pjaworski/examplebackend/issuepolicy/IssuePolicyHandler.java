// PRESERVED-BY-HAND: policy number is allocated from the policy_number_seq database
// sequence (GWT scenario "when issue policy then policy number has next ordinal"
// drives the decision); the model still carries the throwing stub for this bracketed
// decision.
package pl.pjaworski.examplebackend.issuepolicy;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;
import pl.pjaworski.examplebackend.eventstream.CommandHandler;
import pl.pjaworski.examplebackend.eventstream.EventStream;

@RestController
@Component
@Transactional
@RequiredArgsConstructor
public class IssuePolicyHandler implements CommandHandler<IssuePolicyCmd> {

    private final EventStream eventStream;
    private final PolicyNumberSequence policyNumberSequence;

    @PostMapping("issue-policy")
    @Override
    public UUID handle(@RequestBody IssuePolicyCmd command) {
        var aggregateId = UUID.randomUUID();
        eventStream.append(List.of(new PolicyIssuedEvent(
                aggregateId,
                command.policyHolder(),
                command.policyCoverage(),
                policyNumber())));
        return aggregateId;
    }

    private String policyNumber() {
        return policyNumberSequence.nextNumber();
    }
}
