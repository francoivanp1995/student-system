package datos.DAO;

import datos.*;
import datos.Excepcion.DAOException;
import datos.Excepcion.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOH2Impl implements UsuarioDAO{

    @Override
    public void crearUsuario(Usuario unUsuario) throws DAOException {
        Connection c = DBManager.connect();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO usuarios (dni, nombre, apellido, email, tipo, nombre_de_usuario, contrasenia, limite_cursos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, unUsuario.getId());
            stmt.setString(2, unUsuario.getNombre());
            stmt.setString(3, unUsuario.getApellido());
            stmt.setString(4, unUsuario.getEmail());
            stmt.setString(5, unUsuario.getRol().name());
            stmt.setString(6, unUsuario.getNombreUsuario());
            stmt.setString(7, unUsuario.getContrasenia());

            //Como estoy poniendo todos los usuarios en una tabla, unicamente tiene sentido para Alumno el limite de curso
            if (unUsuario instanceof Alumno alumno) {
                System.out.println("El limite de cursos es: " + alumno.getLimiteCursos());
                stmt.setInt(8, alumno.getLimiteCursos());
            } else {
                stmt.setNull(8, Types.INTEGER);
            }

            stmt.executeUpdate();
            c.commit();

        } catch (SQLException e) {
            try {
                throw new DatabaseException("Error al crear el usuario", e);
            } catch (DatabaseException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void actualizarUsuario(Usuario unUsuario) throws DAOException {
        Connection conn = DBManager.connect();
        String sql = "UPDATE USUARIOS SET nombre = ?, email = ?, tipo = ?, limite_cursos = ? WHERE dni = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, unUsuario.getNombre());
            stmt.setString(2, unUsuario.getEmail());
            stmt.setString(3, unUsuario.getRol().name());

            if (unUsuario instanceof Alumno alumno) {
                stmt.setInt(4, alumno.getLimiteCursos());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setString(5, unUsuario.getId());
            int filas = stmt.executeUpdate();
            if (filas == 0) {
                throw new DAOException("No se encontró el usuario con DNI: " + unUsuario.getId());
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void eliminarUsuario(Usuario unUsuario) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBManager.connect();
            String sql = "DELETE FROM USUARIOS WHERE dni = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, unUsuario.getId());
            int filas = stmt.executeUpdate();
            if (filas == 0) {
                throw new DAOException("No se encontró el usuario a eliminar: " + unUsuario.getId());
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }

    @Override
    public Usuario buscarUsuario(Usuario usuario) throws DAOException {
        return usuario;
    }

    public Usuario autenticarUsuario(String nombreUsuario, String contrasenia) throws DAOException{
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            c = new DBManager().connect();
            String sql = """
                    SELECT dni, nombre, apellido, email, tipo
                    FROM usuarios
                    WHERE nombre_de_usuario = ? AND contrasenia = ?
                """;
            stmt = c.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasenia);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String apellido = rs.getString("apellido");
                RolUsuario rol = RolUsuario.valueOf(rs.getString("tipo").toUpperCase());

                return crearUsuarioPorRol(dni, nombre, apellido, email, rol, nombreUsuario, contrasenia);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    private Usuario crearUsuarioPorRol(String dni, String nombre, String apellido, String email, RolUsuario rol, String nombreUsuario, String contrasenia) {
        return switch (rol) {
            case ALUMNO -> new Alumno(dni, nombre, apellido, email, rol,nombreUsuario,contrasenia);
            case PROFESOR -> new Profesor(dni, nombre, apellido, email, rol,nombreUsuario,contrasenia);
            case ADMINISTRADOR -> new Administrador(dni, nombre, apellido, email, rol,nombreUsuario,contrasenia);
        };
    }

    public List<Usuario> listaTodosLosUsuarios() throws DAOException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = DBManager.connect();
        String sql = "SELECT dni, apellido, nombre, apellido, email, tipo, nombre_de_usuario, contrasenia FROM USUARIOS";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            while (rs.next()) {
                String dni = rs.getString("dni");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String rolString = rs.getString("tipo");
                String nombreUsuario = rs.getString("nombre_de_usuario");
                String contrasenia = rs.getString("contrasenia");

                RolUsuario rol = RolUsuario.valueOf(rolString.toUpperCase());

                Usuario usuario;
                switch (rol) {
                    case ADMINISTRADOR -> usuario = new Administrador(dni, nombre, apellido, email, rol, nombreUsuario, contrasenia);
                    case ALUMNO -> usuario = new Alumno(dni, nombre, apellido, email, rol,nombreUsuario ,contrasenia );
                    case PROFESOR -> usuario = new Profesor(dni, nombre, apellido, email, rol, nombreUsuario, contrasenia);
                    default -> throw new DAOException("Rol desconocido: " + rolString);
                }

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return usuarios;
    }
}
