package presentacion.admin;

import Servicios.ServicioAdmin;
import datos.Excepcion.PanelException;
import datos.Gestionar;
import datos.RolUsuario;
import presentacion.PanelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datos.Comandos.*;

public class ControladorPanelPrincipalAdmin implements ActionListener {

    private PanelManager panelManager;
    private final ServicioAdmin servicioAdmin;
    private final PanelPrincipalAdmin panelPrincipalAdmin;


    public ControladorPanelPrincipalAdmin(PanelPrincipalAdmin panelPrincipalAdmin, PanelManager panelManager) {
        this.panelPrincipalAdmin = panelPrincipalAdmin;
        this.panelManager = panelManager;
        this.servicioAdmin = new ServicioAdmin(panelPrincipalAdmin,panelManager);
        this.panelPrincipalAdmin.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CURSO:
                try {
                    panelManager.mostrarPanelGestionarAdmin(Gestionar.CURSO);
                } catch (PanelException ex) {
                    panelPrincipalAdmin.mostrarError("Error al cambiar a gestión de curso: " + ex.getMessage());
                }
                break;
            case USUARIO:
                try {
                    panelManager.mostrarPanelGestionarAdmin(Gestionar.USUARIO);
                } catch (PanelException ex) {
                    panelPrincipalAdmin.mostrarError("Error al cambiar a gestión de usuario: " + ex.getMessage());
                }
                break;

            case REGRESAR:
                try {
                    panelManager.mostrarPanelPorRol(RolUsuario.ADMINISTRADOR);
                } catch (PanelException ex) {
                    panelPrincipalAdmin.mostrarError("Error al regresar: " + ex.getMessage());
                }
                break;
            case CERRAR:
                System.exit(0);
                break;
        }
    }
}
