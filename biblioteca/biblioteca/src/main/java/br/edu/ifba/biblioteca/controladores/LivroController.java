package br.edu.ifba.biblioteca.controladores;


import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.biblioteca.dtos.LivroDTO;
import br.edu.ifba.biblioteca.dtos.LivroForm;
import br.edu.ifba.biblioteca.servicos.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

	private LivroService livroService;

	public LivroController(LivroService livroService) {
		super();
		this.livroService = livroService;
	}
	
	@PostMapping
	public ResponseEntity<LivroForm> cadastrar(@RequestBody @Valid LivroForm livroForm, UriComponentsBuilder builder) {
		LivroForm form = this.livroService.cadastrar(livroForm);
		URI uri = builder.path("/livros/{id}").buildAndExpand(form.id()).toUri();
		return ResponseEntity.ok().location(uri).body(form);
	}
	
	@GetMapping
	public Page<LivroDTO> listar(Pageable pageable) {
		return this.livroService.listar(pageable);
	}
	
	@GetMapping("{Id}")
	public LivroDTO listar(@PathVariable Long Id) {
		return this.livroService.listar(Id);
	}
	
	@PutMapping("{Id}")
	public LivroForm atualizar(@PathVariable Long Id, @RequestBody @Valid LivroForm form) {
		return this.livroService.atualizar(Id, form);
	}
	
	@DeleteMapping("{Id}")
	public ResponseEntity<LivroDTO> deletar(@PathVariable Long Id) {
		LivroDTO dto = this.livroService.deletar(Id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping("/search")
	public Page<LivroDTO> buscarPorTitulo(@RequestParam String titulo, Pageable pageable) {
		return this.livroService.buscarPorTitulo(titulo, pageable);
	}

}
