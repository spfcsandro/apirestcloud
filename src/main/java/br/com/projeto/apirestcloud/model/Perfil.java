package br.com.projeto.apirestcloud.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_perfil")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Perfil extends AbstractEntity implements GrantedAuthority{
 
 	private static final long serialVersionUID = 1L;
 	
 	private String nome;

	@Override
	public String getAuthority() {
		return this.nome;
	}

	
}
