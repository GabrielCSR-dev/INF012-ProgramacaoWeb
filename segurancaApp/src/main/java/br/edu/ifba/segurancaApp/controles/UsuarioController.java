package br.edu.ifba.segurancaApp.controles;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.segurancaApp.dtos.UsuarioDTO;
import br.edu.ifba.segurancaApp.dtos.UsuarioForm;
import br.edu.ifba.segurancaApp.servicos.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private  UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioForm usuarioForm) {
		UsuarioDTO usuarioDTO = usuarioService.salvar(usuarioForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
        }
}
