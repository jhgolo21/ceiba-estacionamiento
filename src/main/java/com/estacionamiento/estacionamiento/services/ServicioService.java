package com.estacionamiento.estacionamiento.services;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento.dto.ServicioDto;
import com.estacionamiento.estacionamiento.entity.TbServicio;
import com.estacionamiento.estacionamiento.repository.ServicioRepository;
import com.estacionamiento.estacionamiento.util.Constant;

@Service("servicioService")
public class ServicioService {
	
	@Autowired
	@Qualifier("repositorioServicio")
	private ServicioRepository servicioRepository;
	
	
	/**
	 * funcion que registra el servicio
	 * @param servicio
	 * @return
	 */
	@Transactional
	public String registrarServicio(ServicioDto servicioDto) {
		try {
			TbServicio servicio = new TbServicio(servicioDto);
			//TODO hacer funcion de consulta del objeto tabla.
			servicio.setDtServicioFechafin(new Date());
			//Validación de la placa y dia de la semana
			if(servicio.getVrServicioPlaca().substring(0, 1).equalsIgnoreCase("A")) {
				Calendar calendar = Calendar.getInstance();
				int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
				if(diaSemana == Calendar.SUNDAY || diaSemana == Calendar.MONDAY) {
					return Constant.NO_INGRESO_PLACA_A;
				}
			}
			servicio.setNbServicioValor(calcularValor(servicio));
			servicioRepository.save(servicio);
		} catch (Exception e) {
			System.out.println(e +" "+e.getMessage()+" "+e.getCause());
			return e.getMessage();
		}
		return Constant.OPERACION_EXITOSA;
	}
	
	
	public int calcularValor(TbServicio servicio) {
		 int diferencia; 
		 int dias; 
		 int horas; 
		 int horaRestantes;
		 int valorServicio = 0;
		 long cilindraje;
		 String tipoVehiculo; 
		 try {
			 tipoVehiculo = servicio.getTbCelda().getVrCeldaTipo();
			 cilindraje = servicio.getNbServicioCilindraje();
			 diferencia = (int)(servicio.getDtServicioFechaini().getTime() - servicio.getDtServicioFechafin().getTime());
			 dias = diferencia/86400;
			 horas = diferencia/3600;
			 //cobrar por dia
			 if(horas > 24) {
				 //se calculan las horas restantes del ultimo dia
				 horaRestantes = horas - (24*dias);
				 if(tipoVehiculo.equalsIgnoreCase(Constant.TIPO_MOTO)) {
					 valorServicio = ConfigValService.findValue(Constant.VALOR_DIA_M) * dias;
					 valorServicio = valorServicio + (ConfigValService.findValue(Constant.VALOR_HORA_M) * horaRestantes);					 
				 }else if(tipoVehiculo.equalsIgnoreCase(Constant.TIPO_CARRO)) {
					 valorServicio = ConfigValService.findValue(Constant.VALOR_DIA_C) * dias;
					 valorServicio = valorServicio + (ConfigValService.findValue(Constant.VALOR_HORA_C) * horaRestantes);
				 }
			 }else if(horas < 24) {
				 if(tipoVehiculo.equalsIgnoreCase(Constant.TIPO_MOTO)) {
					 valorServicio = horas > 8 ? ConfigValService.findValue(Constant.VALOR_DIA_M) : (ConfigValService.findValue(Constant.VALOR_HORA_M) * horas);
				 }else if(tipoVehiculo.equalsIgnoreCase(Constant.TIPO_CARRO)) {
					 valorServicio = horas > 8 ? ConfigValService.findValue(Constant.VALOR_DIA_C) : (ConfigValService.findValue(Constant.VALOR_HORA_C) * horas);
				 }
			 }
			 valorServicio = cilindraje > 500 && tipoVehiculo.equalsIgnoreCase(Constant.TIPO_MOTO) ? valorServicio + 2000 : valorServicio;
		 } catch (Exception e) {
			System.out.println(e);
		}
		return valorServicio;
	}

}
