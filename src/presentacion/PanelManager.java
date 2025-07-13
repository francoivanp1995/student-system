package presentacion;

import Servicios.ServicioAlumno;
import Servicios.ServicioProfesor;
import Servicios.ServicioUsuario;
import datos.*;
import presentacion.admin.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import Servicios.ServicioAdmin;
import datos.Excepcion.PanelException;
import datos.interfaz.ObtenerPanel;
import presentacion.alumno.*;
import presentacion.profesor.*;

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
	private ServicioProfesor servicioProfesor;
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
					servicioProfesor = new ServicioProfesor(panelPrincipalProfesor,this);
					ControladorPanelPrincipalProfesor controlador = new ControladorPanelPrincipalProfesor(panelPrincipalProfesor,this);
					panelPrincipalProfesor.setListener(controlador);
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
		PanelCursosAlumno panelCursosAlumno = new PanelCursosAlumno(this, modelo, "CURSOS DISPONIBLES");
		ControladorPanelCursosAlumno controlador = new ControladorPanelCursosAlumno(panelCursosAlumno,this,servicioAlumno);
		mostrarPanel(panelCursosAlumno);
	}

	public void mostrarMisInscripciones() throws PanelException {
		List<Curso> cursosInscriptos = servicioAlumno.obtenerCursosInscriptos(usuarioLogueado);
		AbstractTableModel modelo = new CursoAlumnoInscriptoTableModel(cursosInscriptos);
		PanelInscripcionesAlumno panelInscripciones = new PanelInscripcionesAlumno(this, modelo, "MIS INSCRIPCIONES");
		ControladorPanelInscripcionesAlumno controlador = new ControladorPanelInscripcionesAlumno(panelInscripciones,this);
		mostrarPanel(panelInscripciones);
	}

//	public void mostrarInscripcionesDeProfesor() throws PanelException {
//		List<Inscripcion> inscripciones = servicioProfesor.obtenerInscripcionesPorCurso(usuarioLogueado.getId());
//		AbstractTableModel modelo = new ModeloAlumnosConCurso(inscripciones);
//		PanelAlumnosProfesor panel = new PanelAlumnosProfesor(this, modelo, "ALUMNOS INSCRIPTOS");
//		ControladorPanelAlumnosProfesor controlador = new ControladorPanelAlumnosProfesor(panel, this, servicioProfesor, null);
//		mostrarPanel(panel);
//	}

	public void mostrarAlumnosDeMisCursos() throws PanelException {
			List<Inscripcion> inscripciones = servicioProfesor.obtenerInscripcionesDeTodosMisCursos(usuarioLogueado.getId());
			ModeloAlumnos modelo = new ModeloAlumnos(inscripciones);
			PanelAlumnosProfesor panelAlumnosProfesor = new PanelAlumnosProfesor(this, modelo, "ALUMNOS Y NOTAS");
			ControladorPanelAlumnosProfesor controlador = new ControladorPanelAlumnosProfesor(panelAlumnosProfesor, this, servicioProfesor);
			mostrarPanel(panelAlumnosProfesor);
	}

	public void mostrarPanelCursosProfesor() throws PanelException {
		servicioAdmin = new ServicioAdmin(panelPrincipalAdmin,this);
		List<Curso> cursos = servicioProfesor.obtenerMisCursos(usuarioLogueado.getId());
		AbstractTableModel modelo = new CursoProfesorTableModel(cursos);
		PanelCursosProfesor panelCursoProfesor = new PanelCursosProfesor(this, modelo, "CURSOS ASIGNADOS");
		ControladorPanelCursosProfesor controlador = new ControladorPanelCursosProfesor(panelCursoProfesor,this,servicioProfesor);
		mostrarPanel(panelCursoProfesor);
	}

//	public void mostrarPanelAlumnosProfesor() throws PanelException {
//		List<Inscripcion> inscripciones = servicioProfesor.obtenerInscripcionesDeTodosMisCursos(usuarioLogueado.getId());
//		AbstractTableModel modelo = new ModeloAlumnosConCurso(inscripciones);
//		PanelAlumnosProfesor panelAlumnosProfesor = new PanelAlumnosProfesor(this, modelo, "ALUMNOS Y NOTAS");
//		ControladorPanelAlumnosProfesor controlador = new ControladorPanelAlumnosProfesor(panelAlumnosProfesor, this, servicioProfesor, null); // null porque no filtras por curso
//		mostrarPanel(panelAlumnosProfesor);
//	}

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
