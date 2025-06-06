package br.edu.ifba.segurancaApp.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.segurancaApp.dtos.DadosAutenticacao;
import br.edu.ifba.segurancaApp.entidades.Usuario;
import br.edu.ifba.segurancaApp.servicos.JWTokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	private AuthenticationManager manager;
	private JWTokenService tokenService;
	
	public AutenticacaoController(AuthenticationManager manager, JWTokenService tokenService) {
		this.manager = manager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<String> efetuarLogin (@RequestBody DadosAutenticacao dados) {
		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication = manager.authenticate(token);
		return ResponseEntity.ok(this.tokenService.gerarToken((Usuario)authentication.getPrincipal()));
	}
}
