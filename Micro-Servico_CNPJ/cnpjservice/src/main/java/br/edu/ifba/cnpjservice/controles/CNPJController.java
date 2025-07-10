package br.edu.ifba.cnpjservice.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.cnpjservice.dtos.CNPJDTO;
import br.edu.ifba.cnpjservice.entidades.CNPJValidacao;

@RestController
@RequestMapping("/validacnpj")
public class CNPJController {
	
	public CNPJController() {
	}
	
	@PostMapping
	public ResponseEntity<String> validarCNPJ(@RequestBody CNPJDTO cnpj){
		if(CNPJValidacao.validaCNPJ(cnpj.cnpj()) == true) {
			return ResponseEntity.ok().body("CNPJ '" + cnpj.cnpj() + "' é válido!");
		} else {
			return ResponseEntity.badRequest().body("CNPJ '" + cnpj.cnpj() + "' NÃO é válido!");
		}
	}
}