package br.edu.ifba.segurancaApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.segurancaApp.entidades.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
