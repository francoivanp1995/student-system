package datos.DAO;

import datos.Curso;
import datos.DBManager;
import datos.Excepcion.DAOException;
import datos.Inscripcion;
import datos.Usuario;

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
                "WHERE i.alumno_dni = ? AND (i.nota_final IS NULL OR i.nota_final < c.nota_aprobacion)";        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, alumno.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Inscripcion> obtenerInscripcionesPorCurso(Curso curso) throws DAOException {
        List<Inscripcion> lista = new ArrayList<>();
        // Implementar si querés ver los inscriptos de un curso (profesor)
        return lista;
    }

    @Override
    public int contarCursosActivosSinAprobar(Usuario alumno) {
        return 0;
    }

    @Override
    public void eliminarInscripcion(Usuario alumno, Curso curso) {

    }

    @Override
    public List<Curso> obtenerCursosPorAlumno(Usuario alumno) {
        return List.of();
    }

    @Override
    public Inscripcion obtenerInscripcion(Usuario alumno, Curso curso) {
        return null;
    }
}
