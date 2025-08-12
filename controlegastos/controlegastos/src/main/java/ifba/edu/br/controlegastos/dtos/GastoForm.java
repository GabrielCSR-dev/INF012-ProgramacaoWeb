package ifba.edu.br.controlegastos.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import ifba.edu.br.controlegastos.entidades.Gasto;
import ifba.edu.br.controlegastos.enums.Categoria;

public record GastoForm(Long id, String descricao, BigDecimal valor, LocalDate data, Categoria categoria) {

	public GastoForm(Gasto gasto) {
		this(gasto.getId(), gasto.getDescricao(), gasto.getValor(), gasto.getData(), gasto.getCategoria());
	}
}
