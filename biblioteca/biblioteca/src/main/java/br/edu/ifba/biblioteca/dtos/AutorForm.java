package br.edu.ifba.biblioteca.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AutorForm(Long id, @NotNull @Size(min = 2, max = 100) String nome, @NotNull @Email String email,
		@Size(max = 50) String nacionalidade) {

}
