package presentacion;

import Servicios.ServicioAdmin;
import Servicios.ServicioUsuario;
import datos.Excepcion.CredencialesInvalidaException;
import datos.Excepcion.DatabaseException;
import datos.Excepcion.PanelException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ControladorPanelPrincipalAdmin implements ActionListener {

    private final ServicioAdmin servicioAdmin;
    private final PanelPrincipalAdmin panelPrincipalAdmin;

    public ControladorPanelPrincipalAdmin(PanelPrincipalAdmin panelPrincipalAdmin, PanelManager panelManager) {
        this.panelPrincipalAdmin = panelPrincipalAdmin;
        this.servicioAdmin = new ServicioAdmin(panelPrincipalAdmin,panelManager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Estoy en el action performed");
        switch (e.getActionCommand()) {
            case "CURSO":
                try {
                    System.out.println("Aca en el curso desde controladorPanelPrincipal");
                } catch (Exception ex) {
                    panelPrincipalAdmin.mostrarError("Error inesperado: " + ex.getMessage());
                }
                break;
            case "ALUMNO":
                System.exit(0);
                break;
        }
    }
}
