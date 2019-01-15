package com.estacionamiento.estacionamiento.dto;

import java.util.Date;

import com.estacionamiento.estacionamiento.entity.TbServicio;

public class ServicioDto {
	
	private long id;
	private String placa;
	private long cilindraje;
	private String estado;
	private Date fechaInicio;
	private Date fechaFin;
	private String tiempoCalcularo;
	private long valorServicio;
	private long celda;
	
	
	public ServicioDto() {
		super();
	}


	public ServicioDto(long id, String placa, long cilindraje, String estado, Date fechaInicio, Date fechaFin,
			String tiempoCalcularo, long valorServicio, long celda) {
		super();
		this.id = id;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tiempoCalcularo = tiempoCalcularo;
		this.valorServicio = valorServicio;
		this.celda = celda;
	}
	
	public static ServicioDto  getInstance(TbServicio servicio) {
		ServicioDto  servicioDto = new ServicioDto();
		servicioDto.setCelda(servicio.getTbCelda().getVrCeldaId());
		servicioDto.setCilindraje(servicio.getNbServicioCilindraje());
		servicioDto.setEstado(servicio.getVrServicioEstado());
		servicioDto.setFechaFin(servicio.getDtServicioFechafin());
		servicioDto.setFechaInicio(servicio.getDtServicioFechaini());
		servicioDto.setPlaca(servicio.getVrServicioPlaca());
		servicioDto.setTiempoCalcularo(servicio.getVrServicioTiempoCal());
		servicioDto.setValorServicio(servicio.getNbServicioValor());
		servicioDto.setId(servicio.getNbServicioId());
		return servicioDto;
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public long getCilindraje() {
		return cilindraje;
	}


	public void setCilindraje(long cilindraje) {
		this.cilindraje = cilindraje;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getTiempoCalcularo() {
		return tiempoCalcularo;
	}


	public void setTiempoCalcularo(String tiempoCalcularo) {
		this.tiempoCalcularo = tiempoCalcularo;
	}


	public long getValorServicio() {
		return valorServicio;
	}


	public void setValorServicio(long valorServicio) {
		this.valorServicio = valorServicio;
	}


	public long getCelda() {
		return celda;
	}


	public void setCelda(long celda) {
		this.celda = celda;
	}
	
	
	
	
	
	
	
	

}
