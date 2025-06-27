package Servicios;

import datos.*;
import datos.DAO.UsuarioDAO;
import datos.DAO.UsuarioDAOH2Impl;
import datos.Excepcion.*;
import datos.interfaz.VistaLogin;
import presentacion.PanelInicio;
import presentacion.PanelManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioUsuario {

    private final UsuarioDAOH2Impl usuarioDAO = new UsuarioDAOH2Impl();
    private final PanelInicio panelInicio;
    private final PanelManager panelManager;

    public ServicioUsuario(PanelInicio panelInicio, PanelManager panelManager) {
        this.panelInicio = panelInicio;
        this.panelManager = panelManager;
    }

    public Usuario login(String usuario, String contrasenia) throws ServicioException {
        try {
            Usuario u = usuarioDAO.autenticarUsuario(usuario, contrasenia);
            if (u == null) {
                throw new CredencialesInvalidaException("Credenciales inválidas. Por favor intente de nuevo.");
            }
            return u;
        } catch (DAOException e){
            throw new ServicioException(e);
        }
    }
}
