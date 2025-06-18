package datos;

import datos.interfaz.VistaLogin;
import presentacion.PanelManager;

import java.awt.event.ActionEvent;

public class ControladorLogin {

    private final VistaLogin vista;
    private final PanelManager panelManager;

    public ControladorLogin(VistaLogin vista, PanelManager panelManager) {
        this.vista = vista;
        this.panelManager = panelManager;
//        this.vista.setListener(this);
    }

//    @Override
    public void actionPerformed(ActionEvent e) {
            System.out.println("1");
//        String usuario = vista.getUsuario();
//        char[] password = vista.getContrasena();
//
//        if (usuario.isBlank() || password.length == 0) {
//            vista.mostrarMensajeValidacion("Usuario o contraseña vacíos.");
//            return;
//        }
//
//        // Lógica de autenticación simulada:
//        Usuario user = ServicioAutenticacion.autenticar(usuario, password);
//
//        if (user == null) {
//            vista.mostrarMensajeValidacion("Credenciales incorrectas.");
//            return;
//        }
//
//        vista.limpiar();
//
//        switch (user.getRol()) {
//            case ALUMNO -> System.out.println("1");
//            case PROFESOR -> System.out.println("21");
//            case ADMINISTRADOR -> System.out.println("31");
//        }
    }
}
