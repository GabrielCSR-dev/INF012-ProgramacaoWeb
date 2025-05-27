package br.edu.ifba.admindisciplinasenotas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.admindisciplinasenotas.entidades.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{
	public Disciplina getById(Long ID);
}
