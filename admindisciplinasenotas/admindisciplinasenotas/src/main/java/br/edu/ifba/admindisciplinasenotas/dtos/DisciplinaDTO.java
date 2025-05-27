package br.edu.ifba.admindisciplinasenotas.dtos;

import br.edu.ifba.admindisciplinasenotas.entidades.Disciplina;

public record DisciplinaDTO(Long ID, String nome, String codigo) {
	
	public DisciplinaDTO(Disciplina disciplina) {
		this(disciplina.getID(), disciplina.getNome(), disciplina.getCodigo());
	}
}
