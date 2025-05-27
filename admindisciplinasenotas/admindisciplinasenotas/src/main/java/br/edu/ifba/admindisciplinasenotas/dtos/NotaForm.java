package br.edu.ifba.admindisciplinasenotas.dtos;

import br.edu.ifba.admindisciplinasenotas.entidades.Nota;

public record NotaForm(Long ID, String aluno, Float nota, DisciplinaForm disciplina) {
	
	public NotaForm(Nota nota){
		this(nota.getID(), nota.getAluno(), nota.getNota(), new DisciplinaForm(nota.getDisciplina()));
	}
}
