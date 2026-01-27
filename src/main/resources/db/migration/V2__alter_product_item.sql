-- V2: Alter product_item to support BigDecimal price, SKU and optimistic locking version

-- change price type to numeric(19,4)
ALTER TABLE product_item
    ALTER COLUMN price TYPE numeric(19,4) USING price::numeric;

-- add sku column if not exists
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='product_item' AND column_name='sku') THEN
        ALTER TABLE product_item ADD COLUMN sku varchar;
        CREATE UNIQUE INDEX IF NOT EXISTS uq_product_item_sku ON product_item (sku);
    END IF;
END$$;

-- add version column for optimistic locking
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='product_item' AND column_name='version') THEN
        ALTER TABLE product_item ADD COLUMN version bigint DEFAULT 0;
    END IF;
END$$;

-- ensure price non-null if desired (only if existing rows updated)
-- ALTER TABLE product_item ALTER COLUMN price SET NOT NULL;

