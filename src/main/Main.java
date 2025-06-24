package main;

import datos.Excepcion.TablaException;
import datos.Excepcion.UIException;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		try {
			AppManager app = new AppManager();
		} catch (TablaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al iniciar tablas", JOptionPane.ERROR_MESSAGE);
		} catch (UIException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al iniciar interfaz", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error inesperado desde el main: " + e.getMessage(), "Error general", JOptionPane.ERROR_MESSAGE);
		}
	}
}
