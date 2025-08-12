package br.edu.ifba.biblioteca.entidades;

import br.edu.ifba.biblioteca.dtos.LivroForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Livros")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 150)
	private String titulo;
	@Size(min = 13, max = 13)
	private String isbn;
	@Min(value = 1500)
	@Max(value = 2025)
	private Integer anoPublicacao;
	@NotNull
	@ManyToOne
	private Autor autor;

	
	public Livro() {
		super();
	}

	public Livro(Long id, @NotNull @Size(min = 2, max = 150) String titulo, @Size(min = 13, max = 13) String isbn,
			@Min(1500) @Max(2025) Integer anoPublicacao, @NotNull Autor autor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.anoPublicacao = anoPublicacao;
		this.autor = autor;
	}
	
	public Livro(LivroForm form) {
		this.id = form.id();
		this.titulo = form.titulo();
		this.isbn = form.isbn();
		this.anoPublicacao = form.anoPublicacao();
		this.autor = new Autor(form.autor());
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(@NotNull @Size(min = 2, max = 150) @Valid String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(@Size(min = 13, max = 13) @Valid String isbn) {
		this.isbn = isbn;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(@Min(1500) @Max(2025) @Valid Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(@NotNull @Valid Autor autor) {
		this.autor = autor;
	}
	
	
}
