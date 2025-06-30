package presentacion;

import Servicios.ServicioAlumno;
import Servicios.ServicioUsuario;
import datos.*;
import presentacion.admin.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import Servicios.ServicioAdmin;
import datos.Excepcion.PanelException;
import datos.interfaz.ObtenerPanel;
import presentacion.alumno.*;
import presentacion.profesor.PanelPrincipalProfesor;

import java.util.List;

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
	private ServicioUsuario servicioUsuario;
	private ServicioAlumno servicioAlumno;
	Usuario usuarioLogueado;

	public PanelManager() {

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

	public void mostrarPanelPorRol(RolUsuario rol) throws PanelException {
		//Chequear exception aca.
		switch (rol) {
			case ADMINISTRADOR -> {
				if (panelPrincipalAdmin == null) {
					panelPrincipalAdmin = new PanelPrincipalAdmin(this);
					servicioAdmin = new ServicioAdmin(panelPrincipalAdmin, this);
					ControladorPanelPrincipalAdmin controlador = new ControladorPanelPrincipalAdmin(panelPrincipalAdmin,this);
					servicioUsuario = new ServicioUsuario(panelInicio, this);
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
				if (panelPrincipalAlumno == null) {
					panelPrincipalAlumno = new PanelPrincipalAlumno(this);
					servicioAlumno = new ServicioAlumno(panelPrincipalAlumno, this);
					ControladorPanelPrincipalAlumno controlador = new ControladorPanelPrincipalAlumno(panelPrincipalAlumno, this);
					panelPrincipalAlumno.setListener(controlador);
				}
				mostrarPanel(panelPrincipalAlumno);
			}
		}
	}

	public void mostrarPanelGestionarAdmin(Gestionar gestion) throws PanelException {
		switch (gestion) {
			case CURSO -> {
				if (panelPrincipalGestionarCurso == null) {
					AbstractTableModel modelo = new CursoAdminTableModel();
					panelPrincipalGestionarCurso = new PanelPrincipalGestionar(this, modelo, "GESTION DE CURSOS",Gestionar.CURSO);
					new ControladorPanelPrincipalGestionar(panelPrincipalGestionarCurso, servicioAdmin, Gestionar.CURSO,RolUsuario.ADMINISTRADOR,servicioUsuario);
				}
				mostrarPanel(panelPrincipalGestionarCurso);
			}
			case USUARIO -> {
				if (panelGestionarUsuario == null) {
					AbstractTableModel modelo = new UsuarioTableModel();
					panelGestionarUsuario = new PanelPrincipalGestionar(this, modelo, "GESTION DE USUARIOS",Gestionar.USUARIO);
					new ControladorPanelPrincipalGestionar(panelGestionarUsuario, servicioAdmin, Gestionar.USUARIO,RolUsuario.ADMINISTRADOR,servicioUsuario);
				}
				mostrarPanel(panelGestionarUsuario);
			}
		}
	}

	public void mostrarPanelCursosAlumno() throws PanelException {
		servicioAdmin = new ServicioAdmin(panelPrincipalAdmin,this);
		List<Curso> cursos = servicioAdmin.obtenerTodosLosCursos();
		AbstractTableModel modelo = new CursoAlumnoTableModel(cursos);
		PanelCursosAlumno panelCursosAlumno = new PanelCursosAlumno(this, modelo, "Cursos Disponibles");
		ControladorPanelCursosAlumno controlador = new ControladorPanelCursosAlumno(panelCursosAlumno,this,servicioAlumno);
		mostrarPanel(panelCursosAlumno);
	}

	public void mostrarMisInscripciones() throws PanelException {
		List<Curso> cursosInscriptos = servicioAlumno.obtenerCursosInscriptos(usuarioLogueado);
		AbstractTableModel modelo = new CursoAlumnoInscriptoTableModel(cursosInscriptos);
		PanelInscripcionesAlumno panelInscripciones = new PanelInscripcionesAlumno(this, modelo, "Mis Inscripciones");
		ControladorPanelInscripcionesAlumno controlador = new ControladorPanelInscripcionesAlumno(panelInscripciones,this);
		mostrarPanel(panelInscripciones);
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

	public Usuario getUsuarioLogueado() {

		return usuarioLogueado;
	}

	public void setUsuarioLogueado(Usuario usuario) {
		this.usuarioLogueado = usuario;
	}
}
