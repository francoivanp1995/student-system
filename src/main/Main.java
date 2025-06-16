package main;

import datos.Excepcion.PanelException;
import presentacion.FrameInicio;
import presentacion.PanelManager;

public class Main {

	public static void main(String[] args) {

		PanelManager panelManager = new PanelManager();
		new FrameInicio(panelManager);

		try {
			panelManager.iniciar();
		} catch (PanelException e) {
			e.printStackTrace();
		}

		panelManager.showFrame();
	}
}
