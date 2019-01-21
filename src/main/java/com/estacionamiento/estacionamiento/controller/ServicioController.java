package com.estacionamiento.estacionamiento.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento.dto.ServicioDto;
import com.estacionamiento.estacionamiento.services.ServicioService;

@RestController
@RequestMapping("/controladorServicio")
public class ServicioController {
	
	@Autowired
	@Qualifier("servicioService")
	private ServicioService servicioService; 
	
	@PostMapping(value = "/registrarServicio")
   public void registrarServicio(@RequestBody ServicioDto servicioDto) {
		Calendar fechActual = Calendar.getInstance();
		servicioDto.setFechaInicio(fechActual.getTime());
		servicioService.registrarServicio(servicioDto);
   }
	
	@GetMapping(value = "/finalizarServicio/{idServicio}")
	public ServicioDto finalizarServicio(@PathVariable int idServicio) {
	     return servicioService.finServicio(idServicio);
    }
	
	@GetMapping(value = "/serviciosActivos")
	public @ResponseBody List<ServicioDto> serviciosActivos(){
		return servicioService.serviciosActivos();
	}
	 
	@GetMapping(value = "/historialServicios")
	public @ResponseBody List<ServicioDto> historialServicios(){
		return servicioService.historialServicios();
	}
	

}
