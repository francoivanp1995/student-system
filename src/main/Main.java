package main;

import Servicios.AppManager;
import datos.Excepcion.TablaException;
import datos.Excepcion.UIException;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		AppManager app = new AppManager();
		try {
			app.iniciarAplicacion();
		} catch (TablaException e) {
			e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Error al inicializar la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UIException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al iniciar la interfaz de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error inesperado abriendo la apliaccion. " , "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
