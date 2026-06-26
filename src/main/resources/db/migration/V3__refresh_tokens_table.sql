CREATE TABLE auth.refresh_tokens (
                                     id UUID PRIMARY KEY,
                                     token TEXT NOT NULL UNIQUE,
                                     user_id UUID NOT NULL,
                                     expires_at TIMESTAMP NOT NULL,
                                     revoked BOOLEAN NOT NULL DEFAULT FALSE,
                                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                     CONSTRAINT fk_refresh_tokens_user
                                         FOREIGN KEY (user_id)
                                             REFERENCES auth.users(id)
                                             ON DELETE CASCADE
);


CREATE INDEX idx_refresh_tokens_user_id
    ON auth.refresh_tokens(user_id);

CREATE INDEX idx_refresh_tokens_expires_at
    ON auth.refresh_tokens(expires_at);