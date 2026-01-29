-- V3: Ensure user deletion cascades to use_payment_method by updating FK constraint
-- This migration drops the existing FK and recreates it with ON DELETE CASCADE

-- Drop existing constraint if present
ALTER TABLE use_payment_method
    DROP CONSTRAINT IF EXISTS fk_user_id_use_payment_method;

-- Recreate constraint with ON DELETE CASCADE so removing a user will delete related rows
ALTER TABLE use_payment_method
    ADD CONSTRAINT fk_user_id_use_payment_method
        FOREIGN KEY (user_id)
        REFERENCES app_user(id)
        ON DELETE CASCADE;
