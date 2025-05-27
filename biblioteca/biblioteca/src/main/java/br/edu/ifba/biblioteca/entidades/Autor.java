package br.edu.ifba.biblioteca.entidades;

import br.edu.ifba.biblioteca.dtos.AutorForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Autores")
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 100)
	private String nome;
	@NotNull
	@Email
	private String email;
	@Size(max = 50)
	private String nacionalidade;
	
	public Autor() {
		super();
	}
	public Autor(Long id, @NotNull @Size(min = 2, max = 100) String nome, @NotNull @Email String email,
			@Size(max = 50) String nacionalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.nacionalidade = nacionalidade;
	}
	public Autor(AutorForm form) {
		this.id = form.id();
		this.nome = form.nome();
		this.email = form.email();
		this.nacionalidade = form.nacionalidade();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
}
