package br.edu.ifba.biblioteca.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.biblioteca.dtos.AutorDTO;
import br.edu.ifba.biblioteca.dtos.AutorForm;
import br.edu.ifba.biblioteca.entidades.Autor;
import br.edu.ifba.biblioteca.repositorios.AutorRepository;

@Service
public class AutorService {

	private AutorRepository autorRepository;

	public AutorService(AutorRepository autorRepository) {
		super();
		this.autorRepository = autorRepository;
	}
	
	public AutorForm cadastrar(AutorForm autorForm) {
		Autor autor = this.autorRepository.save(new Autor(autorForm));
		return new AutorForm(autor.getId(), autor.getNome(), autor.getEmail(), autor.getNacionalidade());
	}
	
	public List<AutorDTO> listar() {
		return this.autorRepository.findAll().stream().map(AutorDTO::new).toList();
	}
	
	public AutorDTO listarPorId(Long Id) {
		return new AutorDTO(this.autorRepository.findById(Id).get());
	}
}
