package com.estacionamiento.estacionamiento.util;

/**
 * clase que contiene las constantes
 * @author jhonatan.gomez
 *
 */
public class Constant {
	
	
	//Tipo de celda
	public static final String TIPO_CELDA_MOTO = "MOTO";
	public static final String TIPO_CELDA_CARRO = "CARRO";

	
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
	
	//Estados Servicio
	public static final String ESTADO_INICIO = "INI_SER";
	public static final String ESTADO_FIN = "FIN_SER";
	
	//Estados Celdas
	public static final String ESTADO_CEL_INICIO = "OCUPADO";
	public static final String ESTADO_CEL_FIN = "DISPONIBLE";
	public static final String ESTADO_CEL_MANT = "MANTENIMIENTO";
	
	
	private Constant() {}


}
