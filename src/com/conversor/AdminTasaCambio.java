package com.conversor;

import java.util.HashMap;
import java.util.Map;

//Implementa el metodo convertir
public class AdminTasaCambio implements AdminConversor {

	private final Map<String, Map<String, Double>> tipoCambio = new HashMap<>();

	public AdminTasaCambio() {
		configurarValores();
	}
	
	
	/*
	 * Estructura de Datos Map que contiene los factores de conversion
	 * de cada moneda
	 */
	
	@Override
	public void configurarValores() {
		
		Map<String, Double> cop = new HashMap<>();
        cop.put("USD", 0.00024);
        cop.put("EUR", 0.00022);
        cop.put("GBP", 0.00019);
        cop.put("JPY", 0.034);        
        cop.put("KRW", 0.31);
        tipoCambio.put("COP", cop);
        
		Map<String, Double> usd = new HashMap<>();        
        usd.put("EUR", 0.91);
        usd.put("GBP", 0.78);
        usd.put("JPY", 141.88);        
        usd.put("KRW", 1299.60);
        usd.put("COP", 4090.52);
        tipoCambio.put("USD", usd);
        
		Map<String, Double> gbp = new HashMap<>();
        gbp.put("USD", 1.27);
        gbp.put("EUR", 1.16);
        gbp.put("JPY", 180.89);        
        gbp.put("KRW", 1656.53);
        gbp.put("COP", 5212.73);
        tipoCambio.put("GBP", gbp);  

		Map<String, Double> eur = new HashMap<>();
        eur.put("USD", 1.10);
        eur.put("GBP", 0.86);
        eur.put("JPY", 156.12);        
        eur.put("KRW", 1429.78);
        eur.put("COP", 4499.65);
        tipoCambio.put("EUR", eur);
        
		Map<String, Double> jpy = new HashMap<>();
        jpy.put("USD", 0.0070);
        jpy.put("EUR", 0.0064);
        jpy.put("GBP", 0.0055);       
        jpy.put("KRW", 9.16);
        jpy.put("COP", 28.82);
        tipoCambio.put("JPY", jpy);
        
		Map<String, Double> krw = new HashMap<>();
        krw.put("USD", 0.00077);
        krw.put("EUR", 0.00070);
        krw.put("GBP", 0.0000060);
        krw.put("JPY", 0.11);        
        krw.put("COP", 3.15);
        tipoCambio.put("KRW", krw);        

	}
	
	
	/*
	 * Se obtiene la tasa de cambio para un determinado
	 * valor según las monedas elegidas.
	 */
	@Override
	public Double convertir(String moneda1, String moneda2, int valor) {
		
		Map<String, Double> cambioMoneda = this.tipoCambio.get(moneda1);
        return valor * cambioMoneda.get(moneda2);

	}

}
