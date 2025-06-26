package presentacion;

import Servicios.ServicioUsuario;
import datos.Excepcion.CredencialesInvalidaException;
import datos.Excepcion.DatabaseException;
import datos.Excepcion.PanelException;
import datos.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static datos.Comandos.CANCELAR;
import static datos.Comandos.INICIAR;

public class ControladorPanelInicio implements ActionListener {

    private final ServicioUsuario servicioUsuario;
    private final PanelInicio panelInicio;
    private PanelManager panelManager;

    public ControladorPanelInicio(PanelInicio panelInicio, PanelManager panelManager) {
        this.panelInicio = panelInicio;
        this.panelManager = panelManager;
        this.servicioUsuario = new ServicioUsuario(panelInicio, panelManager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case INICIAR:
                String usuario = panelInicio.getUsuario();
                String contrasenia = new String(panelInicio.getContrasena());
                try {
                    Usuario u = servicioUsuario.login(usuario, contrasenia);
                    panelManager.mostrarPanelPorRol(u.getRol());
                } catch (CredencialesInvalidaException ex) {
                    panelInicio.mostrarError(ex.getMessage());
                } catch (PanelException ex) {
                    panelInicio.mostrarError("Error al cambiar de pantalla: " + ex.getMessage());
                } catch (DatabaseException ex) {
                    panelInicio.mostrarError("Error de base de datos: " + ex.getMessage());
                } catch (Exception ex) {
                    panelInicio.mostrarError("Error inesperado: " + ex.getMessage());
                }
                panelInicio.limpiar();
                break;
            case CANCELAR:
                panelInicio.limpiar();
                System.exit(0);
                break;
        }
    }
}

