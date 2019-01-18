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

@RunWith(PowerMockRunner.class)
public class celdaUnitTestDto {
	
	@Before
	public void initMock() throws Exception{
		MockitoAnnotations.initMocks(celdaUnitTestDto.class);
	}
	
	@Test
	public void validarInstanciaListCelda() {
		try {
			List<CeldaDto> celdaDtoList = new ArrayList<>();
			
			List<TbCelda> listaCelda = new ArrayList<>();
			
			TbCelda celda = new TbCelda();
			
			listaCelda.add(celda);
			
			celdaDtoList = CeldaDto.getInstanceList(listaCelda);
			
			if(celdaDtoList.size()>0) {
				assert(true);
			}else {
				fail();
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	

}
