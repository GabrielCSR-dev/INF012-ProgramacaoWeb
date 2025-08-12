package br.edu.ifba.biblioteca.controladores;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.biblioteca.dtos.AutorDTO;
import br.edu.ifba.biblioteca.dtos.AutorForm;
import br.edu.ifba.biblioteca.dtos.LivroDTO;
import br.edu.ifba.biblioteca.servicos.AutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

	private AutorService autorService;

	public AutorController(AutorService autorService) {
		super();
		this.autorService = autorService;
	}
	
	@PostMapping
	public ResponseEntity<AutorForm> cadastrar(@RequestBody @Valid AutorForm autorForm, UriComponentsBuilder uriBulder) {
		AutorForm form = this.autorService.cadastrar(autorForm);
		URI uri = uriBulder.path("/autores/{id}").buildAndExpand(form.id()).toUri();
		return ResponseEntity.ok().location(uri).body(form);
	}
	
	@GetMapping
	public List<AutorDTO> listar() {
		return this.autorService.listar();
	}
	
	@GetMapping("{Id}")
	public AutorDTO listar(@PathVariable Long Id) {
		return this.autorService.listarPorId(Id);
	}
	
	@PutMapping("{Id}")
	public AutorForm atualizar(@PathVariable Long Id, @RequestBody @Valid AutorForm autorForm) {
		return this.autorService.atualizar(Id, autorForm);
	}
	
	@DeleteMapping("{Id}")
	public ResponseEntity<AutorDTO> deletar(@PathVariable Long Id) {
		AutorDTO dto = this.autorService.deletar(Id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping("{Id}/livros")
	public List<LivroDTO> listarLivros(@PathVariable Long Id) {
		return this.autorService.listarLivros(Id);
	}
}
