CREATE TABLE product
(
    product_code   SERIAL PRIMARY KEY UNIQUE NOT NULL,
    category       TEXT                      NOT NULL,
    subcategory    TEXT                      NOT NULL,
    name           TEXT                      NOT NULL,
    stock_quantity INT                       NOT NULL,
    price_in_cents INT                       NOT NULL,
    size_or_lot    TEXT                      NOT NULL,
    expiry_date    DATE                      NOT NULL
);

ALTER SEQUENCE product_product_code_seq RESTART WITH 1000;