package com.estacionamiento.estacionamiento.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estacionamiento.estacionamiento.entity.TbConfigVal;
import com.estacionamiento.estacionamiento.repository.ConfigValRepository;

@Service("configValService")
public class ConfigValService {
	
	@Autowired
	@Qualifier("repositorioConfigVal")
	private ConfigValRepository configValRepository;
	
	private static Map<String, Long> valoresConfigurados = new HashMap<>();
	
	public static Map<String, Long> getValoresConfigurados() {
		return valoresConfigurados;
	}

	public static void setValoresConfigurados(Map<String, Long> valoresConfigurados) {
		ConfigValService.valoresConfigurados = valoresConfigurados;
	}

	@Transactional
	public void cargarValores(){
		List<TbConfigVal> lista = new ArrayList<>();
		try {
			lista = configValRepository.findAll();
			if(!lista.isEmpty()) {
				for (TbConfigVal tbConfigVal : lista) {
					valoresConfigurados.put(tbConfigVal.getVrConfigValNombre(), tbConfigVal.getNbConfigValValor());
				}
			}
		} catch (Exception e) {
			System.out.println(e +" "+e.getMessage()+" "+e.getCause());
		}
	}
	
	public static int findValue(String nombreValor) {
		int valor = 0;
		try {
			Long val = valoresConfigurados.get(nombreValor);
			valor = val != null ? val.intValue() : 0;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		return valor;
	}
	
	
	
	
	

}
