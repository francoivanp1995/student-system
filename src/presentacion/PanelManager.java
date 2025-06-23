package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import datos.Excepcion.PanelException;
import datos.Gestionar;
import datos.RolUsuario;
import datos.interfaz.ObtenerPanel;

public class PanelManager implements ObtenerPanel {

	private JFrame frame;
	private PanelInicio panelInicio;
	private PanelPrincipalAdmin panelPrincipalAdmin;
	private PanelPrincipalAlumno panelPrincipalAlumno;
	private PanelPrincipalProfesor panelPrincipalProfesor;
	private PanelGestionarCurso panelGestionarCurso;
	private PanelGestionUsuario panelGestionarUsuario;

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
				if (panelPrincipalAdmin == null) {
					panelPrincipalAdmin = new PanelPrincipalAdmin(this);
					ControladorPanelPrincipalAdmin controlador = new ControladorPanelPrincipalAdmin(panelPrincipalAdmin,this);
					panelPrincipalAdmin.setListener(controlador);
				}
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

	public void mostrarPanelGestionar(Gestionar gestion) throws PanelException {
		switch (gestion){
			case CURSO -> {
				if(panelGestionarCurso == null)
					panelGestionarCurso = new PanelGestionarCurso(this);
				mostrarPanel(panelGestionarCurso);
			}
			case USUARIO -> {
				if(panelGestionarUsuario == null)
					panelGestionarUsuario = new PanelGestionUsuario(this);
				mostrarPanel(panelGestionarUsuario);
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

	@Override
	public PanelGestionarCurso getPanelGestionarCurso() {
		return panelGestionarCurso;
	}
}
