CREATE TABLE curso(
    id SERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO curso VALUES (1, 'Kotlin', 'Programação');
