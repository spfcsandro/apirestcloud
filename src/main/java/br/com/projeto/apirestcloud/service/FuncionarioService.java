package br.com.projeto.apirestcloud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.projeto.apirestcloud.model.Funcionario;
import br.com.projeto.apirestcloud.model.dto.FuncionarioDTO;

public interface FuncionarioService {

	Funcionario salvar(FuncionarioDTO funcionarioDTO);
	Funcionario atualizar(Long id, FuncionarioDTO funcionarioDTO);
	public void existeFuncionario(Long id);
	public Funcionario getOne(Long id);
	Page<Funcionario> findAll(Pageable paginacao);
	Page<Funcionario> findByNome(String nome, Pageable paginacao);
	void deletar(Long id);

}
