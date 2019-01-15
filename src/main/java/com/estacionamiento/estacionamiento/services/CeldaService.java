package com.estacionamiento.estacionamiento.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento.dto.CeldaDto;
import com.estacionamiento.estacionamiento.entity.TbCelda;
import com.estacionamiento.estacionamiento.repository.CeldaRepository;

@Service("celdaService")
public class CeldaService {
	
	@Autowired
	@Qualifier("repositoriocCelda")
	private CeldaRepository celdaRepository;
	
	/**
	 * funcion que retorna la lista de celdas que tiene el parqueadero
	 * @return
	 */
	public List<CeldaDto> listaCeldas(){
		List<TbCelda> lista = new ArrayList<>();
		List<CeldaDto> celdaDtos = new ArrayList<>();
		try {
			lista = celdaRepository.findAll();
			celdaDtos = CeldaDto.getInstanceList(lista);
		} catch (Exception e) {
			System.out.println(e);
		}
		return celdaDtos;
	}
	
	public boolean saveCelda(TbCelda celda) {
		try {
			celdaRepository.save(celda);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	

}
