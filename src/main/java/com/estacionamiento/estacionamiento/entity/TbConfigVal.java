package com.estacionamiento.estacionamiento.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_CONFIGVAL", schema="estacionamientobd")
public class TbConfigVal {
	
	@Id
	@Column(name="nb_configval_id")
	private long nbConfigValId;
	
	@Column(name="vr_configval_nombre")
	private String vrConfigValNombre;
	
	@Column(name="nb_configval_valor")
	private long nbConfigValValor;
	
	@Column(name="dt_configval_update")
	private Date dtConfigValUpdate;

	public TbConfigVal() {
		super();
	}

	public long getNbConfigValId() {
		return nbConfigValId;
	}

	public void setNbConfigValId(long nbConfigValId) {
		this.nbConfigValId = nbConfigValId;
	}

	public String getVrConfigValNombre() {
		return vrConfigValNombre;
	}

	public void setVrConfigValNombre(String vrConfigValNombre) {
		this.vrConfigValNombre = vrConfigValNombre;
	}

	public long getNbConfigValValor() {
		return nbConfigValValor;
	}

	public void setNbConfigValValor(long nbConfigValValor) {
		this.nbConfigValValor = nbConfigValValor;
	}

	public Date getDtConfigValUpdate() {
		return dtConfigValUpdate;
	}

	public void setDtConfigValUpdate(Date dtConfigValUpdate) {
		this.dtConfigValUpdate = dtConfigValUpdate;
	}

}
