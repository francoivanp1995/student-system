package presentacion;

import Servicios.ServicioUsuario;
import datos.Excepcion.CredencialesInvalidaException;
import datos.Excepcion.DatabaseException;
import datos.Excepcion.PanelException;
import datos.Excepcion.ValidacionException;
import presentacion.abstracto.MensajeUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ControladorLogin implements ActionListener {

    private final ServicioUsuario servicioUsuario;
    private final PanelInicio panelInicio;

    public ControladorLogin(PanelInicio panelInicio, PanelManager panelManager) {
        this.panelInicio = panelInicio;
        this.servicioUsuario = new ServicioUsuario(panelInicio, panelManager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Estoy en el action performed");
        switch (e.getActionCommand()) {
            case "INICIAR":
                try {
                    servicioUsuario.login();
                } catch (CredencialesInvalidaException ex) {
                    panelInicio.mostrarError(ex.getMessage()); // <--- Mostrás al usuario
                } catch (PanelException ex) {
                    panelInicio.mostrarError("Error al cambiar de pantalla: " + ex.getMessage());
                } catch (SQLException | DatabaseException ex) {
                    panelInicio.mostrarError("Error de base de datos: " + ex.getMessage());
                } catch (Exception ex) {
                    panelInicio.mostrarError("Error inesperado: " + ex.getMessage());
                }
                panelInicio.limpiar();
                break;
            case "CANCELAR":
                panelInicio.limpiar();
                System.exit(0);
                break;
        }
    }
}

