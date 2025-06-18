package Servicios;

import datos.*;
import datos.DAO.UsuarioDAO;
import datos.DAO.UsuarioDAOH2Impl;
import datos.Excepcion.CredencialesInvalidaException;
import datos.Excepcion.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioUsuario {

    private final UsuarioDAOH2Impl usuarioDAO = new UsuarioDAOH2Impl();

    public Usuario login(String nombreUsuario, String contrasenia) throws CredencialesInvalidaException, SQLException, DatabaseException {
        try {
            Usuario usuario = usuarioDAO.autenticarUsuario(nombreUsuario, contrasenia);
            if (usuario == null) {
                throw new CredencialesInvalidaException("Usuario o contraseña incorrectos");
            }
            return usuario;
        } catch (Exception e) {
            //Ojo acá iría sql exception!!
            throw new DatabaseException("Error de acceso a la base de datos", e);
        }
    }
}
