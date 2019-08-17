package br.com.projeto.apirestcloud.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.apirestcloud.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	@Query("select max(f.id) from Funcionario f")
	public Optional<Long> findMaxId();
	Page<Funcionario> findByNomeContainingIgnoreCase(String nome, Pageable paginacao);
	
	
}
