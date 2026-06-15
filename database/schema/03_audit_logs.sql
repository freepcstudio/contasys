CREATE TABLE audit_logs (

    id BIGSERIAL PRIMARY KEY,

    table_name VARCHAR(100) NOT NULL,

    operation VARCHAR(20) NOT NULL,

    record_id BIGINT,

    old_data JSONB,

    new_data JSONB,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

);