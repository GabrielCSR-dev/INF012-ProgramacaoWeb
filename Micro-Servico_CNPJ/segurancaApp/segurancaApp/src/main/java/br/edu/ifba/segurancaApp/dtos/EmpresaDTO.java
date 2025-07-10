package br.edu.ifba.segurancaApp.dtos;

import br.edu.ifba.segurancaApp.entidades.Empresa;

public record EmpresaDTO(Long Id, String cnpj, String nome) {
	
	public EmpresaDTO(Empresa empresa) {
		this(empresa.getId(), empresa.getCnpj(), empresa.getNome());
	}
}
