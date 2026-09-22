package pl.pjaworski.examplebackend.issuepolicy;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Allocates policy numbers from the {@code policy_number_seq} database sequence.
 *
 * <p>A plain domain collaborator of {@link IssuePolicyHandler}: a bracketed decision
 * that needs a cross-aggregate/global source is kept out of the aggregate, so the
 * handler delegates the number to this service.</p>
 *
 * <p>Unit specs construct the handler without Spring, so the handler defaults its
 * collaborator to the shared {@link #inMemory()} double. It has exactly the same
 * contract and {@link #reset()} semantics as a repository/sequence collaborator
 * (see the ad-hoc extensions recipe): every spec exercising the slice resets it in
 * {@code setup()}.</p>
 */
@Component
public class PolicyNumberSequence {

    private static final PolicyNumberSequence IN_MEMORY = new InMemorySequence();

    private final JdbcTemplate jdbc;

    public PolicyNumberSequence(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /** Shared in-memory double used by unit specs (no Spring context there). */
    public static PolicyNumberSequence inMemory() {
        return IN_MEMORY;
    }

    /** Allocates the next policy number from the database sequence. */
    public String nextNumber() {
        Long next = jdbc.queryForObject("SELECT NEXT VALUE FOR policy_number_seq", Long.class);
        return "POL-" + next;
    }

    /** Test hook: resets mutable collaborator state. No-op on the DB-backed instance. */
    public void reset() {
    }

    private static final class InMemorySequence extends PolicyNumberSequence {
        private final AtomicLong counter = new AtomicLong();

        private InMemorySequence() {
            super(null);
        }

        @Override
        public String nextNumber() {
            return "POL-" + counter.incrementAndGet();
        }

        @Override
        public void reset() {
            counter.set(0);
        }
    }
}