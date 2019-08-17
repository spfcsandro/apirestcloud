delete from tb_usuario;
delete from tb_dependente;
delete from tb_funcionario;

ALTER SEQUENCE tb_usuario_id_seq RESTART WITH 1;
ALTER SEQUENCE tb_dependente_id_seq RESTART WITH 1;
ALTER SEQUENCE tb_funcionario_id_seq RESTART WITH 1;

INSERT INTO tb_funcionario (id, cpf, endereco, matricula, nome) VALUES
  (1, '00557907551', 'Rua da Alvorada', 'FUNC1', 'Sandro Andrade'),
  (2, '43711688098', 'Rua da Alvorada', 'FUNC2', 'Marina Teles');
  
INSERT INTO tb_dependente (id, idade, nome, sexo, funcionario_id) VALUES
(1, 10, 'Sandro Filho', 'MASCULINO', 1),
(2, 10, 'Samia Rafaela', 'FEMININO', 1),
(3, 10, 'Marisa Teles', 'FEMININO', 2);

INSERT INTO tb_usuario (id, email, nome, senha) VALUES
  (1, 'spfcsandro@gmail.com', 'Sandro Roberto', '$2a$10$Cou8TblybwJoT2Xi1aA49OwgMUmXIQufJtQf3Be..LOIjllQvobm6'),
  (2, 'marina@gmail.com', 'Marina Souza', '$2a$10$Cou8TblybwJoT2Xi1aA49OwgMUmXIQufJtQf3Be..LOIjllQvobm6');
  (3, 'selecao@lccv.ufal.br', 'LCCV', '$2a$10$Cou8TblybwJoT2Xi1aA49OwgMUmXIQufJtQf3Be..LOIjllQvobm6');
  (4, 'teste@teste.com.br', 'TESTE', '$2a$10$Cou8TblybwJoT2Xi1aA49OwgMUmXIQufJtQf3Be..LOIjllQvobm6');

ALTER SEQUENCE tb_usuario_id_seq RESTART WITH 5;
ALTER SEQUENCE tb_dependente_id_seq RESTART WITH 4;
ALTER SEQUENCE tb_funcionario_id_seq RESTART WITH 3; 