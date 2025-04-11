CREATE TABLE book(
     id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
     name VARCHAR(255) NOT NULL,
     price DECIMAL(10, 2) NOT NULL,
     status VARCHAR(50) NOT NULL,
     customer_id BIGINT NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (customer_id) REFERENCES customer(id)
);