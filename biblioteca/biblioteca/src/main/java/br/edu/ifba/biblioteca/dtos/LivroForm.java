package br.edu.ifba.biblioteca.dtos;

import br.edu.ifba.biblioteca.entidades.Livro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Valid
public record LivroForm(Long id, @NotNull @Size(min = 2, max = 150) String titulo, @Size(min = 13, max = 13) String isbn,
		@Min(1500) @Max(2025) Integer anoPublicacao, @NotNull AutorForm autor) {

	public LivroForm(Livro livro) {
		this(livro.getId(), livro.getTitulo(), livro.getIsbn(), livro.getAnoPublicacao(), 
				new AutorForm(livro.getAutor()));
	}
}
