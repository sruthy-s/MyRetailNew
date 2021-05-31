DROP TABLE IF EXISTS Price;
DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
    id INT IDENTITY(10000000,1) PRIMARY KEY,
    `name` VARCHAR
);

CREATE TABLE Price (
    product_id INT PRIMARY KEY,
    currency VARCHAR,
    `value` DECIMAL
);

INSERT INTO Product (`name`) values ('Samsung TV');
INSERT INTO Product (`name`) values ('Oneplus phone');
INSERT INTO Price (currency, `value`, product_id) values ('USD', 200.42, 10000000);
INSERT INTO Price (currency, `value`, product_id) values ('INR', 30000, 10000001);