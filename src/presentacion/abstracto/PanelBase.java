package presentacion.abstracto;

import java.awt.BorderLayout;

import javax.swing.*;

import presentacion.PanelBotonera;
import presentacion.PanelManager;

public abstract class PanelBase extends JPanel{

	protected PanelManager panelManager;
	protected PanelBotonera panelBotonera;
	
	
	public PanelBase() {
		
	}
	
	public PanelBase(PanelManager panelManager) {
		this.panelManager = panelManager;
		setLayout(new BorderLayout());
		setUIComponentes();
		construirPanel();
	}
	
	protected void setBotonera(PanelBotonera panelBotonera) {
		this.panelBotonera = panelBotonera;
		add(panelBotonera, BorderLayout.SOUTH);
	}

	protected abstract JPanel construirPanel();
	protected abstract void setUIComponentes();
	protected abstract JPanel crearTitulo();

	protected void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	protected void mostrarAdvertencia(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
	}

	protected void mostrarInfo(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	}
}
