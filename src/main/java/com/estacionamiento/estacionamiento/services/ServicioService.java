package com.estacionamiento.estacionamiento.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento.dto.ServicioDto;
import com.estacionamiento.estacionamiento.entity.TbServicio;
import com.estacionamiento.estacionamiento.repository.CeldaRepository;
import com.estacionamiento.estacionamiento.repository.ServicioRepository;
import com.estacionamiento.estacionamiento.util.Constant;

@Service("servicioService")
public class ServicioService {
	
	@Autowired
    @Qualifier("repositorioServicio")
	private ServicioRepository servicioRepository;
	
	@Autowired
    @Qualifier("repositoriocCelda")
	private CeldaRepository celdaRepository;

	/**
	 * funcion que registra el servicio
	 * @param servicio
	 * @return
	 */
	@Transactional
	public String registrarServicio(ServicioDto servicioDto) {
		try {
			TbServicio servicio = new TbServicio(servicioDto);
			//Validación de la placa y dia de la semana
			if(servicio.getVrServicioPlaca().substring(0, 1).equalsIgnoreCase("A")) {
				Calendar fecha = Calendar.getInstance();
				fecha.setTime(servicioDto.getFechaInicio());
				int diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
				if(diaSemana == Calendar.SUNDAY || diaSemana == Calendar.MONDAY) {
					return Constant.NO_INGRESO_PLACA_A;
				}
			}
			servicio.setTbCelda(celdaRepository.findByNbCeldaId(servicioDto.getCelda()));
			servicio.getTbCelda().setVrCeldaEstado(Constant.ESTADO_CEL_INICIO);
			servicio.setVrServicioEstado(Constant.ESTADO_INICIO);
			celdaRepository.save(servicio.getTbCelda());
			servicioRepository.save(servicio);
		}catch (Exception e) {
			System.out.println(e +" "+e.getMessage()+" "+e.getCause());
			return e.getMessage();
		}
		return Constant.OPERACION_EXITOSA;
	}
	
	
	/**
	 * funcion que permite finilizar el servicio
	 * @param idServicio
	 * @return
	 */
	@Transactional
	public String finServicio(long idServicio) {
		try {
			TbServicio servicio;
			servicio = servicioRepository.findByNbServicioId(idServicio);
			Calendar fecha = Calendar.getInstance();
			servicio.setDtServicioFechafin(fecha.getTime());
			servicio.setNbServicioValor(calcularValor(servicio));
			servicio.setVrServicioEstado(Constant.ESTADO_FIN);
			servicio.getTbCelda().setVrCeldaEstado(Constant.ESTADO_CEL_FIN);
			celdaRepository.save(servicio.getTbCelda());
			servicioRepository.save(servicio);
		} catch (Exception e) {
			System.out.println(e +" "+e.getMessage()+" "+e.getCause());
			return e.getMessage();
		}
		return Constant.OPERACION_EXITOSA;
	}
	
	public List<ServicioDto> serviciosActivos(){
		List<ServicioDto> listaDto = new ArrayList<>();
		List<TbServicio> listaServicio = new ArrayList<>();
		try {
			listaServicio = servicioRepository.findByVrServicioEstado(Constant.ESTADO_INICIO);
			listaDto = ServicioDto.getInstanceList(listaServicio);
		} catch (Exception e) {
			System.out.println(e +" "+e.getMessage()+" "+e.getCause());
		}
		return listaDto;
	}
	
	
	//TODO: falta logica para calcular minutos
	public Long calcularValor(TbServicio servicio) {
		 int diferencia; 
		 int dias; 
		 int horas; 
		 int horaRestantes;
		 int valorServicio = 0;
		 long cilindraje;
		 String tipoVehiculo;
		 int valorDiaM;
		 int valorDiaC;
		 int valorHoraM;
		 int valorHoraC;
		 int valorCilindraje;
		 try {
			 tipoVehiculo = servicio.getTbCelda().getVrCeldaTipo();
			 cilindraje = servicio.getNbServicioCilindraje();
			 diferencia = (int)(servicio.getDtServicioFechafin().getTime() - servicio.getDtServicioFechaini().getTime());
			 dias = diferencia/86400000;
			 horas = diferencia/3600000;
			 valorDiaM = ConfigValService.findValue(Constant.VALOR_DIA_M);
			 valorDiaC = ConfigValService.findValue(Constant.VALOR_DIA_C);
			 valorHoraM = ConfigValService.findValue(Constant.VALOR_HORA_M);
			 valorHoraC = ConfigValService.findValue(Constant.VALOR_HORA_C);
			 valorCilindraje = ConfigValService.findValue(Constant.VALOR_M_CILINDRAJE_MAYOR_500);
			 //cobrar por dia
			 if(horas > 24) {
				 //se calculan las horas restantes del ultimo dia
				 horaRestantes = horas - (24*dias);
				 switch (tipoVehiculo) {
					case Constant.TIPO_CELDA_MOTO:
						 valorServicio = valorDiaM * dias;
						 valorServicio = valorServicio + (valorHoraM * horaRestantes);	
						break;
					case Constant.TIPO_CELDA_CARRO:
						 valorServicio = valorDiaC * dias;
						 valorServicio = valorServicio + (valorHoraC * horaRestantes);
						break;
					default:
						break;
				}
			 }else if(horas < 24) {
				 switch (tipoVehiculo) {
					case Constant.TIPO_CELDA_MOTO:
						valorServicio = horas > 8 ? valorDiaM : (valorHoraM * horas);
						break;
					case Constant.TIPO_CELDA_CARRO:
						valorServicio = horas > 8 ? valorDiaC : (valorHoraC * horas);
						break;
					default:
						break;
				}
			 }
			 valorServicio = cilindraje > 500 && tipoVehiculo.equalsIgnoreCase(Constant.TIPO_CELDA_MOTO) ? valorServicio + valorCilindraje : valorServicio;
		 } catch (Exception e) {
			System.out.println(e);
		}
		return Long.valueOf(valorServicio);
	}

}
