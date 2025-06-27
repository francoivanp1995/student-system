package datos.DAO;

import datos.Curso;
import datos.Excepcion.DAOException;
import datos.Excepcion.DatabaseException;

import java.util.List;

public interface CursoDAO {
    void crearCurso(Curso unCurso) throws DAOException;
    void eliminarCurso(String unCurso) throws DAOException;
    void actualizarCurso(Curso unCurso) throws DAOException;
    Curso muestraCurso(String nombreCurso);
    List<Curso> listaTodosLosCursos() throws DAOException;
    void buscarCurso(String unCurso);

}
