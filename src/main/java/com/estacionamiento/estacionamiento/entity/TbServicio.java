package com.estacionamiento.estacionamiento.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.estacionamiento.estacionamiento.dto.ServicioDto;

@Entity
@Table(name="tb_servicio", schema="estacionamientobd")
public class TbServicio {
	
	@Id
	@Column(name="nb_servicio_id")
	private long nbServicioId;
	
	@Column(name="vr_servicio_placa")
	private String vrServicioPlaca;
	
	@Column(name="nb_servicio_cilindraje")
	private long nbServicioCilindraje;
	
	@Column(name="vr_servicio_estado")
	private String vrServicioEstado;
	
	@Column(name="dt_servicio_fechaini")
	private Date dtServicioFechaini;
	
	@Column(name="dt_servicio_fechafin")
	private Date dtServicioFechafin;
	
	@Column(name="vr_servicio_tiempoCal")
	private String vrServicioTiempoCal;
	
	@Column(name="nb_servicio_valor")
	private Long nbServicioValor;
	
	@ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name="nb_celda_id")
	private TbCelda tbCelda;

	public TbServicio() {
		super();
	}

	public TbServicio(ServicioDto servicioDto) {
		this.nbServicioId = servicioDto.getId();
		this.vrServicioPlaca = servicioDto.getPlaca();
		this.nbServicioCilindraje = servicioDto.getCilindraje();
		this.vrServicioEstado = servicioDto.getEstado();
		this.dtServicioFechaini = servicioDto.getFechaInicio();
		this.dtServicioFechafin = servicioDto.getFechaFin();
		this.vrServicioTiempoCal = servicioDto.getTiempoCalcularo();
		this.nbServicioValor = servicioDto.getValorServicio();
		TbCelda celda = new TbCelda();
		celda.setNbCeldaId(servicioDto.getCelda());
		this.tbCelda = celda;
	}

	public long getNbServicioId() {
		return nbServicioId;
	}

	public void setNbServicioId(long nbServicioId) {
		this.nbServicioId = nbServicioId;
	}

	public String getVrServicioPlaca() {
		return vrServicioPlaca;
	}

	public void setVrServicioPlaca(String vrServicioPlaca) {
		this.vrServicioPlaca = vrServicioPlaca;
	}

	public long getNbServicioCilindraje() {
		return nbServicioCilindraje;
	}

	public void setNbServicioCilindraje(long nbServicioCilindraje) {
		this.nbServicioCilindraje = nbServicioCilindraje;
	}

	public String getVrServicioEstado() {
		return vrServicioEstado;
	}

	public void setVrServicioEstado(String vrServicioEstado) {
		this.vrServicioEstado = vrServicioEstado;
	}

	public Date getDtServicioFechaini() {
		return dtServicioFechaini;
	}

	public void setDtServicioFechaini(Date dtServicioFechaini) {
		this.dtServicioFechaini = dtServicioFechaini;
	}

	public Date getDtServicioFechafin() {
		return dtServicioFechafin;
	}

	public void setDtServicioFechafin(Date dtServicioFechafin) {
		this.dtServicioFechafin = dtServicioFechafin;
	}

	public String getVrServicioTiempoCal() {
		return vrServicioTiempoCal;
	}

	public void setVrServicioTiempoCal(String vrServicioTiempoCal) {
		this.vrServicioTiempoCal = vrServicioTiempoCal;
	}

	public Long getNbServicioValor() {
		return nbServicioValor;
	}

	public void setNbServicioValor(Long nbServicioValor) {
		this.nbServicioValor = nbServicioValor;
	}

	public TbCelda getTbCelda() {
		return tbCelda;
	}

	public void setTbCelda(TbCelda tbCelda) {
		this.tbCelda = tbCelda;
	}
	
	

}
