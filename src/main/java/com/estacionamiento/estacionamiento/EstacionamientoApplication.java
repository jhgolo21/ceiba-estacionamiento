package com.estacionamiento.estacionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.estacionamiento.estacionamiento.services.ConfigValService;

@SpringBootApplication
public class EstacionamientoApplication {
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EstacionamientoApplication.class, args);
		
		//invocacion a bean para cargar la configuración de los valores (singleton)
		context.getBean(ConfigValService.class).cargarValores();
	}

}

