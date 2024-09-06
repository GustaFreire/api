INSERT INTO usuarios 
VALUES (1, 'freiregustavo23@hotmail.com', '$2a$12$1EUZu2q/obQAFcxSAEfuJuYDgptblsgEOj11AOZ.SRLVwvi2Jiare');

INSERT INTO pacientes
    (nome, email, telefone, cpf, ativo, logradouro, bairro, cep, cidade, uf)
VALUES 
    ('Arthur Freire Pereira', 'artfdatrk@gmail.com','11940028922', '12345678910', 1, 'Rua das Primas', 'Cidade Santa', '06608350', 'Barueri', 'SP'),
    ('Gustavo Santos Duarte da Silva de Andrade', 'freiregustavo23@hotmail.com','11951041900', '37693414819', 1, 'Rua Pinto', 'Jaca Doce', '06609320', 'Jandira', 'BA'),
    ('Ana Maria', 'maria_ana@cobol.com','11959068982', '90730747468', 1, 'Rua Áurea Vieira da Silva', 'Jardim Patriarca', '06602345', 'Salgueiro', 'PE'),
    ('Edson Costa De Oliveira', 'edonacostalarga@outlook.com','11987790723', '15551685829', 1, 'Rua Boa', 'Tijuca', '04512050', 'Niterói', 'RJ');

INSERT INTO medicos
    (nome, email, telefone, crm, ativo, especialidade, logradouro, bairro, cep, cidade, uf)
VALUES 
    ('Astolfo Cadelo', 'astolfin.delas@voll.med','11987790723', '232425', 1, 'ORTOPEDIA', 'Rua das Primas', 'Cidade Santa', '06608350', 'Barueri', 'SP'),
    ('Bianca Silva', 'bianca.silva@medicos.com','1195551234', '987654', 1, 'CARDIOLOGIA','Rua Pinto', 'Jaca Doce', '06609320', 'Jandira', 'BA'),
    ('Carlos Mendes', 'carlos.mendes@clinicamed.com','1193339876', '543210', 1, 'ORTOPEDIA','Rua Áurea Vieira da Silva', 'Jardim Patriarca', '06602345', 'Salgueiro', 'PE'),
    ('Diana Rodrigues', 'diana.rodrigues@clinica.com','1194445678', '123456', 1, 'PEDIATRIA','Rua Boa', 'Tijuca', '04512050', 'Niterói', 'RJ');