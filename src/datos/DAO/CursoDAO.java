package datos.DAO;

import datos.Curso;
import datos.Excepcion.DatabaseException;

import java.util.List;

public interface CursoDAO {
    void crearCurso(Curso unCurso) throws DatabaseException;
    void eliminarCurso(Curso unCurso);
    void actualizarCurso(Curso unCurso);
    Curso muestraCurso(String nombreCurso);
    List<Curso> listaTodosLosCursos();
    void buscarCurso(String unCurso);
}
