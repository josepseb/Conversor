package com.conversor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VistaConversor extends JFrame implements ActionListener {
	
	/*
	 * Declaración de los compononentes que tendrá la ventana principal
	 */
	
	JLabel moneda1, moneda2;
	JComboBox<String> combo1, combo2;
	//Lista de los items que tendrá los JComboBox
	ArrayList<String> monedas = new ArrayList<>(Arrays.asList("COP", "USD", "EUR", "GBP", "JPY", "KRW"));
	JButton convertir, salir;
	AdminTasaCambio adminTasaCambio = new AdminTasaCambio();

	public VistaConversor() {
		this.configurarComponentes();
		this.configurarFrame();
		this.adicionarComponentes();
		this.setVisible(true);

	}

	//Los componentes se agregan a la ventana
	private void adicionarComponentes() {
		this.add(moneda1);
		this.add(moneda2);
		this.add(combo1);
		this.add(combo2);
		this.add(convertir);
		this.add(salir);
	}

	//Cada componenete se instancia y se configura
	private void configurarComponentes() {
		moneda1 = new JLabel("De moneda1:");
		moneda1.setBounds(45, 20, 80, 35);
		combo1 = new JComboBox(monedas.toArray());
		combo1.setBounds(45, 50, 100, 30);

		moneda2 = new JLabel("Pasar a:");
		moneda2.setBounds(175, 30, 100, 15);
		combo2 = new JComboBox(monedas.toArray());
		combo2.setBounds(175, 50, 100, 30);

		convertir = new JButton();
		convertir.setText("Convertir");
		convertir.setBounds(45, 110, 100, 30);
		convertir.addActionListener(this);

		salir = new JButton();
		salir.setText("Salir");
		salir.setBounds(175, 110, 100, 30);
		salir.addActionListener(this);
	}

	//Configuración de la ventana principal
	private void configurarFrame() {
		this.setTitle("Conversor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(350, 250);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.orange);
	
	}


	//Cuadro de diálogo para obtener la cantidad a convertir
	private int obtenerCantidad(String moneda1, String moneda2) {
		Boolean valido = false;
		int cantidad = 0;

		while (!valido) {
			try {
				String entrada = JOptionPane.showInputDialog(null,
						"Ingresa la cantidad a convertir (" + moneda1 + "->" + moneda2 + ")", "Cantidad a convertir",
						JOptionPane.PLAIN_MESSAGE);

				if (entrada == null || entrada.equals("0")) {
					return cantidad;
				}

				cantidad = Integer.parseInt(entrada);
				valido = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un número válido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		return cantidad;
	}

	//Se da formato al valor obtenido de la conversión
	private void mostrarResultado(String cambio, Double valor) {
		Formatter f = new Formatter();
		String mensaje = String.valueOf(f.format("Tienes %s %.2f", cambio, valor));
		JOptionPane.showMessageDialog(null, mensaje);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object fuente = e.getSource();

		if (fuente.equals(convertir)) {
			if (combo1.getSelectedItem() == combo2.getSelectedItem()) {
				JOptionPane.showMessageDialog(this, "Selecciona otro tipo de moneda para convertir", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//Cuando se ejecuta el botón convertir se llama al metodo obtenerCantidad()
			int cantidad = this.obtenerCantidad((String) combo1.getSelectedItem(), (String) combo2.getSelectedItem());

			if (cantidad != 0) {
				Double valor = this.adminTasaCambio.convertir((String) combo1.getSelectedItem(),
						(String) combo2.getSelectedItem(), cantidad);

				this.mostrarResultado((String) combo2.getSelectedItem(), valor);
			}
		}

		if (fuente.equals(salir)) {
			this.dispose();
			System.exit(0);
		}
	}

}
