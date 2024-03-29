CREATE TABLE product
(
    id             TEXT PRIMARY KEY UNIQUE NOT NULL,
    name           TEXT                    NOT NULL,
    category       TEXT                    NOT NULL,
    quantity_in_stock       INT                     NOT NULL,
    price_in_cents INT                     NOT NULL
);