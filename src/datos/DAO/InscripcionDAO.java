package datos.DAO;

import datos.Curso;
import datos.Excepcion.DAOException;
import datos.Inscripcion;
import datos.Usuario;

import java.util.List;

public interface InscripcionDAO {

    void inscribirAlumno(Usuario alumno, Curso curso) throws DAOException;
    boolean estaInscripto(Usuario alumno, Curso curso) throws DAOException;
    int contarInscripcionesPorCurso(Curso curso) throws DAOException;
    int contarCursosActivos(Usuario alumno) throws DAOException;
    List<Inscripcion> obtenerInscripcionesPorCurso(String idcurso) throws DAOException;
    int contarCursosActivosSinAprobar(Usuario alumno);
    void eliminarInscripcion(Usuario alumno, Curso curso);
    List<Curso> obtenerCursosPorAlumno(Usuario alumno);
    Inscripcion obtenerInscripcion(Usuario alumno, Curso curso);
    void actualizarNota(String idAlumno, String idCurso, int nota) throws DAOException;
    public List<Curso> listaCursosPorProfesor(String profesorDni) throws DAOException;
}
