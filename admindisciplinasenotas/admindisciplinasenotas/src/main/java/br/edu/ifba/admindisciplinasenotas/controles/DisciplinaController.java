package br.edu.ifba.admindisciplinasenotas.controles;

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

import br.edu.ifba.admindisciplinasenotas.dtos.DisciplinaDTO;
import br.edu.ifba.admindisciplinasenotas.dtos.DisciplinaForm;
import br.edu.ifba.admindisciplinasenotas.dtos.NotaDTO;
import br.edu.ifba.admindisciplinasenotas.dtos.NotaForm;
import br.edu.ifba.admindisciplinasenotas.servicos.DisciplinaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

	private DisciplinaService disciplinaService;

	public DisciplinaController(DisciplinaService disciplinaService) {
		super();
		this.disciplinaService = disciplinaService;
	}

	@PostMapping
	public ResponseEntity<DisciplinaForm> cadastrar(@RequestBody DisciplinaForm disciplinaForm, UriComponentsBuilder uriBuilder) {
		DisciplinaForm form = this.disciplinaService.cadastrar(disciplinaForm);
		URI uri = uriBuilder.path("/api/disciplinas/{ID}").buildAndExpand(form.ID()).toUri();
		return ResponseEntity.created(uri).body(form);
	}
	
	@GetMapping
	public List<DisciplinaDTO> listar() {
		return this.disciplinaService.listar();
	}
	
	@GetMapping("/{ID}")
	public DisciplinaDTO listarPorID(@PathVariable Long ID) {
		return this.disciplinaService.listarPorID(ID);
	}
	
	@DeleteMapping("/{ID}")
	public ResponseEntity<DisciplinaDTO> deletarPorID(@PathVariable Long ID) {
		DisciplinaDTO disciplinaDTO = this.disciplinaService.listarPorID(ID);
		this.disciplinaService.deletarPorID(ID);
		return ResponseEntity.ok().body(disciplinaDTO);
	}
	
	@PutMapping("/{ID}")
	@Transactional
	public ResponseEntity<DisciplinaForm> atualizarPorID(@PathVariable Long ID, @RequestBody DisciplinaForm disciplinaForm) {
		DisciplinaForm form = this.disciplinaService.atualizarPorID(ID, disciplinaForm);
		return ResponseEntity.ok().body(form);
	}
	
	@GetMapping("/{ID}/notas")
	public List<NotaDTO> listarNotasdeDisciplina(@PathVariable Long ID){
		return this.disciplinaService.listarNotasPorDisciplina(ID);
	}
	
	@PostMapping("/{ID}/notas")
	public ResponseEntity<NotaForm> postarNotaEmDisciplina(@PathVariable Long ID, @RequestBody NotaForm form, UriComponentsBuilder uriBuilder){
		NotaForm notaForm = this.disciplinaService.cadastrarNota(ID, form);
		URI uri = uriBuilder.path("/api/notas/{ID}").buildAndExpand(form).toUri();
		return ResponseEntity.created(uri).body(notaForm);
		
	}
}
