package Servicios;

import Servicios.Validacion.ValidarCurso;
import Servicios.Validacion.ValidarUsuario;
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
    ValidarUsuario validarUsuario;

    public ServicioUsuario(PanelInicio panelInicio, PanelManager panelManager) {
        this.panelInicio = panelInicio;
        this.panelManager = panelManager;
    }

    public Usuario login(String usuario, String contrasenia) throws ServicioException {
        try {
            Usuario u = usuarioDAO.autenticarUsuario(usuario, contrasenia);
            return u;
        } catch (DAOException e){
            throw new ServicioException(e);
        }
    }

    //ToDo
    public void validarUsuario(Usuario usuario){
        try {
            validarUsuario.validar(usuario);
        } catch (ValidacionException e) {
            e.printStackTrace();
            throw new ServicioException(e);
        }
    };
}
