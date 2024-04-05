-- ******************************************************
-- Versão 2: Adicionar tabela de congregação
-- ******************************************************

-- Criação da tabela de congregação
CREATE TABLE congregation (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Criação da tabela de junção para a relação muitos-para-muitos entre pessoa e congregação
CREATE TABLE person_congregation (
    person_id INT,
    congregation_id INT,
    PRIMARY KEY (person_id, congregation_id),
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (congregation_id) REFERENCES congregation(id)
);