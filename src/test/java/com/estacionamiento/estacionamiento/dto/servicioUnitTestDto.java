package com.estacionamiento.estacionamiento.dto;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.estacionamiento.estacionamiento.entity.TbCelda;
import com.estacionamiento.estacionamiento.entity.TbServicio;

@RunWith(PowerMockRunner.class)
public class servicioUnitTestDto {
	
	@Before
	public void initMock() throws Exception{
		MockitoAnnotations.initMocks(servicioUnitTestDto.class);
	}
	
	@Test
	public void validarInstanciaListServicio() {
		try {
           List<ServicioDto> servicioDtos = new ArrayList<>();
			
			List<TbServicio> listaServicios = new ArrayList<>();
			
			TbCelda celda = new TbCelda();
			TbServicio serivicio = new TbServicio();
			serivicio.setTbCelda(celda);
			
			listaServicios.add(serivicio);
			
			servicioDtos = ServicioDto.getInstanceList(listaServicios);
			
			if(servicioDtos.size()>0) {
				assert(true);
			}else {
				fail();
			}
			
		} catch (Exception e) {
			fail();
		}
		
	}

}
