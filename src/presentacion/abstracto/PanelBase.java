package presentacion.abstracto;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import datos.interfaz.InterfacePanelBase;
import presentacion.PanelBotonera;
import presentacion.PanelManager;

public abstract class PanelBase extends JPanel implements InterfacePanelBase {

	protected PanelManager panelManager;
	protected PanelBotonera panelBotonera;

	public PanelBase() {
		
	}
	
	public PanelBase(PanelManager panelManager) {
		this.panelManager = panelManager;
		setLayout(new BorderLayout());

	}

	//Lo habia usado para un caso, luego me deshice del caso. Chequear si es posible eliminar
//	public PanelBase(PanelManager panelManager,LayoutManager layout) {
//		this.panelManager = panelManager;
//		setLayout(layout);
//	}
	
	protected void setBotoneraEnPanel(PanelBotonera panelBotonera) {
		this.panelBotonera = panelBotonera;
		agregarBotonABotonera();
	}

	protected abstract void agregarBotonABotonera();
	protected abstract void setUIComponentesBase();
	protected abstract JPanel panelSuperior();
	protected abstract JPanel panelCentral();
	protected abstract void setListener(ActionListener listener);


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
