package datos.DAO;

import datos.*;
import datos.Excepcion.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAOH2Impl implements InscripcionDAO{

    public void inscribirAlumno(Usuario alumno, Curso curso) throws DAOException {
        Connection connection = DBManager.connect();
        System.out.println("Inscribiendo alumno al curso ID: " + curso.getId());
        String sql = "INSERT INTO INSCRIPCIONES (alumno_dni, curso_id, nota_final) VALUES (?, ?, NULL)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, alumno.getId());
            stmt.setString(2, curso.getId());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
            throw new DAOException(e);
        } //agregar finally
    }

    public boolean estaInscripto(Usuario alumno, Curso curso) throws DAOException {
        Connection connection = DBManager.connect();
        String sql = "SELECT 1 FROM INSCRIPCIONES WHERE alumno_dni = ? AND curso_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, alumno.getId());
            stmt.setString(2, curso.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public int contarInscripcionesPorCurso(Curso curso) throws DAOException {
        Connection connection = DBManager.connect();
        String sql = "SELECT COUNT(*) FROM INSCRIPCIONES WHERE curso_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curso.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public int contarCursosActivos(Usuario alumno) throws DAOException {
        Connection connection = DBManager.connect();
        String sql = "SELECT COUNT(*) FROM INSCRIPCIONES i " +
                "JOIN CURSOS c ON i.curso_id = c.id " +
                "WHERE i.alumno_dni = ? AND (i.nota_final IS NULL OR i.nota_final < c.nota_aprobacion)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, alumno.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int contarCursosActivosSinAprobar(Usuario alumno) {
        return 0;
    }

    @Override
    public void eliminarInscripcion(Usuario alumno, Curso curso) {
        Connection connection = DBManager.connect();
        String sql = "DELETE FROM INSCRIPCIONES WHERE alumno_dni = ? AND curso_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, alumno.getId());
            stmt.setString(2, curso.getId());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new DAOException("No se encontró una inscripción para eliminar.");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error al eliminar la inscripción: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Curso> obtenerCursosPorAlumno(Usuario alumno) {
        List<Curso> cursos = new ArrayList<>();
        Connection connection = DBManager.connect();

        String sql = "SELECT c.id, c.nombre, c.nota_aprobacion " +
                "FROM INSCRIPCIONES i " +
                "JOIN CURSOS c ON i.curso_id = c.id " +
                "WHERE i.alumno_dni = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, alumno.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso();
                    curso.setId(rs.getString("id"));
                    curso.setNombre(rs.getString("nombre"));
//                    curso.setDescripcion(rs.getString("descripcion"));
                    curso.setNotaAprobacion(rs.getInt("nota_aprobacion"));
                    cursos.add(curso);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    @Override
    public Inscripcion obtenerInscripcion(Usuario alumno, Curso curso) {
        return null;
    }

    @Override
    public void actualizarNota(String idAlumno, String idCurso, int nota) throws DAOException {
        System.out.println("En el actualizar Nota del DAO");
        Connection connection = DBManager.connect();
        String sql = """
        UPDATE INSCRIPCIONES 
        SET nota_final = ? 
        WHERE alumno_dni = ? AND curso_id = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, nota);
            ps.setString(2, idAlumno);
            ps.setString(3, idCurso);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar nota final", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión", e);
            }
        }
    }

    public List<Curso> listaCursosPorProfesor(String profesorDni) throws DAOException {
        List<Curso> cursos = new ArrayList<>();
        Connection connection = DBManager.connect();
        String sql = "SELECT id, nombre, cupo, precio, nota_aprobacion, profesor_dni FROM CURSOS WHERE profesor_dni = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, profesorDni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getInt("cupo"),
                        rs.getInt("precio"),
                        rs.getInt("nota_aprobacion"),
                        rs.getString("profesor_dni")
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar cursos por profesor: " + e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage(), e);
            }
        }

        return cursos;
    }

    public List<Inscripcion> obtenerInscripcionesPorCurso(String cursoId) throws DAOException {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = """
                SELECT
                    i.nota_final,
                    u.dni AS alumno_dni,
                    u.nombre AS alumno_nombre,
                    u.apellido AS alumno_apellido,
                    c.id AS curso_id,
                    c.nombre AS curso_nombre,
                    c.nota_aprobacion
                FROM INSCRIPCIONES i
                JOIN USUARIOS u ON i.alumno_dni = u.dni
                JOIN CURSOS c ON i.curso_id = c.id
                WHERE i.curso_id = ? """;

        try (Connection connection = DBManager.connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, cursoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alumno alumno = new Alumno(
                            rs.getString("alumno_dni"),
                            rs.getString("alumno_nombre"),
                            rs.getString("alumno_apellido")
                    );
                    System.out.println("DNI: " + rs.getString("alumno_dni") +
                            ", Nombre: " + rs.getString("alumno_nombre") +
                            ", Apellido: " + rs.getString("alumno_apellido"));


                    Curso curso = new Curso(
                            rs.getString("curso_id"),
                            rs.getString("curso_nombre"),
                            rs.getInt("nota_aprobacion")
                    );

                    Inscripcion insc = new Inscripcion((Alumno) alumno, curso);

                    int nota = rs.getInt("nota_final");
                    if (!rs.wasNull()) {
                        insc.setNotaFinal(nota);
                    }

                    inscripciones.add(insc);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener inscripciones del curso", e);
        }

        return inscripciones;
    }
}
