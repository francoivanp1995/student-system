package Servicios;

import datos.Alumno;
import datos.Curso;
import datos.DAO.InscripcionDAO;
import datos.DAO.InscripcionDAOH2Impl;
import datos.Excepcion.DAOException;
import datos.Excepcion.ServicioException;
import datos.Inscripcion;
import presentacion.PanelManager;
import presentacion.alumno.PanelPrincipalAlumno;
import presentacion.profesor.PanelPrincipalProfesor;

import java.util.ArrayList;
import java.util.List;

public class ServicioProfesor {

    private final PanelPrincipalProfesor panelPrincipalProfesor;
    private final PanelManager panelManager;
    private final InscripcionDAO inscripcionDAO;

    public ServicioProfesor(PanelPrincipalProfesor panelPrincipalProfesor, PanelManager panelManager){
        this.panelPrincipalProfesor = panelPrincipalProfesor;
        this.panelManager = panelManager;
        this.inscripcionDAO = new InscripcionDAOH2Impl();
    }

    public void asignarNota(Alumno alumno, Curso curso, int nota) throws ServicioException {
        try {
            System.out.println("Estoy en el asignarNota del Servicio profesor");
            System.out.println("La nota es: " + nota);
            System.out.println("El curso es: " + curso.getNombre());
            System.out.println("El alumno es: " + alumno.getApellido());
            InscripcionDAO dao = new InscripcionDAOH2Impl();
            dao.actualizarNota(alumno.getId(), curso.getId(), nota);
        } catch (DAOException e) {
            throw new ServicioException("No se pudo asignar la nota", e);
        }
    }

    public List<Curso> obtenerMisCursos(String profesorDni) throws ServicioException {
        try {
            return inscripcionDAO.listaCursosPorProfesor(profesorDni);
        } catch (DAOException e) {
            throw new ServicioException(e.getMessage());
        }
    }

//    public List<Inscripcion> obtenerInscripcionesPorCurso(String cursoId) throws DAOException {
//        return new InscripcionDAOH2Impl().obtenerInscripcionesPorCurso(cursoId);
//    }

    public List<Inscripcion> obtenerInscripcionesDeTodosMisCursos(String profesorDni) throws ServicioException {
        try {
            List<Curso> cursos = inscripcionDAO.listaCursosPorProfesor(profesorDni);
            List<Inscripcion> todasInscripciones = new ArrayList<>();

            for (Curso curso : cursos) {
                List<Inscripcion> inscripcionesCurso = inscripcionDAO.obtenerInscripcionesPorCurso(curso.getId());
                todasInscripciones.addAll(inscripcionesCurso);
            }

            return todasInscripciones;
        } catch (DAOException e) {
            throw new ServicioException("Error al obtener inscripciones de todos mis cursos", e);
        }
    }
}
