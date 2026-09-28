package pl.pjaworski.examplebackend.policydetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;
import pl.pjaworski.examplebackend.eventstream.PersistingProjector;

@RestController
@Component
@RequiredArgsConstructor
public class PolicyDetailsProjector implements PersistingProjector {

    private final PolicyDetailsRepository repository;

    @GetMapping("policy-details/{aggregateId}")
    public PolicyDetails getPolicyDetails(@PathVariable Long aggregateId) {
        return repository.findById(aggregateId)
                .map(PolicyDetailsEntity::toReadModel)
                .orElse(null);
    }

    @Override
    public void project(DomainEvent event) {
        switch (event) {
            case PolicyIssuedEvent evt -> {
                var state = repository.findById(evt.aggregateId())
                        .map(PolicyDetailsEntity::toReadModel)
                        .orElse(null);
                apply(state, evt);
            }
            default -> {
            }
        }
    }

    public PolicyDetails apply(PolicyDetails state, PolicyIssuedEvent event) {
        var projected = new PolicyDetails(
                event.aggregateId(),
                event.policyHolder(),
                event.policyCoverage(),
                event.policyNumber());
        repository.save(new PolicyDetailsEntity(event.aggregateId(), projected.policyKey(), projected.policyHolder(), projected.policyCoverage(), projected.policyNumber()));
        return projected;
    }
}
