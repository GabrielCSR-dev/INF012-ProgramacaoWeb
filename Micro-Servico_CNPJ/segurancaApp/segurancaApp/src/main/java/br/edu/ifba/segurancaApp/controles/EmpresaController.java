package br.edu.ifba.segurancaApp.controles;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.segurancaApp.dtos.EmpresaDTO;
import br.edu.ifba.segurancaApp.dtos.EmpresaForm;
import br.edu.ifba.segurancaApp.servicos.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	private EmpresaService empresaService;

	public EmpresaController(EmpresaService empresaService) {
		super();
		this.empresaService = empresaService;
	}

	@GetMapping
	public List<EmpresaDTO> listEmpresas() {
		return this.empresaService.listEmpresas();
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<EmpresaDTO> findEmpresaById(@PathVariable Long Id){
		EmpresaDTO empresa = this.empresaService.findEmpresaById(Id);
		if(empresa == null) 
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(empresa);
	}
	
	@PostMapping
	public ResponseEntity<String> postEmpresa(@RequestBody EmpresaForm form, UriComponentsBuilder uriBuilder){
		EmpresaForm newEmpresa = this.empresaService.postEmpresa(form);
		if(newEmpresa != null) {
			URI uri = uriBuilder.path("/empresas/{Id}").buildAndExpand(newEmpresa.Id()).toUri();
			return ResponseEntity.created(uri).body(newEmpresa.toString());
		}
		return ResponseEntity.badRequest().body("CNPJ '" + form.cnpj() + "' inválido!");
	}
	
	@DeleteMapping("/{Id}")
	public ResponseEntity<EmpresaDTO> deleteEmpresa(@PathVariable Long Id){
		EmpresaDTO empresa = this.empresaService.deleteEmpresa(Id);
		return ResponseEntity.ok().body(empresa);
	}
	
	@PutMapping("/{Id}")
	public ResponseEntity<EmpresaForm> updateEmpresa(@PathVariable Long Id, @RequestBody EmpresaForm form){
		EmpresaForm newEmpresa = this.empresaService.updateEmpresa(Id, form);
		return ResponseEntity.ok().body(newEmpresa);
	}
	
}
