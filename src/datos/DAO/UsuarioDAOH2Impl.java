package datos.DAO;

import datos.*;
import datos.Excepcion.DAOException;
import datos.Excepcion.DatabaseException;
import datos.Excepcion.UsuarioExcepction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOH2Impl implements UsuarioDAO{

    @Override
    public void crearUsuario(Alumno unUsuario) throws DAOException {

    }

    @Override
    public void actualizarUsuario(Alumno unUsuario) throws DAOException {

    }

    @Override
    public void eliminarUsuario(Alumno unUsuario) throws DAOException {

    }

    @Override
    public Usuario buscarUsuario(Usuario usuario) throws DAOException {
        return usuario;
    }

    public Usuario autenticarUsuario(String nombreUsuario, String contrasenia) throws DAOException, DatabaseException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            c = new DBManager().connect();
            String sql = """
                    SELECT id, nombre, email, rol 
                    FROM usuarios 
                    WHERE nombreDeUsuario = ? AND contrasenia = ?
                """;
            stmt = c.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasenia);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                RolUsuario rol = RolUsuario.valueOf(rs.getString("rol").toUpperCase());

                return crearUsuarioPorRol(id, nombre, email, rol);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                throw new DatabaseException("Error al cerrar recursos", e);
            }
        }
    }

    private Usuario crearUsuarioPorRol(String id, String nombre, String email, RolUsuario rol) {
        return switch (rol) {
            case ALUMNO -> new Alumno(id, nombre, email, rol);
            case PROFESOR -> new Profesor(id, nombre, email, rol);
            case ADMINISTRADOR -> new Administrador(id, nombre, email, rol);
        };
    }
}
