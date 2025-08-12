package br.edu.ifba.admindisciplinasenotas.entidades;

import br.edu.ifba.admindisciplinasenotas.dtos.DisciplinaForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "disciplinas")
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	private String nome;
	private String codigo;
	
	public Disciplina() {
	}
	public Disciplina(Long iD, String nome, String codigo) {
		ID = iD;
		this.nome = nome;
		this.codigo = codigo;
	}
	public Disciplina(DisciplinaForm form) {
		ID = form.ID();
		nome = form.nome();
		codigo = form.codigo();
	}
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
