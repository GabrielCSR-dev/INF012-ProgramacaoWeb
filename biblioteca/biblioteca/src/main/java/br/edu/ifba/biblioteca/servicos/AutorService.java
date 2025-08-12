package br.edu.ifba.biblioteca.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.biblioteca.dtos.AutorDTO;
import br.edu.ifba.biblioteca.dtos.AutorForm;
import br.edu.ifba.biblioteca.dtos.LivroDTO;
import br.edu.ifba.biblioteca.entidades.Autor;
import br.edu.ifba.biblioteca.repositorios.AutorRepository;
import br.edu.ifba.biblioteca.repositorios.LivroRepository;

@Service
public class AutorService {

	private AutorRepository autorRepository;
	private LivroRepository livroRepository;

	public AutorService(AutorRepository autorRepository, LivroRepository livroRepository) {
		super();
		this.autorRepository = autorRepository;
		this.livroRepository = livroRepository;
	}
	
	public AutorForm cadastrar(AutorForm autorForm) {
		Autor autor = this.autorRepository.save(new Autor(autorForm));
		return new AutorForm(autor);
	}
	
	public List<AutorDTO> listar() {
		return this.autorRepository.findAll().stream().map(AutorDTO::new).toList();
	}
	
	public AutorDTO listarPorId(Long Id) {
		return new AutorDTO(this.autorRepository.findById(Id).get());
	}
	
	public AutorForm atualizar(Long Id, AutorForm form) {
		Autor autor = this.autorRepository.findById(Id).get();
		autor.setNome(form.nome());
		autor.setEmail(form.email());
		autor.setNacionalidade(form.nacionalidade());
		this.autorRepository.save(autor);
		
		return new AutorForm(autor.getId(), autor.getNome(), autor.getEmail(), autor.getNacionalidade());
	}
	
	public AutorDTO deletar(Long Id) {
		Autor autor = this.autorRepository.findById(Id).get();
		this.autorRepository.deleteById(Id);
		return new AutorDTO(autor);
	}
	
	public List<LivroDTO> listarLivros(Long Id) {
		Autor autor = this.autorRepository.findById(Id).get();
		return this.livroRepository.getByAutor(autor).stream().map(LivroDTO::new).toList();
	}
}
