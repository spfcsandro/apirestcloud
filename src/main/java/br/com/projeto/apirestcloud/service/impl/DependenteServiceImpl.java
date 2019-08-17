package br.com.projeto.apirestcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.apirestcloud.model.Dependente;
import br.com.projeto.apirestcloud.model.Funcionario;
import br.com.projeto.apirestcloud.model.dto.DependenteDTO;
import br.com.projeto.apirestcloud.repository.DependenteRepository;
import br.com.projeto.apirestcloud.service.DependenteService;
import br.com.projeto.apirestcloud.service.FuncionarioService;

@Service
@Transactional
public class DependenteServiceImpl implements DependenteService {

	@Autowired
	private DependenteRepository dependenteRepository;
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	public List<Dependente> findByFuncionarioId(Long id) {
		return dependenteRepository.findByFuncionarioId(id);
	}
	
	public Dependente findById(Long id) {
		return dependenteRepository.getOne(id);
	}
	
	@Override
	public Dependente salvar(Long idFuncionario, DependenteDTO dependenteDTO) {
		funcionarioService.existeFuncionario(idFuncionario);
		Dependente dependente = Dependente.builder()
		.nome(dependenteDTO.getNome())
		.sexo(dependenteDTO.getSexo())
		.idade(dependenteDTO.getIdade())
		.funcionario(funcionarioService.getOne(idFuncionario))
		.build();
		
		return dependenteRepository.saveAndFlush(dependente);
	}

	@Override
	public Dependente atualizar(Long idFuncionario, Long idDependente, DependenteDTO dependenteDTO) {
		funcionarioService.existeFuncionario(idFuncionario);
		Funcionario funcionario = funcionarioService.getOne(idFuncionario);
		Dependente dependente = dependenteRepository.getOne(idDependente);
		dependente.converter(dependenteDTO);
		dependente.setFuncionario(funcionario);
		return dependente;
	}
	
	public void deletar(Long idFuncionario, Long idDependente) {
		funcionarioService.existeFuncionario(idFuncionario);
		dependenteRepository.deleteById(idDependente);
	}
	
}
