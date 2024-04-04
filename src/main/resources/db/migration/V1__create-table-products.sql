CREATE TABLE product
(
    product_code   INTEGER      NOT NULL PRIMARY KEY IDENTITY,
    category       VARCHAR(255) NOT NULL,
    subcategory    VARCHAR(255) NOT NULL,
    name           VARCHAR(255) NOT NULL,
    stock_quantity INT          NOT NULL,
    price_in_cents INT          NOT NULL,
    size_or_lot    VARCHAR(255) NOT NULL,
    expiry_date    DATE         NOT NULL
);