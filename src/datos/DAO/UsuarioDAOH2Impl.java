package datos.DAO;

import datos.*;
import datos.Excepcion.DAOException;
import datos.Excepcion.DatabaseException;
import datos.Excepcion.UsuarioExcepction;

import java.sql.*;

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

            Statement debugStmt = c.createStatement();
            ResultSet debugRs = debugStmt.executeQuery("SELECT nombre_de_usuario, contrasenia FROM usuarios");
            System.out.println("Contenido de la tabla USUARIOS:");
            while (debugRs.next()) {
                System.out.println("Usuario: " + debugRs.getString("nombre_de_usuario") +
                        ", Contraseña: " + debugRs.getString("contrasenia"));
            }
            debugRs.close();
            debugStmt.close();



            String sql = """
                    SELECT dni, nombre, email, tipo
                    FROM usuarios
                    WHERE nombre_de_usuario = ? AND contrasenia = ?
                """;
            stmt = c.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasenia);
            System.out.println("Probando login con: " + nombreUsuario + " / " + contrasenia);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                RolUsuario rol = RolUsuario.valueOf(rs.getString("tipo").toUpperCase());

                return crearUsuarioPorRol(dni, nombre, email, rol);
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

    private Usuario crearUsuarioPorRol(String dni, String nombre, String email, RolUsuario rol) {
        return switch (rol) {
            case ALUMNO -> new Alumno(dni, nombre, email, rol);
            case PROFESOR -> new Profesor(dni, nombre, email, rol);
            case ADMINISTRADOR -> new Administrador(dni, nombre, email, rol);
        };
    }

    public Usuario buscarUsuarioPorDni(String dni) throws DAOException, DatabaseException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            c = new DBManager().connect();

            String sql = """
                SELECT dni, nombre, email, tipo
                FROM usuarios
                WHERE dni = ?
            """;
            stmt = c.prepareStatement(sql);
            stmt.setString(1, dni);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                RolUsuario rol = RolUsuario.valueOf(rs.getString("tipo").toUpperCase());

                return crearUsuarioPorRol(dni, nombre, email, rol);
            } else {
                return null; // No se encontró usuario
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

}
