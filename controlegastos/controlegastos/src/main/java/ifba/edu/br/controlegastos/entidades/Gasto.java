package ifba.edu.br.controlegastos.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import ifba.edu.br.controlegastos.dtos.GastoForm;
import ifba.edu.br.controlegastos.enums.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity(name = "gastos")
public class Gasto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "A descricao não pode ser nula ou vazia")
	@Length(min = 3, max = 40, message = "A descrição precisa ter de 3 a 40 caracteres")
	private String descricao;
	@NotNull(message = "O valor não pode ser nulo")
	@Min(value = 0, message = "O valor não pode ser menor que 0")
	@Max(value = 1000000, message = "O valor não pode ser acima de 1000000")
	private BigDecimal valor;
	@NotNull(message = "A data não pode ser nula")
	@PastOrPresent(message = "Esta data está no futuro")
	private LocalDate data;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public Gasto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gasto(Long id, String descricao, BigDecimal valor, LocalDate data, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = categoria;
	}
	
	public Gasto(GastoForm form) {
		super();
		this.id = form.id();
		this.descricao = form.descricao();
		this.valor = form.valor();
		this.data = form.data();
		this.categoria = form.categoria();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
