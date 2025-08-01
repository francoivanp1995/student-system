package Servicios;

import datos.*;
import datos.DAO.InscripcionDAO;
import datos.DAO.InscripcionDAOH2Impl;
import datos.Excepcion.ServicioException;
import datos.interfaz.Panel;
import presentacion.PanelManager;
import presentacion.alumno.PanelPrincipalAlumno;

import java.util.List;

public class ServicioAlumno {

    private final PanelPrincipalAlumno panelPrincipalAlumno;
    private final PanelManager panelManager;
    private final InscripcionDAO inscripcionDAO;

    //Chequear el inscripto.
    private final int maximoInscripto = 3;

    public ServicioAlumno(PanelPrincipalAlumno panelPrincipalAlumno, PanelManager panelManager){
        this.panelPrincipalAlumno = panelPrincipalAlumno;
        this.panelManager = panelManager;
        this.inscripcionDAO = new InscripcionDAOH2Impl();
    }

    public void inscribirACurso(Alumno alumno, Curso curso) throws ServicioException {
        validarAlumno(alumno);

        try {
            if (inscripcionDAO.estaInscripto(alumno, curso)) {
                throw new ServicioException("Ya estás inscrito en este curso.");
            }

            if (!puedeInscribirse(alumno)) {
                throw new ServicioException("No puedes inscribirte: tienes cursos activos sin aprobar.");
            }

            int inscritos = inscripcionDAO.contarInscripcionesPorCurso(curso);
            if (inscritos >= curso.getCupoMaximo()) {
                throw new ServicioException("El curso alcanzó el cupo máximo.");
            }

            inscripcionDAO.inscribirAlumno(alumno, curso);
        } catch (Exception e) {
            throw new ServicioException("No se pudo inscribir al curso: " + e.getMessage());
        }
    }

    public void desinscribirDeCurso(Usuario alumno, Curso curso) throws ServicioException {
        validarAlumno(alumno);

        try {
            if (!inscripcionDAO.estaInscripto(alumno, curso)) {
                throw new ServicioException("No estás inscrito en este curso.");
            }

            inscripcionDAO.eliminarInscripcion(alumno, curso);
        } catch (Exception e) {
            throw new ServicioException("No se pudo desinscribir: " + e.getMessage());
        }
    }

    //Chequear esta funcion porque en realidad yo le paso el limite de inscripcion cuando creo el usuario;
    public boolean puedeInscribirse(Alumno alumno) {
        try {
            int cursosActivos = inscripcionDAO.contarCursosActivosSinAprobar(alumno);
            return cursosActivos < maximoInscripto;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Curso> obtenerCursosInscriptos(Usuario alumno) {
        return inscripcionDAO.obtenerCursosPorAlumno(alumno);
    }

    public boolean estaAprobado(Usuario alumno, Curso curso) {
        Inscripcion insc = inscripcionDAO.obtenerInscripcion(alumno, curso);
        if (insc == null || insc.getNotaFinal() == null) {
            return false;
        }
        Integer notaMinima = curso.getNotaAprobacion();
        return insc.getNotaFinal() >= notaMinima;
    }

    private void validarAlumno(Usuario usuario) throws ServicioException {
        if (usuario.getRol() != RolUsuario.ALUMNO) {
            throw new ServicioException("Acción permitida solo para alumnos.");
        }
    }
}
