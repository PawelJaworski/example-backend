-- Policy numbers are allocated from a database sequence (IssuePolicyHandler -> PolicyNumberSequence),
-- not by counting rows; the sequence survives event-stream clears and doesn't reuse numbers on rollback.
CREATE SEQUENCE IF NOT EXISTS policy_number_seq START WITH 1 INCREMENT BY 1;