package com.estacionamiento.estacionamiento.controllerView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTemplatesServicio {

	@GetMapping("/servicio")
	public String servicio() {
		return "servicio";
	} 
	
	
}
