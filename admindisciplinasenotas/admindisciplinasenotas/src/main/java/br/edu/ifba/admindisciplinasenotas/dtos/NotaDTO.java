package br.edu.ifba.admindisciplinasenotas.dtos;

import br.edu.ifba.admindisciplinasenotas.entidades.Nota;

public record NotaDTO(Long ID, String aluno, Float nota, DisciplinaDTO disciplina) {

	public NotaDTO(Nota nota) {
		this(nota.getID(), nota.getAluno(), nota.getNota(), new DisciplinaDTO(nota.getDisciplina()));
	}
}
