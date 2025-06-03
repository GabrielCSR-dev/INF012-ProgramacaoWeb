package br.edu.ifba.biblioteca.servicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifba.biblioteca.dtos.LivroDTO;
import br.edu.ifba.biblioteca.dtos.LivroForm;
import br.edu.ifba.biblioteca.entidades.Autor;
import br.edu.ifba.biblioteca.entidades.Livro;
import br.edu.ifba.biblioteca.repositorios.AutorRepository;
import br.edu.ifba.biblioteca.repositorios.LivroRepository;

@Service
public class LivroService {

	private LivroRepository livroRepository;
	private AutorRepository autorRepository;

	public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
		super();
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
	}
	
	public LivroForm cadastrar(LivroForm form) {
		Autor autor = this.autorRepository.findById(form.autor().id()).get();
		Livro livro = this.livroRepository.save(new Livro(form));
		livro.setAutor(autor);
		return new LivroForm(livro);
	}
	
	public Page<LivroDTO> listar(Pageable pageable) {
		return this.livroRepository.findAll(pageable).map(LivroDTO::new);
	}
	public LivroDTO listar(Long Id) {
		return new LivroDTO(this.livroRepository.findById(Id).get());
	}
	
	public LivroForm atualizar(Long Id, LivroForm form) {
		Livro livro = this.livroRepository.findById(Id).get();
		Autor autor = this.autorRepository.findById(form.autor().id()).get();
		
		livro.setTitulo(form.titulo());
		livro.setIsbn(form.isbn());
		livro.setAnoPublicacao(form.anoPublicacao());
		livro.setAutor(autor);
		this.livroRepository.save(livro);
		
		return new LivroForm(livro);
	}
	
	public LivroDTO deletar(Long Id) {
		Livro livro = this.livroRepository.findById(Id).get();
		this.livroRepository.deleteById(Id);
		return new LivroDTO(livro);
	}
	
	public Page<LivroDTO> buscarPorTitulo(String titulo, Pageable pageable) {
		return this.livroRepository.findByTituloStartsWith(titulo, pageable).map(LivroDTO::new);
	}
}
