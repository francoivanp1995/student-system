package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import datos.Excepcion.PanelDisplayException;
import presentacion.admin.PanelInicio;

public class PanelManager {

	private JFrame frame;
	private String nombreSistema = "SISTEMA DE ALUMNOS";
	private PanelInicio panelInicio;
	
	public PanelManager() {
		this.frame = frame;
	}
	
	public void setFrame() {
		frame = new JFrame(nombreSistema);
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addPanel() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
    private void mostrarPanel(JPanel panel) throws PanelDisplayException {
        try {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panel);
            frame.revalidate();
            frame.repaint();
        } catch (Exception e) {
            throw new PanelDisplayException("Error al mostrar un panel", e);
        }
    }
}
