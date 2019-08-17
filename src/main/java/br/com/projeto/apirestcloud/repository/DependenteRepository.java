package br.com.projeto.apirestcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.apirestcloud.model.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Long> {
	List<Dependente> findByFuncionarioId(Long idFuncionario);
}
