CREATE TABLE IF NOT EXISTS ${schema}.models (
    id UUID PRIMARY KEY,
    user_id UUID NULL,
    thumbnail_url TEXT,
    model_3d_url TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID,
    updated_by UUID,
    deleted BOOLEAN,
    CONSTRAINT user_id_fk 
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE SET NULL
);