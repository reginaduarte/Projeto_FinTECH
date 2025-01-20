
USE BD_FINTECH
GO

-- Inserindo dados na tabela USUARIO
INSERT INTO USUARIO (NOME, CPF, EMAIL, SENHA, TIPO_USUARIO, DATA_CRIACAO) VALUES
('Matheus Santos Ferreira', '85538353087', 'matheusferreira@gmail.com', 'matheus1234','2', GETDATE()),
('Maria Souza Barbosa', '52303220041', 'mariasouza@gmail.com', 'maria1234', '2', GETDATE()),
('Bianca Lima da Silva', '77525442053', 'biancalima@gmail.com', 'bianca@1234', '1', GETDATE())
GO

-- Inserindo dados na tabela ADMINISTRADOR
INSERT INTO ADMINISTRADOR (NOME, SENHA, EMAIL) VALUES
('João Paulo Almeida', 'joao@1234', 'joao.almeida@gmail.com')
GO

-- Inserindo dados na tabela CONTA
INSERT INTO CONTA (ID_USUARIO, NUMERO_CONTA, AGENCIA, TIPO_CONTA, SALDO, DATA_ABERTURA) VALUES
(1, 12345677, 1221, 1, 2500.90, '2024-12-10'),
(2, 28948238, 4433, 2, 1000.50, '2024-12-16')
GO



-- Inserindo dados na tabela TARIFA
INSERT INTO TARIFA (ID_TRANSACAO, VALOR_TARIFA, DATA_TARIFA) VALUES 
(1, 4.00, '2025-01-01'),
(3, 3.00, '2024-12-20')
GO

-- Inserindo dados na tabela BOLETO
INSERT INTO BOLETO (ID_TRANSACAO, CODIGO_BOLETO, DATA_VENCIMENTO) VALUES 
(3, '294948906830748006423700', '2025-01-10')
GO

-- Inserindo dados na tabela TRANSACAO
INSERT INTO TRANSACAO (TIPO_TRANSACAO, VALOR, DATA_TRANSACAO, DESCRICAO, ID_CONTA_ORIGEM, ID_CONTA_DESTINO) VALUES 
(0, 150.00, '2025-01-01', 'Transferência para Maria', 1, 2),
(2, 200.00, '2024-12-20', 'Depósito na conta de Matheus', 2, 1),
(4, 300.00, GETDATE(), 'Pagamento do boleto', 1, 2),
(3, 50.00, GETDATE(), 'Pix para Maria', 1, 2)
GO


-- Inserindo dados na tabela PIX
INSERT INTO PIX (ID_TRANSACAO, CHAVE_PIX, TIPO_CHAVE, DATA_CRIACAO) VALUES
(6, '26932909022', 1, '2025-01-03'),
(2, '11912345678', 1,'2025-01-05'),
(4, 'mariasouza@gmail.com', 3, '2025-01-02'),
(5, 'vM(hdfUF!nkGmv}S6ow', 5, '2025-01-03')
GO

-- Inserindo dados na tabela INVESTIMENTO
INSERT INTO INVESTIMENTO (NOME_INVESTIMENTO, RENDIMENTO) VALUES
('Tesouro Direto', 5.00)
GO

-- Inserindo dados na tabela APLICACAO
INSERT INTO APLICACAO (VALOR_APLICACAO, DATA_APLICACAO, DATA_VENCIMENTO, ID_INVESTIMENTO, ID_CONTA) VALUES
(400.00, '2024-12-22', '2025-02-21', 1, 1)
GO

-- Inserindo dados na tabela ENDERECO
INSERT INTO ENDERECO (ID_USUARIO, CEP, LOGRADOURO, BAIRRO, NUMERO, CIDADE, ESTADO, UF) VALUES
('1', '15945970', 'Rua Guia Lopes', 'Centro', '225', 'Cajamar', 'São Paulo', 'SP'),
('2', '32920971', 'Rua Primeiro de Maio', 'Centro', '13', 'São Joaquim de Bicas', 'Minas Gerais', 'MG'),
('3', '86155970', 'Rua Emílio Solcia', 'Centro', '40', 'Esperança do Norte', 'Paraná', 'PR')
GO