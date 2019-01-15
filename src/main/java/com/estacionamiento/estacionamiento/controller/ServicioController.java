package com.estacionamiento.estacionamiento.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento.dto.ServicioDto;
import com.estacionamiento.estacionamiento.services.ServicioService;

@RestController
@RequestMapping("/controladorServicio")
public class ServicioController {
	
	@Autowired
	@Qualifier("celdaService")
	private ServicioService servicioService; 
	
	@GetMapping(value = "/registrarServicio")
   public @ResponseBody String registrarServicio(@PathParam("dto") ServicioDto servicioDto) {
		return servicioService.registrarServicio(servicioDto);
		
   } 
	
	
	

}
