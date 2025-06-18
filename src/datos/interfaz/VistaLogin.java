package datos.interfaz;

import java.awt.event.ActionListener;

public interface VistaLogin {

    String getUsuario();
    char[] getContrasena();
    void limpiar();
    void setListener(ActionListener listener);
}
