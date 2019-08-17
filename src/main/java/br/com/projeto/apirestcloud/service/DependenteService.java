package br.com.projeto.apirestcloud.service;

import java.util.List;

import br.com.projeto.apirestcloud.model.Dependente;
import br.com.projeto.apirestcloud.model.dto.DependenteDTO;

public interface DependenteService {

	Dependente salvar(Long id, DependenteDTO dependenteDTO);
	Dependente atualizar(Long idFuncionario,Long idDependente, DependenteDTO dependenteDTO);
	List<Dependente> findByFuncionarioId(Long idFuncionario);
	void deletar(Long idFuncionario, Long idDependente);
}
