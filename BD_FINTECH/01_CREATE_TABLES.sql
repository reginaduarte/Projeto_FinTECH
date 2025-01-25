-- Banco de dados do projeto FinTECH
USE BD_FINTECH
GO

-- TABELA USUARIO --
CREATE TABLE USUARIO (
    ID_USUARIO INT NOT NULL IDENTITY(1,1),
    NOME VARCHAR(100) NOT NULL,
    CPF VARCHAR(11) NOT NULL UNIQUE,
    EMAIL VARCHAR(60) NOT NULL UNIQUE,
    SENHA VARCHAR(50) NOT NULL,
    TIPO_USUARIO SMALLINT NOT NULL,
    DATA_CRIACAO DATETIME NOT NULL,
    PRIMARY KEY(ID_USUARIO),
    CHECK(LEN(CPF) = 11),
    CHECK(TIPO_USUARIO = 1 OR TIPO_USUARIO = 2) -- 1: CLIENTE, 2: ADM
)
GO

-- TABELA ADMINISTRADOR --
CREATE TABLE ADMINISTRADOR (
	ID_USUARIO INT,
	ID_ADMINISTRADOR INT NOT NULL IDENTITY(1,1),
	NOME VARCHAR(100) NOT NULL,
	SENHA VARCHAR(80) NOT NULL,
	EMAIL VARCHAR(80) NOT NULL,
	PRIMARY KEY(ID_ADMINISTRADOR),
	FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO)
)
GO



-- TABELA CONTA --
CREATE TABLE CONTA (
    ID_USUARIO INT NOT NULL,
    ID_CONTA INT NOT NULL IDENTITY(1,1),
    NUMERO_CONTA INT NOT NULL UNIQUE,
    AGENCIA INT NOT NULL,
    TIPO_CONTA SMALLINT NOT NULL,
    SALDO DECIMAL(10,2) NOT NULL,
    DATA_ABERTURA DATETIME,
    PRIMARY KEY(ID_CONTA),
    FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO),
    CHECK(SALDO >= 0),
    CHECK(TIPO_CONTA = 1 OR TIPO_CONTA = 2) -- 1: SIMPLES, 2: PREMIUM
)
GO


-- TABELA TRANSACAO --
CREATE TABLE TRANSACAO (
    ID_TRANSACAO INT NOT NULL IDENTITY(1,1),
    TIPO_TRANSACAO SMALLINT NOT NULL,
    VALOR DECIMAL NOT NULL,
    DATA_TRANSACAO DATETIME NOT NULL,
    DESCRICAO VARCHAR(100) NOT NULL,
    ID_CONTA INT,
    PRIMARY KEY(ID_TRANSACAO),
    FOREIGN KEY(ID_CONTA) REFERENCES CONTA(ID_CONTA),
    CHECK(VALOR > 0),
    CHECK(TIPO_TRANSACAO BETWEEN 0 AND 4) -- 0:TRANSFERÊNCIA SUJEITA A TARIFAS , 1:SAQUE , 2:DEPÓSITO , 3:PIX , 4:BOLETO
)
GO


-- TABELA TARIFA --
CREATE TABLE TARIFA (
    ID_TARIFA INT NOT NULL IDENTITY(1,1),
    ID_TRANSACAO INT NOT NULL,
    VALOR_TARIFA DECIMAL,
    DATA_TARIFA DATETIME NOT NULL,
    PRIMARY KEY(ID_TARIFA),
    FOREIGN KEY(ID_TRANSACAO) REFERENCES TRANSACAO(ID_TRANSACAO)
)
GO

-- TABELA BOLETO --
CREATE TABLE BOLETO (
    ID_BOLETO INT NOT NULL IDENTITY(1,1),
    CODIGO_BOLETO VARCHAR(100) UNIQUE NOT NULL,
    DATA_VENCIMENTO DATETIME NOT NULL,
    ID_TRANSACAO INT NOT NULL,
    PRIMARY KEY(ID_BOLETO),
    FOREIGN KEY (ID_TRANSACAO) REFERENCES TRANSACAO(ID_TRANSACAO)
)
GO

-- TABELA PIX --
CREATE TABLE PIX (
    ID_PIX INT NOT NULL IDENTITY(1,1),
    CHAVE_PIX VARCHAR(100) NOT NULL,
    TIPO_CHAVE SMALLINT NOT NULL, -- 1: CPF, 2: CNPJ, 3: EMAIL, 4: TELEFONE, 5: CHAVE ALEATÓRIA
    DATA_CRIACAO DATETIME NOT NULL,
    ID_TRANSACAO INT NOT NULL,
    PRIMARY KEY(ID_PIX),
    FOREIGN KEY(ID_TRANSACAO) REFERENCES TRANSACAO(ID_TRANSACAO)
)
GO


-- TABELA INVESTIMENTO --
CREATE TABLE INVESTIMENTO (
    ID_INVESTIMENTO INT NOT NULL IDENTITY(1,1),
    NOME_INVESTIMENTO VARCHAR(100) NOT NULL,
    RENDIMENTO DECIMAL NOT NULL,
    PRIMARY KEY(ID_INVESTIMENTO),
    CHECK (RENDIMENTO > 0)
)
GO

-- TABELA APLICACAO --
CREATE TABLE APLICACAO (
    ID_APLICACAO INT NOT NULL IDENTITY(1,1),
    VALOR_APLICACAO DECIMAL NOT NULL,
    DATA_APLICACAO DATETIME NOT NULL,
    DATA_VENCIMENTO DATETIME NOT NULL,
    ID_INVESTIMENTO INT NOT NULL,
    ID_CONTA INT NOT NULL,
    PRIMARY KEY(ID_APLICACAO),
    CHECK (VALOR_APLICACAO > 0),
    FOREIGN KEY (ID_INVESTIMENTO) REFERENCES INVESTIMENTO(ID_INVESTIMENTO),
    FOREIGN KEY (ID_CONTA) REFERENCES CONTA(ID_CONTA)
)
GO

-- TABELA ENDEREÇO --

CREATE TABLE ENDERECO (
    ID_ENDERECO INT NOT NULL IDENTITY(1,1),
    ID_USUARIO INT NOT NULL,
    CEP VARCHAR(8) NOT NULL UNIQUE,
    LOGRADOURO VARCHAR(100) NOT NULL,
    NUMERO VARCHAR(10) NOT NULL,
	BAIRRO VARCHAR(60) NOT NULL,
    CIDADE VARCHAR(100) NOT NULL,
	ESTADO VARCHAR(60) NOT NULL,
	UF VARCHAR(2) NOT NULL,
    PRIMARY KEY(ID_ENDERECO),
    CHECK(LEN(CEP) = 8),
	FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO)
)
GO

