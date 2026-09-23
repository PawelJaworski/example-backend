// SCAFFOLDED ONCE by the backend codegen — this file is YOURS.
// scaffold-version: 1
// Domain-independent event-sourcing runtime; adapt it freely.
package pl.pjaworski.examplebackend.infrastructure;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Allocates aggregate ids from the {@code aggregate_id_seq} database sequence.
 *
 * <p>A plain infrastructure collaborator of every command handler: the handler
 * calls {@link #nextId()} before appending the first event, so the id is known
 * before persistence and is stable across the aggregate's event stream.</p>
 *
 * <p>Unit specs construct the handler without Spring, so the handler defaults its
 * collaborator to the shared {@link #inMemory()} double. It has exactly the same
 * contract and {@link #reset()} semantics as a repository/sequence collaborator.
 */
@Component
public class AggregateIdSequence {

    private static final AggregateIdSequence IN_MEMORY = new InMemorySequence();

    private final JdbcTemplate jdbc;

    public AggregateIdSequence(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /** Shared in-memory double used by unit specs (no Spring context there). */
    public static AggregateIdSequence inMemory() {
        return IN_MEMORY;
    }

    /** Allocates the next aggregate id from the database sequence. */
    public Long nextId() {
        return jdbc.queryForObject("SELECT NEXT VALUE FOR aggregate_id_seq", Long.class);
    }

    /** Test hook: resets mutable collaborator state. No-op on the DB-backed instance. */
    public void reset() {
    }

    private static final class InMemorySequence extends AggregateIdSequence {
        private final AtomicLong counter = new AtomicLong();

        private InMemorySequence() {
            super(null);
        }

        @Override
        public Long nextId() {
            return counter.incrementAndGet();
        }

        @Override
        public void reset() {
            counter.set(0);
        }
    }
}
