package main;

import Servicios.AppManager;
import datos.Excepcion.TablaException;
import datos.Excepcion.UIException;

public class Main {

	public static void main(String[] args) {
		AppManager app = new AppManager();
		try {
			app.iniciarAplicacion();
		}catch (TablaException e) {
			//Printeo para la consola tambien.
			e.printStackTrace();
			System.err.println("Error al inicializar la base de datos: " + e.getMessage());
		} catch (UIException e) {
			e.printStackTrace();
			System.err.println("Error al iniciar la interfaz de usuario: " + e.getMessage());
		}
	}
}
