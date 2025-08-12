package br.edu.ifba.biblioteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.biblioteca.entidades.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
