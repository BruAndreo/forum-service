CREATE TABLE usuario(
    id SERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO usuario VALUES (1, 'Bruno', 'bruno@email.com');
