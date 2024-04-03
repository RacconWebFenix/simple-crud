CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE,
    birth_date DATE,
    email VARCHAR(255) UNIQUE
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    person_id INT,
    CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES person(id)
);


CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    person_id INT,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postal_code VARCHAR(10),
    FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE phone_number (
    id SERIAL PRIMARY KEY,
    person_id INT,
    phone VARCHAR(20),
    FOREIGN KEY (person_id) REFERENCES person(id)
);
