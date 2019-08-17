package br.com.projeto.apirestcloud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDTO {

	private String token;
	private String tipo;
}
