package br.edu.ifba.admindisciplinasenotas.entidades;

import br.edu.ifba.admindisciplinasenotas.dtos.NotaForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "notas")
public class Nota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	private String aluno;
	private float nota;
	@ManyToOne
	private Disciplina disciplina;
	
	public Nota(){
	}
	public Nota(Long ID, String aluno, Float nota, Disciplina disciplina) {
		this.ID = ID;
		this.aluno = aluno;
		this.nota = nota;
		this.disciplina = disciplina;
	}
	public Nota(NotaForm form, Disciplina disciplina) {
		this.ID = form.ID();
		this.aluno = form.aluno();
		this.nota = form.nota();
		this.disciplina = disciplina;
	}
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}
