package datos.DAO;

import datos.Curso;
import datos.DBManager;
import datos.Excepcion.DAOException;
import datos.Excepcion.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOH2Impl implements CursoDAO{
    @Override
    public void crearCurso(Curso unCurso) throws DAOException {
        Connection connection = DBManager.connect();
        String sql = "INSERT INTO CURSOS (nombre, cupo, precio, nota_aprobacion, profesor_dni) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, unCurso.getNombre());
            preparedStatement.setInt(2, unCurso.getCupoMaximo());
            preparedStatement.setInt(3, unCurso.getPrecio());
            preparedStatement.setInt(4, unCurso.getNotaAprobacion());
            preparedStatement.setString(5, unCurso.getProfesorDni());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                unCurso.setId(String.valueOf(keys.getLong(1)));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                throw new DAOException(rollbackEx);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException close) {
                close.printStackTrace();
            }
        }
    }

    @Override
    public void eliminarCurso(String unCurso) throws DAOException {
        Connection connection = DBManager.connect();
        String sql = "DELETE FROM CURSOS WHERE nombre = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, unCurso);
            int afectados = ps.executeUpdate();
            if (afectados == 0) throw new DAOException("No se encontró un curso con ese nombre.");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actualizarCurso(Curso unCurso) throws DAOException {
        Connection connection = DBManager.connect();
        String sql = "UPDATE CURSOS SET cupo = ?, precio = ?, nota_aprobacion = ?, profesor_dni = ? WHERE nombre = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, unCurso.getCupoMaximo());
            ps.setInt(2, unCurso.getPrecio());
            ps.setInt(3, unCurso.getNotaAprobacion());
            ps.setString(4, unCurso.getId()); // profesor_dni
            ps.setString(5, unCurso.getNombre());

            int filas = ps.executeUpdate();
            if (filas == 0) throw new DAOException("No se encontró un curso con ese nombre.");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DAOException(ex);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Curso muestraCurso(String nombreCurso) {
        return null;
    }

    @Override
    public List<Curso> listaTodosLosCursos() throws DAOException {
        List<Curso> cursos = new ArrayList<>();
        Connection connection = DBManager.connect();
        String sql = "SELECT id, nombre, cupo, precio, nota_aprobacion, profesor_dni FROM CURSOS";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                int cupo = rs.getInt("cupo");
                int precio = rs.getInt("precio");
                int nota = rs.getInt("nota_aprobacion");
                String profesorId = rs.getString("profesor_dni");
                Curso curso = new Curso(id, nombre, cupo, precio, nota, profesorId);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar cursos: " + e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage(), e);
            }
        }

        return cursos;
    }

    @Override
    public void buscarCurso(String unCurso) {

    }

    public List<Curso> listaCursosConCantidadInscritos() throws DAOException {
        List<Curso> cursos = new ArrayList<>();
        Connection connection = DBManager.connect();

        String sql = """
        SELECT c.id, c.nombre, c.cupo, c.precio, c.nota_aprobacion, c.profesor_dni, 
               COUNT(i.id) AS cantidad_inscritos
        FROM CURSOS c
        LEFT JOIN INSCRIPCIONES i ON c.id = i.curso_id
        GROUP BY c.id, c.nombre, c.cupo, c.precio, c.nota_aprobacion, c.profesor_dni
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getString("id"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCupoMaximo(rs.getInt("cupo"));
                curso.setPrecio(rs.getInt("precio"));
                curso.setNotaAprobacion(rs.getInt("nota_aprobacion"));
                curso.setProfesorDni(rs.getString("profesor_dni"));
                curso.setCantidadInscritos(rs.getInt("cantidad_inscritos"));
                // Guardar cantidad inscritos en una propiedad temporal, o crear método getInscripciones que retorne lista dummy con esa cantidad
                curso.setCantidadInscritos(rs.getInt("cantidad_inscritos"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar cursos con inscritos: " + e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage(), e);
            }
        }

        return cursos;
    }
}
