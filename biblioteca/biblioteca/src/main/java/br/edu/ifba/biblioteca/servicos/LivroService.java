package br.edu.ifba.biblioteca.servicos;

import org.springframework.stereotype.Service;

import br.edu.ifba.biblioteca.repositorios.LivroRepository;

@Service
public class LivroService {

	private LivroRepository livroRepository;

	public LivroService(LivroRepository livroRepository) {
		super();
		this.livroRepository = livroRepository;
	}
	
	
}
