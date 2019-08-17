/**
 * 
 */
package br.com.projeto.apirestcloud.model.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.projeto.apirestcloud.model.Dependente;
import br.com.projeto.apirestcloud.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {

	private Long id;
	@NotNull @NotEmpty @NotBlank
	private String nome;
	@Column(length = 11)
	@CPF(message = "CPF inválido")
	private String cpf;
	private String endereco;
	private List<Dependente> dependentes;
	
	public FuncionarioDTO(Funcionario funcionario) {
		BeanUtils.copyProperties(funcionario, this);
	}
	
	public static Page<FuncionarioDTO> converter(Page<Funcionario> funcionarios) {
		return funcionarios.map(FuncionarioDTO::new);
	}
	
	/*
	 * public static List<FuncionarioDTO> converter(List<Funcionario> funcionarios)
	 * { return
	 * funcionarios.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
	 * }
	 */
}
