package main;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		try {
			AppManager app = new AppManager();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error inesperado desde el main: " + e.getMessage(), "Error general", JOptionPane.ERROR_MESSAGE);
		}
	}
}
