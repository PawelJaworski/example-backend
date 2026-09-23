-- Aggregate ids are allocated from a database sequence (command handler -> AggregateIdSequence),
-- not by counting rows; the sequence survives event-stream clears and doesn't reuse ids on rollback.
CREATE SEQUENCE IF NOT EXISTS aggregate_id_seq START WITH 1 INCREMENT BY 1;

-- Policy numbers are allocated from a database sequence (IssuePolicyHandler -> PolicyNumberSequence),
-- not by counting rows; the sequence survives event-stream clears and doesn't reuse numbers on rollback.
CREATE SEQUENCE IF NOT EXISTS policy_number_seq START WITH 1 INCREMENT BY 1;
