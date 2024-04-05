ALTER TABLE congregation
ADD COLUMN address_id BIGINT,
ADD FOREIGN KEY (address_id) REFERENCES address(id);