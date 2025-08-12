package br.edu.ifba.biblioteca.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.biblioteca.entidades.Autor;
import br.edu.ifba.biblioteca.entidades.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	public List<Livro> getByAutor(Autor autor);
	public Page<Livro> findByTituloStartsWith(String Titulo, Pageable pageable);
}
