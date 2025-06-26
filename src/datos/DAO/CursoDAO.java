package datos.DAO;

import datos.Curso;
import datos.Excepcion.DatabaseException;

import java.util.List;

public interface CursoDAO {
    void crearCurso(Curso unCurso) throws DatabaseException;
    void eliminarCurso(String unCurso) throws DatabaseException;
    void actualizarCurso(Curso unCurso) throws DatabaseException;
    Curso muestraCurso(String nombreCurso);
    List<Curso> listaTodosLosCursos() throws DatabaseException;
    void buscarCurso(String unCurso);
}
