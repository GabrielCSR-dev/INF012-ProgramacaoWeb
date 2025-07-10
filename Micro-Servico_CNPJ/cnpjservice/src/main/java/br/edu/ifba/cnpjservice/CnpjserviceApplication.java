package br.edu.ifba.cnpjservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CnpjserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnpjserviceApplication.class, args);
	}

}
