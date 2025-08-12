package br.edu.ifba.segurancaApp.servicos;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.edu.ifba.segurancaApp.entidades.Usuario;

@Service
public class JWTokenService {

	public String gerarToken(Usuario usuario) {
		var algoritmo = Algorithm.HMAC256("12345678");
		return JWT.create()
				.withIssuer("Aula de PWeb")
				.withSubject(usuario.getLogin())
				.withExpiresAt(dataExpiracao())
				.sign(algoritmo);
	}
	
	public Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3:00"));
	}
}
