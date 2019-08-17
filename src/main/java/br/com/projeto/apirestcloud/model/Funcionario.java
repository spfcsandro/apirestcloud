package br.com.projeto.apirestcloud.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import br.com.projeto.apirestcloud.model.dto.FuncionarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Funcionario")
@Table(name = "tb_funcionario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Funcionario extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String matricula;
	@NotBlank(message = "Por favor digite um nome válido!")
	@NotNull(message = "Por favor digite um nome válido!")
	@NotEmpty(message = "Por favor digite um nome válido!")
	private String nome;
	@Column(unique = true)
	private String cpf;
	private String endereco;
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private List<Dependente> dependentes;
	
	public Funcionario(FuncionarioDTO funcionario) {
		BeanUtils.copyProperties(funcionario, this);
	}
	
	public void converter(FuncionarioDTO funcionarioDTO) {
		 BeanUtils.copyProperties(funcionarioDTO, this);
	}
	
	public static List<Funcionario> converter(List<FuncionarioDTO> funcionarios) {
		return funcionarios.stream().map(Funcionario::new).collect(Collectors.toList());
	}
}
