package presentacion;

import presentacion.admin.ControladorPanelPrincipalGestionar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import Servicios.ServicioAdmin;
import datos.CursoTableModel;
import datos.Excepcion.PanelException;
import datos.Gestionar;
import datos.RolUsuario;
import datos.UsuarioTableModel;
import datos.interfaz.ObtenerPanel;
import presentacion.admin.ControladorPanelPrincipalAdmin;
import presentacion.admin.PanelPrincipalAdmin;
import presentacion.admin.PanelPrincipalGestionar;
import presentacion.alumno.PanelPrincipalAlumno;
import presentacion.profesor.PanelPrincipalProfesor;

public class PanelManager implements ObtenerPanel {

	private JFrame frame;
	private PanelInicio panelInicio;
	private PanelPrincipalAdmin panelPrincipalAdmin;
	private PanelPrincipalAlumno panelPrincipalAlumno;
	private PanelPrincipalProfesor panelPrincipalProfesor;
	private PanelPrincipalGestionar panelPrincipalGestionarCurso;
	private PanelPrincipalGestionar panelGestionarUsuario;

	//para probar
	private ServicioAdmin servicioAdmin;

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
					servicioAdmin = new ServicioAdmin(panelPrincipalAdmin, this);
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
		System.out.println("Entró a mostrarPanelGestionar con: " + gestion);
		if (servicioAdmin == null) {
			System.out.println("servicioAdmin ES NULL !!!");
			throw new PanelException("servicioAdmin no ha sido inicializado");
		}
		switch (gestion) {
			case CURSO -> {
				if (panelPrincipalGestionarCurso == null) {
					AbstractTableModel modelo = new CursoTableModel();
					panelPrincipalGestionarCurso = new PanelPrincipalGestionar(this, modelo, "Gestión de Cursos");
					new ControladorPanelPrincipalGestionar(panelPrincipalGestionarCurso, servicioAdmin, Gestionar.CURSO,RolUsuario.ADMINISTRADOR);
				}
				System.out.println("Estoy dentro del case curso, luego del if para mostrar Panel principal gestionar curso");
				mostrarPanel(panelPrincipalGestionarCurso);
				System.out.println("Estoy dentro del case curso, LUEGO DE MOSTRAR PANEL");

			}
			case USUARIO -> {
				if (panelGestionarUsuario == null) {
					AbstractTableModel modelo = new UsuarioTableModel();
					panelGestionarUsuario = new PanelPrincipalGestionar(this, modelo, "Gestión de Usuarios");
					new ControladorPanelPrincipalGestionar(panelGestionarUsuario, servicioAdmin, Gestionar.USUARIO,RolUsuario.ADMINISTRADOR);
				}
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
	public PanelPrincipalGestionar getPanelGestionarCurso() {
		return null;
	}

	@Override
	public PanelPrincipalGestionar getPanelGestionarUsuario() {
		return null;
	}
}
