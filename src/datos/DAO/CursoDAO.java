package datos.DAO;

import datos.Curso;

import java.util.List;

public interface CursoDAO {
    void crearCurso(Curso unCurso);
    void eliminarCurso(Curso unCurso);
    void actualizarCurso(Curso unCurso);
    Curso muestraCurso(String nombreCurso);
    List<Curso> listaTodosLosCursos();
    boolean buscarCurso(String unCurso);
}
