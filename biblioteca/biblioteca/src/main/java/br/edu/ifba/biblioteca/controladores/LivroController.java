package br.edu.ifba.biblioteca.controladores;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.biblioteca.servicos.LivroService;

@RestController
public class LivroController {

	private LivroService livroService;

	public LivroController(LivroService livroService) {
		super();
		this.livroService = livroService;
	}
	
	
}
