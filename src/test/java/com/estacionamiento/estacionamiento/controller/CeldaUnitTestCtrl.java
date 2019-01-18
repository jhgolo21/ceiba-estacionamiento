package com.estacionamiento.estacionamiento.controller;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.estacionamiento.estacionamiento.dto.CeldaDto;
import com.estacionamiento.estacionamiento.services.CeldaService;

@RunWith(PowerMockRunner.class)
public class CeldaUnitTestCtrl {
	
	@Mock
	private CeldaService celdaService;
	
	@InjectMocks
	private CeldaController celdaController;
	
	@Before
	public void initMock() throws Exception{
		MockitoAnnotations.initMocks(CeldaUnitTestCtrl.class);
	}
	
	@Test
	public void validarOptenerListaCeldas() {
		try {
			List<CeldaDto> celdaDtos = new ArrayList<>();
			
			when(celdaService.listaCeldas()).thenReturn(celdaDtos);
			
			celdaController.optenerListaCeldas();
			
			assert(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void validarListaCeldasTipo() {
		try {
			List<CeldaDto> celdaDtos = new ArrayList<>();
			
			when(celdaService.listaCeldasTipo(anyString())).thenReturn(celdaDtos);
			
			celdaController.listaCeldasTipo("");
			assert(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	

}
