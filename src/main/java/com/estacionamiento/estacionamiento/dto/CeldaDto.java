package com.estacionamiento.estacionamiento.dto;

import java.util.ArrayList;
import java.util.List;

import com.estacionamiento.estacionamiento.entity.TbCelda;

public class CeldaDto {
	
	private long idCelda;
	private String codigo;
	private String tipo;
	private String estado;
	
	
	public CeldaDto() {
		super();
	}

	
	public static List<CeldaDto> getInstanceList(List<TbCelda> listaCelda){
		List<CeldaDto> celdaDtoList = new ArrayList<>();
		try {
			for (TbCelda celda : listaCelda) {
				CeldaDto celdaDto = new CeldaDto();
				celdaDto.setCodigo(celda.getVrCeldaCodigo());
				celdaDto.setEstado(celda.getVrCeldaEstado());
				celdaDto.setIdCelda(celda.getNbCeldaId());
				celdaDto.setTipo(celda.getVrCeldaTipo());
				celdaDtoList.add(celdaDto);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return celdaDtoList;
	}


	public long getIdCelda() {
		return idCelda;
	}


	public void setIdCelda(long idCelda) {
		this.idCelda = idCelda;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	

}
