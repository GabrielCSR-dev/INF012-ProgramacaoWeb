package br.edu.ifba.biblioteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.biblioteca.entidades.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
