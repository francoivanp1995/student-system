package datos.DAO;

import datos.Alumno;
import datos.Excepcion.DAOException;
import datos.Excepcion.DatabaseException;
import datos.Excepcion.UsuarioExcepction;
import datos.Usuario;

public interface UsuarioDAO {

    void crearUsuario(Alumno unUsuario) throws DAOException;
    void actualizarUsuario(Alumno unUsuario) throws DAOException;
    void eliminarUsuario(Alumno unUsuario) throws DAOException;
    Usuario buscarUsuario(Usuario usuario) throws DAOException;
    public Usuario autenticarUsuario(String nombreUsuario, String contrasenia) throws DAOException, DatabaseException;
}
