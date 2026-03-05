CREATE TABLE topicos (

                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         titulo VARCHAR(200) NOT NULL,
                         mensagem TEXT NOT NULL,
                         data_criacao DATETIME NOT NULL,
                         status VARCHAR(50) DEFAULT 'ABERTO',
                         autor VARCHAR(100),
                         curso VARCHAR(100)

);