CREATE TABLE purchase(
     id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
     customer_id BIGINT NOT NULL,
     price DECIMAL(10, 2) NOT NULL,
     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id),
     FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE purchase_book(
     purchase_id BIGINT NOT NULL,
     book_id BIGINT NOT NULL,
     FOREIGN KEY (purchase_id) REFERENCES purchase(id),
     FOREIGN KEY (book_id) REFERENCES book(id),
     PRIMARY KEY (purchase_id, book_id)
);