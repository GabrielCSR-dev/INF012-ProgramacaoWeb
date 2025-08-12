package ifba.edu.br.controlegastos.dtos;

import java.math.BigDecimal;

public record RelatorioGasto(BigDecimal ALIMENTACAO, BigDecimal TRANSPORTE, BigDecimal LAZER, BigDecimal SAUDE,
								BigDecimal OUTROS) {

}
