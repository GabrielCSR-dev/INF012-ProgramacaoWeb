package br.edu.ifba.segurancaApp.servicos;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ifba.segurancaApp.clients.CNPJClient;
import br.edu.ifba.segurancaApp.clients.CNPJDTO;
import br.edu.ifba.segurancaApp.dtos.EmpresaDTO;
import br.edu.ifba.segurancaApp.dtos.EmpresaForm;
import br.edu.ifba.segurancaApp.entidades.Empresa;
import br.edu.ifba.segurancaApp.repositorios.EmpresaRepository;
import feign.FeignException;

@Service
public class EmpresaService {

	private EmpresaRepository empresaRepository;
	private CNPJClient cnpjCLient;

	public EmpresaService(EmpresaRepository empresaRepository, CNPJClient cnpjCLient) {
		super();
		this.empresaRepository = empresaRepository;
		this.cnpjCLient = cnpjCLient;
	}
	
	public List<EmpresaDTO> listEmpresas(){
		return this.empresaRepository.findAll().stream().map(EmpresaDTO::new).toList();
	}
	
	public EmpresaDTO findEmpresaById(Long Id) {
		return new EmpresaDTO(this.empresaRepository.findById(Id).get());
	}
	
	public EmpresaForm postEmpresa(EmpresaForm form) {
		try {
			ResponseEntity<String> response = this.cnpjCLient.validarCNPJ(new CNPJDTO(form.cnpj()));
			if(response.getStatusCode() == HttpStatusCode.valueOf(200)) {
				Empresa novaEmpresa = new Empresa(form); 
				novaEmpresa = this.empresaRepository.save(new Empresa(form));
				return new EmpresaForm(novaEmpresa);
			}
		}catch (FeignException e){
			return null;
		}
		return null;
	}
	
	public EmpresaDTO deleteEmpresa(Long Id) {
		Empresa empresa = this.empresaRepository.findById(Id).get();
		this.empresaRepository.deleteById(Id);
		return new EmpresaDTO(empresa);
	}
	
	public EmpresaForm updateEmpresa(Long Id, EmpresaForm form) {
		Empresa empresa = this.empresaRepository.findById(Id).get();
		empresa.setCnpj(form.cnpj() == null ? empresa.getCnpj() : form.cnpj());
		empresa.setNome(form.nome() == null ? empresa.getNome() : form.nome());
		return new EmpresaForm(empresa);
	}
}
