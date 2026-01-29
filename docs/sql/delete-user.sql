-- Delete a user safely (PostgreSQL)
--
-- How to use:
-- 1) Replace {{USER_ID}} below with the UUID of the user you want to delete.
--    Example: 86a6836c-3150-43ee-a337-ccbf0fb42591
-- 2) Run this whole script in your database terminal (psql, DBeaver, etc.).
--
-- Notes:
-- - This script removes dependent rows that reference the user to avoid
--   foreign key violations (e.g., use_payment_method, user_review, shopping_cart).
-- - If your database already has ON DELETE CASCADE configured on these FKs,
--   you could delete from app_user directly; keeping these DELETE statements
--   makes the script safe on schemas without cascading.
-- - Review and adjust the dependent table list to match your schema if needed.

BEGIN;

-- Replace this placeholder with the actual user ID (UUID)
-- Example replacement: {{USER_ID}} -> 86a6836c-3150-43ee-a337-ccbf0fb42591

-- 1) Delete dependent records first (to avoid FK constraint errors)
DELETE FROM use_payment_method WHERE user_id = '{{USER_ID}}';
DELETE FROM user_review         WHERE user_id = '{{USER_ID}}';

-- If your schema has a shopping cart table referencing users
-- (rename table/column if different in your DB):
-- DELETE FROM shopping_cart       WHERE user_id = '{{USER_ID}}';

-- Add other dependent deletions here if your schema references app_user elsewhere
-- e.g., orders, addresses, sessions, etc.

-- 2) Delete the user
DELETE FROM app_user WHERE id = '{{USER_ID}}';

COMMIT;

-- If anything fails, you can ROLLBACK instead of COMMIT (in psql, run ROLLBACK;)
