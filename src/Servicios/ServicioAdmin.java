package Servicios;

import datos.DAO.CursoDAOH2Impl;
import datos.DAO.UsuarioDAOH2Impl;
import presentacion.PanelInicio;
import presentacion.PanelManager;
import presentacion.PanelPrincipalAdmin;

public class ServicioAdmin {

    private final PanelPrincipalAdmin panelPrincipalAdmin;
    private final PanelManager panelManager;
    private final CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();

    public ServicioAdmin(PanelPrincipalAdmin panelPrincipalAdmin, PanelManager panelManager) {
        this.panelPrincipalAdmin = panelPrincipalAdmin;
        this.panelManager = panelManager;
    }

    public void curso(){

        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void usuario(){
        //todo
    }
}
