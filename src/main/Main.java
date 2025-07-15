package main;

import Servicios.AppManager;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		try {
			AppManager app = new AppManager();
		} catch (Exception e) {
			//Printeo para la consola tambien
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error inesperado desde el main: " + e.getMessage(), "Error general", JOptionPane.ERROR_MESSAGE);
		}
	}
}
