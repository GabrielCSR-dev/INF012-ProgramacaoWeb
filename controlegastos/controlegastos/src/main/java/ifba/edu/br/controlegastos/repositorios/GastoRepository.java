package ifba.edu.br.controlegastos.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.edu.br.controlegastos.entidades.Gasto;
import ifba.edu.br.controlegastos.enums.Categoria;

public interface GastoRepository extends JpaRepository<Gasto, Long>{
	
	public List<Gasto> findByCategoria(Categoria categoria);
	
	public List<Gasto> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
