package presentacion.abstracto;

import javax.swing.*;

public class FormularioUtilidad {

    public static <T> T mostrarFormulario(PanelFormularioBase<T> formulario, String titulo) {
        int resultado = JOptionPane.showConfirmDialog(
                null,
                formulario,
                titulo,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            return formulario.construirEntidad();
        }

        return null;
    }
}
