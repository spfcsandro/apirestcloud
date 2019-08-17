package br.com.projeto.apirestcloud.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

	private String email;
	private String senha;
}
