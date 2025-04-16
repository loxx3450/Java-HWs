CREATE FUNCTION check_if_client_is_free()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM rents
        WHERE client_id = NEW.client_id
            AND ended_at IS NULL
    ) THEN
        RAISE EXCEPTION 'One client can only rent one flat';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER prevent_several_flats_by_client
BEFORE INSERT ON rents
FOR EACH ROW
EXECUTE FUNCTION check_if_client_is_free();