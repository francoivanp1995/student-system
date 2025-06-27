package presentacion;

import Servicios.ServicioUsuario;
import datos.*;
import presentacion.admin.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import Servicios.ServicioAdmin;
import datos.Excepcion.PanelException;
import datos.interfaz.ObtenerPanel;
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
	private ServicioUsuario servicioUsuario;

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
				if (panelPrincipalAlumno == null)
					panelPrincipalAlumno = new PanelPrincipalAlumno(this);
				mostrarPanel(panelPrincipalAlumno);
			}
		}
	}

	public void mostrarPanelGestionar(Gestionar gestion) throws PanelException {
		switch (gestion) {
			case CURSO -> {
				if (panelPrincipalGestionarCurso == null) {
					AbstractTableModel modelo = new CursoTableModel();
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
