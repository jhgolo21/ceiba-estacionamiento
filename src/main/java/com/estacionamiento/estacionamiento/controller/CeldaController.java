package com.estacionamiento.estacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamiento.estacionamiento.dto.CeldaDto;
import com.estacionamiento.estacionamiento.services.CeldaService;

@RestController
@RequestMapping("/controladorCelda")
public class CeldaController {
	
	@Autowired
	@Qualifier("celdaService")
	private CeldaService celdaService;
	
	@GetMapping(value = "/listaCeldas")
	public @ResponseBody List<CeldaDto> optenerListaCeldas(){
		return celdaService.listaCeldas();
	}
	

}
