package pl.pjaworski.examplebackend.issuedpolicies;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pjaworski.examplebackend.domain.events.PolicyIssuedEvent;
import pl.pjaworski.examplebackend.eventstream.DomainEvent;
import pl.pjaworski.examplebackend.eventstream.PersistingProjector;

@RestController
@Component
@RequiredArgsConstructor
public class IssuedPoliciesProjector implements PersistingProjector {

    private final IssuedPoliciesRepository repository;

    @GetMapping("issued-policies")
    public List<IssuedPolicies> getIssuedPolicies(@RequestParam Map<String, String> search) {
        return repository.findAllBySearch(search).stream()
                .map(IssuedPoliciesEntity::toReadModel)
                .toList();
    }

    @Override
    public void project(DomainEvent event) {
        switch (event) {
            case PolicyIssuedEvent evt -> {
                var state = repository.findById(evt.aggregateId())
                        .map(IssuedPoliciesEntity::toReadModel)
                        .orElse(null);
                apply(state, evt);
            }
            default -> {
            }
        }
    }

    public IssuedPolicies apply(IssuedPolicies state, PolicyIssuedEvent event) {
        var projected = new IssuedPolicies(
                event.aggregateId(),
                event.policyNumber(),
                event.policyHolder(),
                event.policyCoverage());
        repository.save(new IssuedPoliciesEntity(event.aggregateId(), projected.policyKey(), projected.policyNumber(), projected.policyHolder(), projected.policyCoverage()));
        return projected;
    }
}
