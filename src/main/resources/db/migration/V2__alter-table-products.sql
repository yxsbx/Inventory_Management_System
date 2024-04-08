ALTER TABLE product ADD COLUMN active_discount BOOLEAN;
UPDATE product SET active_discount = false;