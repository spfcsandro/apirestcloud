package br.com.projeto.apirestcloud.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.projeto.apirestcloud.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${apirestaws.jwt.secret}")
	private String secret;
	
	@Value("${apirestaws.jwt.expiration}")
	private String expiration;

	public String gerarToken(Authentication authentication) {
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		
		return Jwts.builder()
			   .setIssuer("APIRESTAWS") // Quem foi a API que gerou esse Token.
			   .setSubject(usuarioLogado.getId().toString())
			   .setIssuedAt(new Date())
			   .setExpiration(new Date(new Date().getTime() + Long.parseLong(expiration))) //30 min em Milissegundos
			   .signWith(SignatureAlgorithm.HS256, secret)
			   .compact();
	}

	public boolean isTokenValido(String token) {
		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
			Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
			return Long.parseLong(claims.getSubject());
	}

	
}
