package br.edu.ifba.segurancaApp.dtos;

import br.edu.ifba.segurancaApp.entidades.Empresa;

public record EmpresaForm(Long Id, String cnpj, String nome) {
	public EmpresaForm(Empresa empresa) {
		this(empresa.getId(), empresa.getCnpj(), empresa.getNome());
	}
}
