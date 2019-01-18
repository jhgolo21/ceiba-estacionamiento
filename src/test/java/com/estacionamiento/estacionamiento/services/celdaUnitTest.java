package com.estacionamiento.estacionamiento.services;

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
import com.estacionamiento.estacionamiento.entity.TbCelda;
import com.estacionamiento.estacionamiento.repository.CeldaRepository;
import com.estacionamiento.estacionamiento.util.Constant;

@RunWith(PowerMockRunner.class)
public class celdaUnitTest {
	
	@Mock
	private CeldaRepository celdaRepository;
	
	@InjectMocks
	private CeldaService celdaService;
	
	@Before
	public void initMock() throws Exception{
		MockitoAnnotations.initMocks(celdaUnitTest.class);
	}
	
	/**
	 * validar consulta masiva de celdas
	 */
	@Test
	public void validarlistaCeldas() {
		try {
			List<TbCelda> lista = new ArrayList<>();
			List<CeldaDto> celdaDtos = new ArrayList<>();
			
			when(celdaRepository.findAll()).thenReturn(lista);
			
			celdaDtos = celdaService.listaCeldas();
			
			if(celdaDtos.size() == 0) {
				assert(true);
			}else {
				fail();
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * celdas tipo
	 */
	@Test
	public void validarListaCeldasTipo() {
		try {
			List<TbCelda> lista = new ArrayList<>();
			List<CeldaDto> celdaDtos = new ArrayList<>();
			
			when(celdaRepository.findByVrCeldaTipoAndVrCeldaEstado(anyString(),anyString())).thenReturn(lista);
			
			celdaDtos = celdaService.listaCeldasTipo(Constant.ESTADO_CEL_FIN);
			
			if(celdaDtos.size() == 0) {
				assert(true);
			}else {
				fail();
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	

}
