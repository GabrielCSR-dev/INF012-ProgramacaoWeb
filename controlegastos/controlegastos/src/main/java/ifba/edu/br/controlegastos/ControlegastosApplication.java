package ifba.edu.br.controlegastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ControlegastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlegastosApplication.class, args);
	}

}
