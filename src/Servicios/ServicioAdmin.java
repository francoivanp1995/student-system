package Servicios;

import datos.DAO.UsuarioDAOH2Impl;
import presentacion.PanelInicio;
import presentacion.PanelManager;
import presentacion.PanelPrincipalAdmin;

public class ServicioAdmin {

    private final PanelPrincipalAdmin panelPrincipalAdmin;
    private final PanelManager panelManager;

    public ServicioAdmin(PanelPrincipalAdmin panelPrincipalAdmin, PanelManager panelManager) {
        this.panelPrincipalAdmin = panelPrincipalAdmin;
        this.panelManager = panelManager;
    }
}
