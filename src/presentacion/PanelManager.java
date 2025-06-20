package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import datos.Excepcion.PanelException;
import datos.RolUsuario;
import datos.interfaz.ObtenerPanel;

public class PanelManager implements ObtenerPanel {

	private JFrame frame;
	private PanelInicio panelInicio;
	private PanelPrincipalAdmin panelPrincipalAdmin;
	private PanelPrincipalAlumno panelPrincipalAlumno;
	private PanelPrincipalProfesor panelPrincipalProfesor;

	public PanelManager() {

	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void iniciar() throws PanelException {
		System.out.println("Aca llego en PanelManager antes de panelInicio = new PanelINicio()");
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

	public void mostrarPanelPorRol(RolUsuario rol) throws PanelException {
		System.out.println("Estoy en el mostrarPanelPorRol");
		switch (rol) {
			case ADMINISTRADOR -> {
				if (panelPrincipalAdmin == null)
					panelPrincipalAdmin = new PanelPrincipalAdmin(this);
				mostrarPanel(panelPrincipalAdmin);
			}
			case PROFESOR -> {
				if (panelPrincipalProfesor == null)
					panelPrincipalProfesor = new PanelPrincipalProfesor(this);
				mostrarPanel(panelPrincipalProfesor);
			}
			case ALUMNO -> {
				if (panelPrincipalAlumno == null)
					panelPrincipalAlumno = new PanelPrincipalAlumno(this);
				mostrarPanel(panelPrincipalAlumno);
			}
		}
	}

	public void showFrame() {
		frame.setVisible(true);
	}

	@Override
	public PanelInicio getPanelInicio() {
		return panelInicio;
	}

	@Override
	public PanelPrincipalAdmin getPanelPrincipalAdmin() {
		return panelPrincipalAdmin;
	}

	@Override
	public PanelPrincipalAlumno getPanelPrincipalAlumno() {
		return panelPrincipalAlumno;
	}

	@Override
	public PanelPrincipalProfesor getPanelPrincipalProfesor() {
		return panelPrincipalProfesor;
	}
}
