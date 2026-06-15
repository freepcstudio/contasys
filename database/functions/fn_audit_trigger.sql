CREATE OR REPLACE FUNCTION fn_audit_trigger()

RETURNS TRIGGER

LANGUAGE plpgsql

AS $$

BEGIN

    IF (TG_OP = 'INSERT') THEN

        INSERT INTO audit_logs(
            table_name,
            operation,
            record_id,
            new_data
        )
        VALUES(
            TG_TABLE_NAME,
            TG_OP,
            NEW.id,
            to_jsonb(NEW)
        );

        RETURN NEW;

    ELSIF (TG_OP = 'UPDATE') THEN

        INSERT INTO audit_logs(
            table_name,
            operation,
            record_id,
            old_data,
            new_data
        )
        VALUES(
            TG_TABLE_NAME,
            TG_OP,
            NEW.id,
            to_jsonb(OLD),
            to_jsonb(NEW)
        );

        RETURN NEW;

    ELSIF (TG_OP = 'DELETE') THEN

        INSERT INTO audit_logs(
            table_name,
            operation,
            record_id,
            old_data
        )
        VALUES(
            TG_TABLE_NAME,
            TG_OP,
            OLD.id,
            to_jsonb(OLD)
        );

        RETURN OLD;

    END IF;

    RETURN NULL;

END;

$$;