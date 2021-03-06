package com.estacionamiento.estacionamiento.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estacionamiento.estacionamiento.entity.TbCelda;

@Repository("repositoriocCelda")
public interface CeldaRepository extends JpaRepository<TbCelda, Serializable>{
	
	public TbCelda findByNbCeldaId (long id);
	 
	public List<TbCelda> findByVrCeldaTipoAndVrCeldaEstado (String tipo, String estado);
}
