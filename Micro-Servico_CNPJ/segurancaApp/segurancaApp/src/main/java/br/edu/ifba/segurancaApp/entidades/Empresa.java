package br.edu.ifba.segurancaApp.entidades;

import br.edu.ifba.segurancaApp.dtos.EmpresaForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "empresas")
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cnpj;
	private String nome;
	
	public Empresa() {
		super();
	}
	public Empresa(Long id, String cnpj, String nome) {
		super();
		this.id = id;
		this.setCnpj(cnpj.replaceAll("\\D", ""));
		this.setNome(nome);
	}
	public Empresa(EmpresaForm form) {
		this.id = form.Id();
		this.cnpj = form.cnpj();
		this.nome = form.nome();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
