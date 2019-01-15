package com.estacionamiento.estacionamiento.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estacionamiento.estacionamiento.entity.TbServicio;

@Repository("repositorioServicio")
public interface ServicioRepository extends JpaRepository<TbServicio, Serializable>{
	
	public TbServicio findByNbServicioId(long id);
	
	public List<TbServicio> findByVrServicioEstado(String estado);

}
