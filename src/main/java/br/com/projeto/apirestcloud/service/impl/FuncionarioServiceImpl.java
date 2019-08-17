package br.com.projeto.apirestcloud.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.apirestcloud.exception.NotFoundException;
import br.com.projeto.apirestcloud.model.Funcionario;
import br.com.projeto.apirestcloud.model.dto.FuncionarioDTO;
import br.com.projeto.apirestcloud.repository.FuncionarioRepository;
import br.com.projeto.apirestcloud.service.FuncionarioService;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Page<Funcionario> findAll(Pageable paginacao) {
		return funcionarioRepository.findAll(paginacao);
	}
	
	public Optional<Funcionario> findById(Long id) {
		return funcionarioRepository.findById(id);
	}
	
	public Funcionario getOne(Long id) {
		return funcionarioRepository.getOne(id);
	}
	
	@Override
	public Funcionario salvar(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = Funcionario.builder()
		.matricula(geraMatricula())
		.nome(funcionarioDTO.getNome())
		.cpf(funcionarioDTO.getCpf().replaceAll("\\D", ""))
		.endereco(funcionarioDTO.getEndereco())
		.build();
		
		return funcionarioRepository.save(funcionario);
	}

	private String geraMatricula() {
		Long maxId = funcionarioRepository.findMaxId().orElse(1L);
		return "FUNC".concat(maxId.toString());
	}
	
	@Override
	public Funcionario atualizar(Long id, FuncionarioDTO funcionarioDTO) {
		existeFuncionario(id);
		Funcionario funcionario = funcionarioRepository.getOne(id);
		funcionario.converter(funcionarioDTO);
		
		return funcionario;
	}
	
	public void deletar(Long id) {
		existeFuncionario(id);
	    funcionarioRepository.delete(funcionarioRepository.getOne(id));
	}
	
	public void existeFuncionario(Long id) {
		if(!funcionarioRepository.existsById(id)) {
            throw new NotFoundException("Funcionário não encontrado com o ID:" + id);
        }
	}

	@Override
	public Page<Funcionario> findByNome(String nome, Pageable paginacao) {
		return funcionarioRepository.findByNomeContainingIgnoreCase(nome, paginacao);
	}

}
