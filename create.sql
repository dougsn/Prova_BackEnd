CREATE TABLE IF NOT EXISTS DENTISTA(ID INT AUTO_INCREMENT PRIMARY KEY, NOME VARCHAR(100) NOT NULL, SOBRENOME VARCHAR(100) NOT NULL, MATRICULA VARCHAR(100) NOT NULL);
CREATE TABLE IF NOT EXISTS PACIENTE(ID INT AUTO_INCREMENT PRIMARY KEY, NOME VARCHAR(100) NOT NULL, SOBRENOME VARCHAR(100) NOT NULL, ENDERECO VARCHAR(250) NOT NULL, DATAALTA DATE NOT NULL)
