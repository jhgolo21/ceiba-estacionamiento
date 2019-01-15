package com.estacionamiento.estacionamiento.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_celda", schema="estacionamientobd")
public class TbCelda {
	
	@Id
	@Column(name="nb_celda_id")
	private long nbCeldaId;
	
	@Column(name="vr_celda_codigo")
	private String vrCeldaCodigo;
	
	@Column(name="vr_celda_tipo")
	private String vrCeldaTipo;
	
	@Column(name="vr_celda_estado")
	private String vrCeldaEstado;
	
	@Column(name="dt_auditoria_fecha")
	private Date dtAuditoriaFecha;

	public TbCelda() {
		super();
	}

	public TbCelda(long vrCeldaId, String vrCeldaCodigo, String vrCeldaTipo, String vrCeldaEstado,
			Date dtAuditoriaFecha) {
		super();
		this.nbCeldaId = vrCeldaId;
		this.vrCeldaCodigo = vrCeldaCodigo;
		this.vrCeldaTipo = vrCeldaTipo;
		this.vrCeldaEstado = vrCeldaEstado;
		this.dtAuditoriaFecha = dtAuditoriaFecha;
	}

	public long getNbCeldaId() {
		return nbCeldaId;
	}

	public void setNbCeldaId(long vrCeldaId) {
		this.nbCeldaId = vrCeldaId;
	}

	public String getVrCeldaCodigo() {
		return vrCeldaCodigo;
	}

	public void setVrCeldaCodigo(String vrCeldaCodigo) {
		this.vrCeldaCodigo = vrCeldaCodigo;
	}

	public String getVrCeldaTipo() {
		return vrCeldaTipo;
	}

	public void setVrCeldaTipo(String vrCeldaTipo) {
		this.vrCeldaTipo = vrCeldaTipo;
	}

	public String getVrCeldaEstado() {
		return vrCeldaEstado;
	}

	public void setVrCeldaEstado(String vrCeldaEstado) {
		this.vrCeldaEstado = vrCeldaEstado;
	}

	public Date getDtAuditoriaFecha() {
		return dtAuditoriaFecha;
	}

	public void setDtAuditoriaFecha(Date dtAuditoriaFecha) {
		this.dtAuditoriaFecha = dtAuditoriaFecha;
	}

}
