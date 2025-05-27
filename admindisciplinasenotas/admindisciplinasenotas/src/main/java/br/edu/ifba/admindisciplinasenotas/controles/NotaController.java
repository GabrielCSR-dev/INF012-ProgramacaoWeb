package br.edu.ifba.admindisciplinasenotas.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.admindisciplinasenotas.dtos.NotaDTO;
import br.edu.ifba.admindisciplinasenotas.dtos.NotaForm;
import br.edu.ifba.admindisciplinasenotas.servicos.NotaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
	
	NotaService notaService;
	
	public NotaController(NotaService notaService) {
		super();
		this.notaService = notaService;
	}	
	
	@PutMapping("{ID}")
	@Transactional
	public ResponseEntity<NotaForm> atualizarNota(@PathVariable Long ID, @RequestBody NotaForm notaForm) {
		NotaForm form = this.notaService.atualizarNota(ID, notaForm);
		return ResponseEntity.ok().body(form);
	}
	
	@DeleteMapping("{ID}")
	public ResponseEntity<NotaDTO> deletarNota(@PathVariable Long ID) {
		NotaDTO notaDTO = this.notaService.deletarNota(ID);
		return ResponseEntity.ok().body(notaDTO);
	}
}
