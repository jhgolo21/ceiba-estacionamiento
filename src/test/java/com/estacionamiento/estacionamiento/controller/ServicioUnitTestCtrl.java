package com.estacionamiento.estacionamiento.controller;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
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

import com.estacionamiento.estacionamiento.dto.ServicioDto;
import com.estacionamiento.estacionamiento.services.ServicioService;
import com.estacionamiento.estacionamiento.util.Constant;

@RunWith(PowerMockRunner.class)
public class ServicioUnitTestCtrl {
	
	@Mock
	private ServicioService servicioService; 
	
	@InjectMocks
	private ServicioController servicioController;
	
	@Before
	public void initMock() throws Exception{
		MockitoAnnotations.initMocks(ServicioUnitTestCtrl.class);
	}
	
	
	/**
	 * test para validar el servicio de registro del servicio
	 */
	@Test
	public void validarRegistroServicio() {
		try {
		 ServicioDto dto = new ServicioDto();
		 dto.setCelda(2);
		 dto.setCilindraje(150);
		 dto.setCodigoCelda("CELDA_C_2");
		 dto.setEstado(Constant.ESTADO_INICIO);
		 dto.setPlaca("PRT45F");
		 dto.setTipo(Constant.TIPO_CELDA_CARRO);
		 
		 when(servicioService.registrarServicio(any(ServicioDto.class))).thenReturn(Constant.OPERACION_EXITOSA);
		 
		 servicioController.registrarServicio(dto);
		 
		 assert(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * test para validar la finalizacion del servicio
	 */
	@Test
	public void validarFinalizarServicio() {
		try {
			
			when(servicioService.finServicio(anyInt())).thenReturn(Constant.OPERACION_EXITOSA);
			
			servicioController.finalizarServicio(1);
			
			assert(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * validar consulta de servicios activos
	 */
	@Test
	public void validarServiciosActivos() {
		try {
			 List<ServicioDto> lista = new ArrayList<>();
			
			when(servicioService.serviciosActivos()).thenReturn(lista);
			
			servicioController.serviciosActivos();
			
			assert(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	

}
