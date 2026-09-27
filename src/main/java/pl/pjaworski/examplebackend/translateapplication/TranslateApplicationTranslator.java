package pl.pjaworski.examplebackend.translateapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.pjaworski.examplebackend.domain.PolicyCoverage;
import pl.pjaworski.examplebackend.domain.PolicyHolder;
import pl.pjaworski.examplebackend.eventstream.CommandHandler;
import pl.pjaworski.examplebackend.issuepolicy.IssuePolicyCmd;
import pl.pjaworski.examplebackend.underwriterportal.ApplicationReceivedExternal;

@Component
@Transactional
@RequiredArgsConstructor
public class TranslateApplicationTranslator {

    private final CommandHandler<IssuePolicyCmd> issuePolicyHandler;

    @KafkaListener(topics = "application-received")
    public void onApplicationReceived(String raw) {
        onApplicationReceived(parseApplicationReceived(raw));
    }

    /**
     * Ingress logic — call this directly in tests (no Kafka needed).
     */
    public void onApplicationReceived(ApplicationReceivedExternal payload) {
        var mappedCommand = toIssuePolicyCmd(payload);
        issuePolicyHandler.handle(mappedCommand);
    }

    private IssuePolicyCmd toIssuePolicyCmd(ApplicationReceivedExternal payload) {
        return new IssuePolicyCmd(
                policyHolder(payload),
                policyCoverage(payload));
    }

    private PolicyHolder policyHolder(ApplicationReceivedExternal payload) {
        throw new UnsupportedOperationException(
                "mapping of external event 'application-received' to command field 'policy holder' is not implemented");
    }

    private PolicyCoverage policyCoverage(ApplicationReceivedExternal payload) {
        throw new UnsupportedOperationException(
                "mapping of external event 'application-received' to command field 'policy coverage' is not implemented");
    }

    private ApplicationReceivedExternal parseApplicationReceived(String raw) {
        throw new UnsupportedOperationException(
                "parsing of external event 'application-received' wire format is not implemented — " +
                        "map the raw Kafka payload (JSON/Avro/Protobuf/...) onto ApplicationReceivedExternal");
    }
}
