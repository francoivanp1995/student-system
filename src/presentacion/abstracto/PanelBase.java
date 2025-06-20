package presentacion.abstracto;

import java.awt.BorderLayout;

import javax.swing.*;

import presentacion.PanelBotonera;
import presentacion.PanelManager;

public abstract class PanelBase extends JPanel implements MensajeUI{

	protected PanelManager panelManager;
	protected PanelBotonera panelBotonera;
	
	
	public PanelBase() {
		
	}
	
	public PanelBase(PanelManager panelManager) {
		this.panelManager = panelManager;
		setLayout(new BorderLayout());
		System.out.println("PanelBase antes de setUIComponentes");
//		setUIComponentes();
	}
	
	protected void setBotonera(PanelBotonera panelBotonera) {
		this.panelBotonera = panelBotonera;
		add(panelBotonera, BorderLayout.SOUTH);
	}

	protected abstract JPanel construirPanel();
	protected abstract void setUIComponentes();
	protected abstract JPanel crearTitulo();

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarAdvertencia(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
	}

	public void mostrarInfo(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	}
}
