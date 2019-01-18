package com.estacionamiento.estacionamiento.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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

import com.estacionamiento.estacionamiento.entity.TbConfigVal;
import com.estacionamiento.estacionamiento.repository.ConfigValRepository;

@RunWith(PowerMockRunner.class)
public class configValUnitTest {
	
	@Mock
	private ConfigValRepository configValRepository;
	
	
	@InjectMocks
	private ConfigValService configValService;
	
	@Before
	public void initMock() throws Exception{
		MockitoAnnotations.initMocks(configValUnitTest.class);
	}
	
	/**
	 * validar la carga de valores en el mapa
	 */
	@Before
	public void validarCargaValoresTest() {
		try {
			
			List<TbConfigVal> lista = new ArrayList<>();
			TbConfigVal configVal = new TbConfigVal();
			
			configVal.setNbConfigValValor(100);
			configVal.setVrConfigValNombre("PP");
			lista.add(configVal);
			
			when(configValRepository.findAll()).thenReturn(lista);
			
			configValService.cargarValores();
			
			assert(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	/**
	 * validar metodo buscar valor, valor no encontrado 
	 */
	@Test
	public void bucarValorNotFound() {
		try {
			int resul = ConfigValService.findValue("a");
			assertEquals(0, resul);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void bucarValorFound() {
		try {
			int resul = ConfigValService.findValue("PP");
			assertEquals(100, resul);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	
	
	
}
