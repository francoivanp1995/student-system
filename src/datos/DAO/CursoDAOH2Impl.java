package datos.DAO;

import datos.Curso;
import datos.DBManager;
import datos.Excepcion.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CursoDAOH2Impl implements CursoDAO{
    @Override
    public void crearCurso(Curso unCurso) throws DatabaseException{
        Connection connection = DBManager.connect();
        String sql = "INSERT INTO CURSOS (nombre, cupo, precio, nota_aprobacion, profesor_dni) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, unCurso.getNombre());
            preparedStatement.setInt(2, unCurso.getCupoMaximo());
            preparedStatement.setInt(3, unCurso.getPrecio());
            preparedStatement.setInt(4, unCurso.getNotaAprobacion());
            preparedStatement.setString(5, unCurso.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                throw new DatabaseException("Error al hacer rollback: " + rollbackEx.getMessage(), rollbackEx);
            }
            throw new DatabaseException("Error al crear curso" + e.getMessage(),e);
        } finally {
            try {
                connection.close();
            } catch (SQLException close) {
                close.printStackTrace();
            }
        }
    }

    @Override
    public void eliminarCurso(Curso unCurso) {

    }

    @Override
    public void actualizarCurso(Curso unCurso) {

    }

    @Override
    public Curso muestraCurso(String nombreCurso) {
        return null;
    }

    @Override
    public List<Curso> listaTodosLosCursos() {
        return List.of();
    }

    @Override
    public void buscarCurso(String unCurso) {

    }
}
