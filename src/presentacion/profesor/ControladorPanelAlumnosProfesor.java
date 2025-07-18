package presentacion.profesor;

import Servicios.LibreriaValidaciones;
import Servicios.ServicioProfesor;
import datos.Alumno;
import datos.Excepcion.DAOException;
import datos.Excepcion.PanelException;
import datos.Excepcion.ValidacionException;
import datos.Inscripcion;
import datos.ModeloAlumnos;
import datos.RolUsuario;
import presentacion.PanelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static datos.Comandos.*;

public class ControladorPanelAlumnosProfesor  implements ActionListener {
    private final PanelAlumnosProfesor panelAlumnosProfesor;
    private final ServicioProfesor servicioProfesor;
    private final PanelManager panelManager;

    public ControladorPanelAlumnosProfesor(PanelAlumnosProfesor panel, PanelManager panelManager, ServicioProfesor servicio) {
        this.panelAlumnosProfesor = panel;
        this.panelManager = panelManager;
        this.servicioProfesor = servicio;
        panelAlumnosProfesor.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case GUARDAR:
                try {
                    guardarNotas();
                } catch (ValidacionException ex){
                    ex.printStackTrace();
                    panelAlumnosProfesor.mostrarAdvertencia(ex.getMessage());
                }
                break;
            case REGRESAR:
                try {
                    panelManager.mostrarPanelPorRol(RolUsuario.PROFESOR);
                } catch (PanelException ex) {
                    ex.printStackTrace();
                    panelAlumnosProfesor.mostrarError("Error al regresar");
                }
                break;
            case CERRAR:
                System.exit(0);
                break;
        }
    }

    private void guardarNotas() throws ValidacionException {
        ModeloAlumnos modelo = (ModeloAlumnos) panelAlumnosProfesor.getTabla().getModel();
        for (Inscripcion insc : modelo.getInscripciones()) {
            Integer nota = insc.getNotaFinal();
            if (nota != null) {
                LibreriaValidaciones.validarNumeroNoNegativo(nota);
                LibreriaValidaciones.validarNota(nota);
                try {
                    servicioProfesor.asignarNota(insc.getAlumno(), insc.getCurso(), nota);
                } catch (DAOException e) {
                    panelAlumnosProfesor.mostrarError("Error al guardar nota de " + insc.getAlumno().getNombre() + ": " + e.getMessage());
                }
            }
        }
    }
}