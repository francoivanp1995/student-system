package datos.DAO;

import datos.Curso;
import datos.DBManager;
import datos.Excepcion.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public void eliminarCurso(String unCurso) throws DatabaseException {
        Connection connection = DBManager.connect();
        String sql = "DELETE FROM CURSOS WHERE nombre = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, unCurso);
            int afectados = ps.executeUpdate();
            if (afectados == 0) throw new DatabaseException("No se encontró un curso con ese nombre.");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new DatabaseException("Error al eliminar el curso: " + e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void actualizarCurso(Curso unCurso) throws DatabaseException {
        Connection connection = DBManager.connect();
        String sql = """
        UPDATE CURSOS 
        SET cupo = ?, 
            precio = ?, 
            nota_aprobacion = ?, 
            profesor_dni = ?
        WHERE nombre = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, unCurso.getCupoMaximo());
            ps.setInt(2, unCurso.getPrecio());
            ps.setInt(3, unCurso.getNotaAprobacion());
            ps.setString(4, unCurso.getId()); // profesor_dni
            ps.setString(5, unCurso.getNombre());

            int filas = ps.executeUpdate();
            if (filas == 0) throw new DatabaseException("No se encontró un curso con ese nombre.");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DatabaseException("Error al hacer rollback: " + ex.getMessage(), ex);
            }
            throw new DatabaseException("Error al actualizar curso: " + e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseException("Error al cerrar conexión: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public Curso muestraCurso(String nombreCurso) {
        return null;
    }

    @Override
    public List<Curso> listaTodosLosCursos() throws DatabaseException {
        List<Curso> cursos = new ArrayList<>();
        Connection connection = DBManager.connect();
        String sql = "SELECT nombre, cupo, precio, nota_aprobacion, profesor_dni FROM CURSOS";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cupo = rs.getInt("cupo");
                int precio = rs.getInt("precio");
                int nota = rs.getInt("nota_aprobacion");
                String profesorId = rs.getString("profesor_dni");

                Curso curso = new Curso(nombre, cupo, precio, nota, profesorId);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar cursos: " + e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseException("Error al cerrar conexión: " + e.getMessage(), e);
            }
        }

        return cursos;
    }

    @Override
    public void buscarCurso(String unCurso) {

    }
}
