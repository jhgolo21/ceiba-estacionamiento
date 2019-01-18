package com.estacionamiento.estacionamiento.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.estacionamiento.estacionamiento.controllerView.ControllerTemplatesServicio;

@RunWith(PowerMockRunner.class)
public class controllerTemplateUnitTest {
	
	@InjectMocks
	private ControllerTemplatesServicio controllerTemplatesServicio;
	
	@Before
	public void initMock() throws Exception{
		MockitoAnnotations.initMocks(controllerTemplateUnitTest.class);
	}
	
	@Test
	public void validarServicioTemplate() {
		try {
			String  respuesta = controllerTemplatesServicio.servicio();
			
			assertEquals("servicio", respuesta);
		} catch (Exception e) {
			fail();
		}
		
	}

}
