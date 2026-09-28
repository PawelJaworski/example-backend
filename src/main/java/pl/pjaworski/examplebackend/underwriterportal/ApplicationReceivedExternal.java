package pl.pjaworski.examplebackend.underwriterportal;

/**
 * Inbound contract of external event 'application-received' (from Underwriter Portal).
 * NOT a DomainEvent — never appended to our stream; a translator maps it
 * onto a command and stops there.
 */
public record ApplicationReceivedExternal(String underwritterMessage) {
}
