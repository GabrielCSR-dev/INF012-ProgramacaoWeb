package br.edu.ifba.segurancaApp.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@FeignClient("cnpjservice")
public interface CNPJClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/validacnpj")
	public ResponseEntity<String> validarCNPJ(@RequestBody CNPJDTO cnpj);

}

