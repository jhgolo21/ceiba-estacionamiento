package com.estacionamiento.estacionamiento.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.estacionamiento.estacionamiento.entity.TbServicio;

public class ServicioDto {
	
	private long id;
	private String placa;
	private long cilindraje;
	private String estado;
	private Date fechaInicio;
	private Date fechaFin;
	private String tiempoCalcularo;
	private Long valorServicio;
	private long celda;
	private String tipo;
	private String codigoCelda;
	
	public ServicioDto() {
		super();
	}

	
	
	public static ServicioDto  getInstance(TbServicio servicio) {
		ServicioDto  servicioDto = new ServicioDto();
		servicioDto.setCelda(servicio.getTbCelda().getNbCeldaId());
		servicioDto.setCilindraje(servicio.getNbServicioCilindraje());
		servicioDto.setEstado(servicio.getVrServicioEstado());
		servicioDto.setFechaFin(servicio.getDtServicioFechafin());
		servicioDto.setFechaInicio(servicio.getDtServicioFechaini());
		servicioDto.setPlaca(servicio.getVrServicioPlaca());
		servicioDto.setTiempoCalcularo(servicio.getVrServicioTiempoCal());
		servicioDto.setValorServicio(servicio.getNbServicioValor());
		servicioDto.setId(servicio.getNbServicioId());
		servicioDto.setCodigoCelda(servicio.getTbCelda().getVrCeldaCodigo());
		return servicioDto;
	}
	
	public static List<ServicioDto> getInstanceList(List<TbServicio> lista){
		List<ServicioDto> listaDto = new ArrayList<>();
		try {
			if(!lista.isEmpty()) {
				for (TbServicio servicio : lista) {
					ServicioDto  servicioDto = new ServicioDto();
					servicioDto.setCelda(servicio.getTbCelda().getNbCeldaId());
					servicioDto.setEstado(servicio.getVrServicioEstado());
					servicioDto.setCilindraje(servicio.getNbServicioCilindraje());
					servicioDto.setFechaInicio(servicio.getDtServicioFechaini());
					servicioDto.setPlaca(servicio.getVrServicioPlaca());
					servicioDto.setId(servicio.getNbServicioId());
					servicioDto.setTipo(servicio.getTbCelda().getVrCeldaTipo());
					servicioDto.setCodigoCelda(servicio.getTbCelda().getVrCeldaCodigo());
					servicioDto.setValorServicio(servicio.getNbServicioValor());
					listaDto.add(servicioDto);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return listaDto;
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


	public Long getValorServicio() {
		return valorServicio;
	}


	public void setValorServicio(Long valorServicio) {
		this.valorServicio = valorServicio;
	}


	public long getCelda() {
		return celda;
	}


	public void setCelda(long celda) {
		this.celda = celda;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getCodigoCelda() {
		return codigoCelda;
	}


	public void setCodigoCelda(String codigoCelda) {
		this.codigoCelda = codigoCelda;
	}
	
	
	
	

}
