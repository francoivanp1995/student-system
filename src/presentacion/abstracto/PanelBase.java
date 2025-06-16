package presentacion.abstracto;

import java.awt.BorderLayout;

import javax.swing.JPanel;

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
	}
	
	protected void setBotonera(PanelBotonera panelBotonera) {
		this.panelBotonera = panelBotonera;
		add(panelBotonera, BorderLayout.SOUTH);
	}
	
	protected abstract void construirPanel();
	protected abstract void setUIComponentes();
	
}
