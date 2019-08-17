package br.com.projeto.apirestcloud.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.projeto.apirestcloud.enums.Sexo;
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
public class DependenteDTO {

	private String nome;
	private Sexo sexo;
	private Integer idade;
	private Funcionario funcionario;
	
	public DependenteDTO(Dependente dependente) {
		BeanUtils.copyProperties(dependente, this);
	}
	
	public static List<DependenteDTO> converter(List<Dependente> dependentes) {
		return dependentes.stream().map(DependenteDTO::new).collect(Collectors.toList());
	}
}
