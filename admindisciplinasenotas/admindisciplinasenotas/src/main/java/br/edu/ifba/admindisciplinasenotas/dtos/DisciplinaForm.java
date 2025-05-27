package br.edu.ifba.admindisciplinasenotas.dtos;

import br.edu.ifba.admindisciplinasenotas.entidades.Disciplina;

public record DisciplinaForm(Long ID, String nome, String codigo) {

	public DisciplinaForm(Disciplina disciplina) {
		this(disciplina.getID(), disciplina.getNome(), disciplina.getCodigo());
	}
}
