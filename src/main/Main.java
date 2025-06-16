package main;

import datos.Excepcion.InitException;
import datos.Excepcion.PanelException;
import datos.Init;
import presentacion.FrameInicio;
import presentacion.PanelManager;

public class Main {

	public static void main(String[] args) {

		try {
			Init init = new Init();
			init.iniciar();
		} catch (Exception e){
			System.out.println("Error al iniciar componentes");
		}

	}
}
