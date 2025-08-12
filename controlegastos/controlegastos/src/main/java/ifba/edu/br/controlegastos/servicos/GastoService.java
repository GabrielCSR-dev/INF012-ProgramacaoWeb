package ifba.edu.br.controlegastos.servicos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ifba.edu.br.controlegastos.dtos.GastoDTO;
import ifba.edu.br.controlegastos.dtos.GastoForm;
import ifba.edu.br.controlegastos.dtos.RelatorioGasto;
import ifba.edu.br.controlegastos.entidades.Gasto;
import ifba.edu.br.controlegastos.enums.Categoria;
import ifba.edu.br.controlegastos.repositorios.GastoRepository;

@Service
public class GastoService {
	
	private GastoRepository gastoRepository;

	public GastoService(GastoRepository gastoRepository) {
		super();
		this.gastoRepository = gastoRepository;
	}
	
	public Page<GastoDTO> listar(Pageable pageable) {
		return this.gastoRepository.findAll(pageable).map(GastoDTO::new);
	}
	
	public List<GastoDTO> listarPorCategoria(Categoria categoria) {
		return this.gastoRepository.findByCategoria(categoria).stream().map(GastoDTO::new).toList();
	}
	
	public List<GastoDTO> listarPorData(Integer ano, Integer mes) {
		LocalDate dataInicial = LocalDate.of(ano, mes, 1);
		LocalDate dataFinal = LocalDate.of(ano, mes, dataInicial.lengthOfMonth());
		
		return this.gastoRepository.findByDataBetween(dataInicial, dataFinal).stream().map(GastoDTO::new).toList();
	}
	
	public GastoDTO buscarPorId(Long id) {
		Gasto gasto = this.gastoRepository.findById(id).get();
		if(gasto == null) {
			return null;
		}
		return new GastoDTO(gasto);
	}
	
	public GastoForm criar(GastoForm form) {
		Gasto newGasto = this.gastoRepository.save(new Gasto(form));
		return new GastoForm(newGasto);
	}
	
	public GastoForm atualizar(Long id, GastoForm form) {
		try {
			Gasto toUpdate = this.gastoRepository.findById(id).get();
			
			toUpdate.setDescricao(form.descricao() != null ? form.descricao() : toUpdate.getDescricao());
			toUpdate.setValor(form.valor() != null ? form.valor() : toUpdate.getValor());
			toUpdate.setData(form.data() != null ? form.data() : toUpdate.getData());
			toUpdate.setCategoria(form.categoria() != null ? form.categoria() : toUpdate.getCategoria());
			
			this.gastoRepository.save(toUpdate);		
			return new GastoForm(toUpdate);
		} catch (Exception e) {
			return null;
		}	
	}
	
	public GastoDTO deletar(Long id) {		
		try {
			Gasto toDelete = this.gastoRepository.findById(id).get();
			
			this.gastoRepository.deleteById(id);
			return new GastoDTO(toDelete);
		} catch (Exception e) {
			return null;
		}
	}
	
	public RelatorioGasto relatorioDoMes(Integer ano, Integer mes) {
		List<GastoDTO> gastos = this.listarPorData(ano, mes);
		BigDecimal totalAlimentacao = new BigDecimal(0);
		BigDecimal totalTransporte = new BigDecimal(0);
		BigDecimal totalLazer = new BigDecimal(0);
		BigDecimal totalSaude = new BigDecimal(0);
		BigDecimal totalOutros = new BigDecimal(0);
		
		for(GastoDTO gasto : gastos) {
			switch(gasto.categoria()) {
				case ALIMENTAÇÃO: totalAlimentacao = totalAlimentacao.add(gasto.valor()); break;
				case TRANSPORTE: totalTransporte = totalTransporte.add(gasto.valor()); break;
				case LAZER: totalLazer = totalLazer.add(gasto.valor()); break;
				case SAÚDE: totalSaude = totalSaude.add(gasto.valor()); break;
				default: totalOutros = totalOutros.add(gasto.valor()); break;
			}
		}
			
			return new RelatorioGasto(totalAlimentacao, totalTransporte, totalLazer, totalSaude, totalOutros);
	}
}
