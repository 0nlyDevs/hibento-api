CREATE TABLE question_upvote (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    question_id UUID NOT NULL REFERENCES question(id) ON DELETE CASCADE,
    visitor_id VARCHAR(64) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    UNIQUE(question_id, visitor_id)
);

CREATE INDEX idx_question_upvote_question_id ON question_upvote(question_id);
