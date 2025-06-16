package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import datos.Excepcion.PanelException;

public class PanelManager {

	private JFrame frame;
	private PanelInicio panelInicio;

	public PanelManager() {
		// Frame se setea externamente desde FrameInicio
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void iniciar() throws PanelException {
		panelInicio = new PanelInicio(this);
		mostrarPanel(panelInicio);
	}

	private void mostrarPanel(JPanel panel) throws PanelException {
		try {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(panel);
			frame.revalidate();
			frame.repaint();
		} catch (Exception e) {
			throw new PanelException(e);
		}
	}

	public void showFrame() {
		frame.setVisible(true);
	}
}
