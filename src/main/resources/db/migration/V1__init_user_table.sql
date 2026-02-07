CREATE TABLE IF NOT EXISTS ${schema}.users (
    id UUID PRIMARY KEY,
    user_keycloak_id UUID NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    user_name VARCHAR(255)NOT NULL UNIQUE,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    user_avatar TEXT,
    is_verified BOOLEAN DEFAULT false,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID,
    updated_by UUID,
    deleted BOOLEAN DEFAULT false
);
