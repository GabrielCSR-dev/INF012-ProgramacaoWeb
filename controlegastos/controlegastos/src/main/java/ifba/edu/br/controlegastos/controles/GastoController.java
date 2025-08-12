package ifba.edu.br.controlegastos.controles;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ifba.edu.br.controlegastos.dtos.GastoDTO;
import ifba.edu.br.controlegastos.dtos.GastoForm;
import ifba.edu.br.controlegastos.dtos.RelatorioGasto;
import ifba.edu.br.controlegastos.enums.Categoria;
import ifba.edu.br.controlegastos.servicos.GastoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gastos")
public class GastoController {

	private GastoService gastoService;

	public GastoController(GastoService gastoService) {
		super();
		this.gastoService = gastoService;
	}
	
	@GetMapping
	public Page<GastoDTO> listar(Pageable pageable) {
		return this.gastoService.listar(pageable);
	}
	
	@GetMapping("/searchCategoria")
	public List<GastoDTO> listarPorCategoria(@RequestParam Categoria categoria) {
			return this.gastoService.listarPorCategoria(categoria);
	}
	
	@GetMapping("/searchData")
	public List<GastoDTO> listarPorData(@RequestParam Integer ano, @RequestParam Integer mes) {
			return this.gastoService.listarPorData(ano, mes);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GastoDTO> buscarPorId(@PathVariable Long id) {
		GastoDTO gasto = this.gastoService.buscarPorId(id);
		if(gasto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(gasto);
	}
	
	@PostMapping
	public ResponseEntity<GastoForm> criar(@Valid @RequestBody GastoForm gasto, UriComponentsBuilder uriBuilder) {
		GastoForm form = this.gastoService.criar(gasto);
		URI uri = uriBuilder.path("/gastos/{id}").buildAndExpand(form.id()).toUri();
		
		return ResponseEntity.created(uri).body(form);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<GastoForm> atualizar(@PathVariable Long id, @RequestBody GastoForm form) {
		GastoForm updated = this.gastoService.atualizar(id, form);
		
		if(updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<GastoDTO> deletar(@PathVariable Long id) {
		GastoDTO deleted = this.gastoService.deletar(id);
		
		if(deleted == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(deleted);
	}
	
	@GetMapping("/relatorio")
	public ResponseEntity<RelatorioGasto> relatorioDoMes(@RequestParam Integer ano, @RequestParam Integer mes) {
		RelatorioGasto relatorio = this.gastoService.relatorioDoMes(ano, mes);
		return ResponseEntity.ok().body(relatorio);
	}
}
