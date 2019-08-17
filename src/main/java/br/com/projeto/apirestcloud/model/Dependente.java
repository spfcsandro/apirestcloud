package br.com.projeto.apirestcloud.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projeto.apirestcloud.enums.Sexo;
import br.com.projeto.apirestcloud.model.dto.DependenteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Dependente")
@Table(name = "tb_dependente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Dependente extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String nome;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	private Integer idade;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	@JsonIgnore
	private Funcionario funcionario;
	
	public Dependente(DependenteDTO dependente) {
		BeanUtils.copyProperties(dependente, this);
	}
	
	public void converter(DependenteDTO dependenteDTO) {
		 BeanUtils.copyProperties(dependenteDTO, this);
	}
	
	public static List<Dependente> converter(List<DependenteDTO> dependentes) {
		return dependentes.stream().map(Dependente::new).collect(Collectors.toList());
	}
	
}
