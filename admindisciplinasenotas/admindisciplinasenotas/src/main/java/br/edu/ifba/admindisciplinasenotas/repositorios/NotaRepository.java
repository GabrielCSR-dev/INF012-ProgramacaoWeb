package br.edu.ifba.admindisciplinasenotas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.admindisciplinasenotas.entidades.Disciplina;
import br.edu.ifba.admindisciplinasenotas.entidades.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{
	public List<Nota> getByDisciplina(Disciplina disciplina);
	public Nota getById(Long ID); 
}
