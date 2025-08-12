package br.edu.ifba.biblioteca.dtos;

import br.edu.ifba.biblioteca.entidades.Autor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Valid
public record AutorForm(Long id, @NotNull @Size(min = 2, max = 100) String nome, @NotNull @Email String email,
		@Size(max = 50) String nacionalidade) {

	public AutorForm(Autor autor) {
		this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getNacionalidade());
	}
}
