package presentacion;

import Servicios.ServicioUsuario;
import datos.Excepcion.ControladorException;
import datos.Excepcion.ServicioException;
import datos.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static datos.Comandos.CERRAR;
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
                    if (u == null) {
                        panelInicio.mostrarError("Credenciales inválidas. Por favor, intente de nuevo.");
                    } else {
                        panelManager.setUsuarioLogueado(u);
                        panelManager.mostrarPanelPorRol(u.getRol());
                    }
                } catch (ServicioException ex) {
                    ex.printStackTrace();
                    panelInicio.mostrarError(ex.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    panelInicio.mostrarError("Error inesperado");
                }
                panelInicio.limpiar();
                break;
            case CERRAR:
                panelInicio.limpiar();
                System.exit(0);
                break;
        }
    }
}

