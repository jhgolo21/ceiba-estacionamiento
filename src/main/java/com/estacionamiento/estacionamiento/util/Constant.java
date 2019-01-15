package com.estacionamiento.estacionamiento.util;

/**
 * clase que contiene las constantes
 * @author jhonatan.gomez
 *
 */
public class Constant {
	
	
	//Tipos de vehiculo
	public static final String TIPO_MOTO = "M";
	public static final String TIPO_CARRO = "C";

	
	//Valores configurados en la base de datos
	public static final String VALOR_HORA_C = "VALOR_HORA_C";
	public static final String VALOR_HORA_M = "VALOR_HORA_M";
	public static final String VALOR_DIA_C = "VALOR_DIA_C";
	public static final String VALOR_DIA_M = "VALOR_DIA_M";
	public static final String VALOR_M_CILINDRAJE_MAYOR_500 = "VALOR_M_CILINDRAJE_MAYOR_500";
	
	//mensajes
	public static final String OPERACION_EXITOSA = "Operación exitosa";
	public static final String ERROR_OPERACION = "Error al realizar la transacción";
	public static final String NO_INGRESO_PLACA_A = "No esta autorizado a ingresar porque no está en un dia hábil";
	
	
	
	
	private Constant() {}


}
