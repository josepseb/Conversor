package com.conversor;

public interface AdminConversor {

	void configurarValores();

	Double convertir(String moneda1, String moneda2, int valor);

}
