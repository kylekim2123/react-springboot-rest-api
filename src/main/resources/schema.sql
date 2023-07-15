DROP TABLE products IF EXISTS;
DROP TABLE orders IF EXISTS;
DROP TABLE order_items IF EXISTS;

CREATE TABLE products(
    product_id UUID PRIMARY KEY,
    product_name VARCHAR(20) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price BIGINT NOT NULL,
    description VARCHAR(500) DEFAULT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) DEFAULT NULL
)

CREATE TABLE orders(
    order_id UUID PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    address VARCHAR(200) NOT NULL,
    postcode VARCHAR(200) NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) DEFAULT NULL
);

CREATE TABLE order_items(
    seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    category VARCHAR(50) NOT NULL,
    price BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) DEFAULT NULL,
    CONSTRAINT fk_order_items_to_order FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    CONSTRAINT fk_order_items_to_product FOREIGN KEY (product_id) REFERENCES products (product_id)
);