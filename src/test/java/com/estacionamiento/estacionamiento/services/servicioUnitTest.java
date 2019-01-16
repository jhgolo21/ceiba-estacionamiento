package com.estacionamiento.estacionamiento.services;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.estacionamiento.estacionamiento.dto.ServicioDto;
import com.estacionamiento.estacionamiento.entity.TbCelda;
import com.estacionamiento.estacionamiento.entity.TbServicio;
import com.estacionamiento.estacionamiento.repository.CeldaRepository;
import com.estacionamiento.estacionamiento.repository.ServicioRepository;
import com.estacionamiento.estacionamiento.util.Constant;

@RunWith(PowerMockRunner.class)
//@SpringBootTest
//@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@PrepareForTest(ConfigValService.class)
public class servicioUnitTest {
	
	@Mock
	private ServicioRepository servicioRepository;
	
	@Mock
	private CeldaRepository celdaRepository;
	
	@InjectMocks
	private ServicioService servicioService;
	
	@Mock
	private ServicioService servicio2;
	
	@Before
	public void initMock() throws Exception{
		MockitoAnnotations.initMocks(servicioUnitTest.class);
	}
	
	public static final int VALOR_HORA_C = 1000;
	public static final int VALOR_HORA_M = 500;
	public static final int VALOR_DIA_C = 8000;
	public static final int VALOR_DIA_M = 4000;
	public static final int VALOR_M_CILINDRAJE_MAYOR_500 = 2000;
	
	
	// =========================== Test's para registrar el servicio ============================
	
	
	/**
	 * funcion que testea si la placa comienza por la letra A y el dia es lunes o domingo
	 */
	@Test
	public void validarRestriccionPlacaDomingoLunes() {
		try {
			
			TbCelda celda = new TbCelda();
			Calendar fechaLunes = Calendar.getInstance();
			Calendar fechaDomingo = Calendar.getInstance();
			
			TbServicio servicio = new TbServicio();
			servicio.setVrServicioPlaca("ACP56R");
			servicio.setTbCelda(celda);
			
			fechaLunes.set(Calendar.MONTH,0);
			fechaLunes.set(Calendar.DAY_OF_MONTH, 14);
			fechaLunes.set(Calendar.HOUR_OF_DAY, 8);
			
			fechaDomingo.set(Calendar.MONTH,0);
			fechaDomingo.set(Calendar.DAY_OF_MONTH, 13);
			fechaDomingo.set(Calendar.HOUR_OF_DAY, 8);
			
			List<Calendar> fechas = new ArrayList<Calendar>();
			
			fechas.add(fechaLunes);
			fechas.add(fechaDomingo);
			
			boolean resultado = false;
			
			for (Calendar calendar : fechas) {
				
				servicio.setDtServicioFechaini(calendar.getTime());
				
				ServicioDto servicioDto = ServicioDto.getInstance(servicio);
			
				String mensaje = servicioService.registrarServicio(servicioDto);
				
				if(mensaje.equalsIgnoreCase(Constant.NO_INGRESO_PLACA_A)) {
					resultado = true;
				}else {
					resultado = false;
					break;
				}
			}
			
			assert(resultado);
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	/**
	 * flujo normal del metodo
	 */
	@Test
	public void validarRegistroServicio() {
	     try {
				TbCelda celda = new TbCelda();
				Calendar fecha = Calendar.getInstance();
				
				TbServicio servicio = new TbServicio();
				servicio.setVrServicioPlaca("ECP56R");
				servicio.setTbCelda(celda);
				
				fecha.set(Calendar.MONTH,0);
				fecha.set(Calendar.DAY_OF_MONTH, 15);
				fecha.set(Calendar.HOUR_OF_DAY, 8);
	
				when(celdaRepository.findByNbCeldaId(anyInt())).thenReturn(celda);
				when(celdaRepository.save(any(TbCelda.class))).thenReturn(celda);
				when(servicioRepository.save(any(TbServicio.class))).thenReturn(servicio);
				
				boolean resultado = false;
					
				servicio.setDtServicioFechaini(fecha.getTime());
				
				ServicioDto servicioDto = ServicioDto.getInstance(servicio);
			
				String mensaje = servicioService.registrarServicio(servicioDto);
				
				if(mensaje.equalsIgnoreCase(Constant.OPERACION_EXITOSA)) {
					resultado = true;
				}
				
				assert(resultado);
				
			} catch (Exception e) {
				System.err.println(e);
			}
	}
	
	@Test
	public void validarConsultaServicios() {
		List<ServicioDto> listaDto = new ArrayList<>();
		List<TbServicio> listaServicio = new ArrayList<>();
		
		try {
			
			when(servicioRepository.findByVrServicioEstado(anyString())).thenReturn(listaServicio);
			
			listaDto = servicioService.serviciosActivos();
			
			if(listaDto.size() == 0) {
				assert(true);
			}else {
				assert(false);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
		
	}
	
	// ====================================== funcion para terminar servicio ==============================
	
	/**
	 * finalizar servicio
	 */
	@Test
	public void validarFinservicioTest() {
		
		try {
			
			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.set(Calendar.MONTH,1);
			fechaInicial.set(Calendar.DAY_OF_MONTH, 14);
			fechaInicial.set(Calendar.HOUR_OF_DAY, 8);
			
			TbCelda celda = new TbCelda();
			celda.setVrCeldaTipo(Constant.TIPO_CELDA_MOTO);
			
			TbServicio servicio = new TbServicio();
			servicio.setNbServicioCilindraje(400);
			servicio.setDtServicioFechaini(fechaInicial.getTime());
			servicio.setTbCelda(celda);
			
			when(servicioRepository.findByNbServicioId(anyLong())).thenReturn(servicio);
			when(celdaRepository.save(any(TbCelda.class))).thenReturn(celda);
			when(servicioRepository.save(any(TbServicio.class))).thenReturn(servicio);
			
			boolean prueba = false;
			
			String respuesta = servicioService.finServicio(0);
			
			if(respuesta.equalsIgnoreCase(Constant.OPERACION_EXITOSA)) {
				prueba = true;
			}
			assert(prueba);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	// =========================== Test's para calcular valor ============================
	
	/**
	 * test para evaluar la condicion cuando las horas de parque son superiores a 24
	 * y el vehiculo es de tipo moto
	 * CODIGO: if(horas > 24) -> switch (tipoVehiculo) = TIPO_MOTO
	 * CILINDRAJE: 400
	 * TIEMPO: 1 dia y 2 horas
	 */
	@Test
	public void calcularValorDiasHorasMotoTest() {
		try {
			
			//give
			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.set(Calendar.MONTH,1);
			fechaInicial.set(Calendar.DAY_OF_MONTH, 14);
			fechaInicial.set(Calendar.HOUR_OF_DAY, 8);
			
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.set(Calendar.MONTH,1);
			fechaFin.set(Calendar.DAY_OF_MONTH, 15);
			fechaFin.set(Calendar.HOUR_OF_DAY, 10);
			
			TbCelda celda = new TbCelda();
			celda.setVrCeldaTipo(Constant.TIPO_CELDA_MOTO);
			
			TbServicio servicio = new TbServicio();
			servicio.setNbServicioCilindraje(400);
			servicio.setDtServicioFechaini(fechaInicial.getTime());
			servicio.setDtServicioFechafin(fechaFin.getTime());
			servicio.setTbCelda(celda);
			
			PowerMockito.mockStatic(ConfigValService.class);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_DIA_M)).thenReturn(VALOR_DIA_M);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_HORA_M)).thenReturn(VALOR_HORA_M);
			
			//when
			Long valor = servicioService.calcularValor(servicio);
			
			//then
			assertEquals(valor, Long.valueOf(5000));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	
	/**
	 * funcion para probar la condicion cuando el tiempo supera las 24 horas y 
	 * es tipo carro
	 * CODIGO: if(horas > 24) -> switch (tipoVehiculo) = TIPO_CARRO
	 * TIEMPO: 1 dia y 3 horas
	 */
	@Test
	public void calcularValorDiasHorasCarroTest() {
		try {
			
			//give
			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.set(Calendar.MONTH,1);
			fechaInicial.set(Calendar.DAY_OF_MONTH, 14);
			fechaInicial.set(Calendar.HOUR_OF_DAY, 8);
			
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.set(Calendar.MONTH,1);
			fechaFin.set(Calendar.DAY_OF_MONTH, 15);
			fechaFin.set(Calendar.HOUR_OF_DAY, 11);
			
			TbCelda celda = new TbCelda();
			celda.setVrCeldaTipo(Constant.TIPO_CELDA_CARRO);
			
			TbServicio servicio = new TbServicio();
			servicio.setNbServicioCilindraje(0);
			servicio.setDtServicioFechaini(fechaInicial.getTime());
			servicio.setDtServicioFechafin(fechaFin.getTime());
			servicio.setTbCelda(celda);
			
			
			PowerMockito.mockStatic(ConfigValService.class);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_DIA_C)).thenReturn(VALOR_DIA_C);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_HORA_C)).thenReturn(VALOR_HORA_C);
			
			//when
			Long valor = servicioService.calcularValor(servicio);
			
			//then
			assertEquals(valor, Long.valueOf(11000));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void calcularValorHorasMotoCobroDiaTest() {
		try {
			
			//give
			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.set(Calendar.MONTH,1);
			fechaInicial.set(Calendar.DAY_OF_MONTH, 14);
			fechaInicial.set(Calendar.HOUR_OF_DAY, 2);
			
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.set(Calendar.MONTH,1);
			fechaFin.set(Calendar.DAY_OF_MONTH, 14);
			fechaFin.set(Calendar.HOUR_OF_DAY, 11);
			
			TbCelda celda = new TbCelda();
			celda.setVrCeldaTipo(Constant.TIPO_CELDA_MOTO);
			
			TbServicio servicio = new TbServicio();
			servicio.setNbServicioCilindraje(400);
			servicio.setDtServicioFechaini(fechaInicial.getTime());
			servicio.setDtServicioFechafin(fechaFin.getTime());
			servicio.setTbCelda(celda);
			
			
			PowerMockito.mockStatic(ConfigValService.class);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_DIA_M)).thenReturn(VALOR_DIA_M);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_HORA_M)).thenReturn(VALOR_HORA_M);
			
			//when
			Long valor = servicioService.calcularValor(servicio);
			
			//then
			assertEquals(valor, Long.valueOf(VALOR_DIA_M));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void calcularValorHorasMotoCobroHoraTest() {
		try {
			//give
			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.set(Calendar.MONTH,1);
			fechaInicial.set(Calendar.DAY_OF_MONTH, 14);
			fechaInicial.set(Calendar.HOUR_OF_DAY, 2);
			
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.set(Calendar.MONTH,1);
			fechaFin.set(Calendar.DAY_OF_MONTH, 14);
			fechaFin.set(Calendar.HOUR_OF_DAY, 9);
			
			TbCelda celda = new TbCelda();
			celda.setVrCeldaTipo(Constant.TIPO_CELDA_MOTO);
			
			TbServicio servicio = new TbServicio();
			servicio.setNbServicioCilindraje(400);
			servicio.setDtServicioFechaini(fechaInicial.getTime());
			servicio.setDtServicioFechafin(fechaFin.getTime());
			servicio.setTbCelda(celda);
			
			
			PowerMockito.mockStatic(ConfigValService.class);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_DIA_M)).thenReturn(VALOR_DIA_M);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_HORA_M)).thenReturn(VALOR_HORA_M);
			
			//when
			Long valor = servicioService.calcularValor(servicio);
			
			//then
			assertEquals(valor, Long.valueOf(3500));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 */
	@Test
	public void calcularValorHorasCarroCobroDiaTest() {
		try {
			//give
			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.set(Calendar.MONTH,1);
			fechaInicial.set(Calendar.DAY_OF_MONTH, 14);
			fechaInicial.set(Calendar.HOUR_OF_DAY, 2);
			
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.set(Calendar.MONTH,1);
			fechaFin.set(Calendar.DAY_OF_MONTH, 14);
			fechaFin.set(Calendar.HOUR_OF_DAY, 15);
			
			TbCelda celda = new TbCelda();
			celda.setVrCeldaTipo(Constant.TIPO_CELDA_CARRO);
			
			TbServicio servicio = new TbServicio();
			servicio.setNbServicioCilindraje(400);
			servicio.setDtServicioFechaini(fechaInicial.getTime());
			servicio.setDtServicioFechafin(fechaFin.getTime());
			servicio.setTbCelda(celda);
			
			
			PowerMockito.mockStatic(ConfigValService.class);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_DIA_C)).thenReturn(VALOR_DIA_C);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_HORA_C)).thenReturn(VALOR_HORA_C);
			
			//when
			Long valor = servicioService.calcularValor(servicio);
			
			//then
			assertEquals(valor, Long.valueOf(VALOR_DIA_C));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 */
	@Test
	public void calcularValorHorasCarroCobroHoraTest() {
		try {
			//give
			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.set(Calendar.MONTH,1);
			fechaInicial.set(Calendar.DAY_OF_MONTH, 14);
			fechaInicial.set(Calendar.HOUR_OF_DAY, 2);
			
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.set(Calendar.MONTH,1);
			fechaFin.set(Calendar.DAY_OF_MONTH, 14);
			fechaFin.set(Calendar.HOUR_OF_DAY, 9);
			
			TbCelda celda = new TbCelda();
			celda.setVrCeldaTipo(Constant.TIPO_CELDA_CARRO);
			
			TbServicio servicio = new TbServicio();
			servicio.setNbServicioCilindraje(400);
			servicio.setDtServicioFechaini(fechaInicial.getTime());
			servicio.setDtServicioFechafin(fechaFin.getTime());
			servicio.setTbCelda(celda);
			
			
			PowerMockito.mockStatic(ConfigValService.class);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_DIA_C)).thenReturn(VALOR_DIA_C);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_HORA_C)).thenReturn(VALOR_HORA_C);
			
			//when
			Long valor = servicioService.calcularValor(servicio);
			
			//then
			assertEquals(valor, Long.valueOf(7000));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 */
	@Test
	public void calcularValorMotoCilindrajeTest() {
		try {
			//give
			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.set(Calendar.MONTH,1);
			fechaInicial.set(Calendar.DAY_OF_MONTH, 14);
			fechaInicial.set(Calendar.HOUR_OF_DAY, 2);
			
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.set(Calendar.MONTH,1);
			fechaFin.set(Calendar.DAY_OF_MONTH, 14);
			fechaFin.set(Calendar.HOUR_OF_DAY, 12);
			
			TbCelda celda = new TbCelda();
			celda.setVrCeldaTipo(Constant.TIPO_CELDA_MOTO);
			
			TbServicio servicio = new TbServicio();
			servicio.setNbServicioCilindraje(550);
			servicio.setDtServicioFechaini(fechaInicial.getTime());
			servicio.setDtServicioFechafin(fechaFin.getTime());
			servicio.setTbCelda(celda);
			
			PowerMockito.mockStatic(ConfigValService.class);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_DIA_M)).thenReturn(VALOR_DIA_M);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_HORA_M)).thenReturn(VALOR_HORA_M);
			PowerMockito.when(ConfigValService.findValue(Constant.VALOR_M_CILINDRAJE_MAYOR_500)).thenReturn(VALOR_M_CILINDRAJE_MAYOR_500);
			
			//when
			Long valor = servicioService.calcularValor(servicio);
			
			//then
			assertEquals(valor, Long.valueOf(6000));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	

}
