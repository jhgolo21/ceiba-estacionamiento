package com.estacionamiento.estacionamiento.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estacionamiento.estacionamiento.entity.TbServicio;

@Repository("repositorioServicio")
public interface ServicioRepository extends JpaRepository<TbServicio, Serializable>{

}
