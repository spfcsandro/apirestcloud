package br.com.projeto.apirestcloud.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.apirestcloud.model.dto.LoginDTO;
import br.com.projeto.apirestcloud.model.dto.TokenDTO;
import br.com.projeto.apirestcloud.security.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Autenticação Endpoint", tags = {"Autenticacção-Endpoint"},  description = "Autenticação")
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@ApiOperation(value = "Autenticar")
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO loginDTO){
		UsernamePasswordAuthenticationToken dadosLogin = 
				new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication); 
			return ResponseEntity.ok(new TokenDTO(token,"Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
