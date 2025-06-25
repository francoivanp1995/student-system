package presentacion;

import javax.swing.*;
import java.awt.*;

public class PanelCampoFormulario extends JPanel {

    private JLabel etiqueta;
    private JTextField campoTexto;

    public PanelCampoFormulario(String label) {
        setLayout(new BorderLayout(5, 5));
        etiqueta = new JLabel(label);
        campoTexto = new JTextField();

        add(etiqueta, BorderLayout.WEST);
        add(campoTexto, BorderLayout.CENTER);
    }

    public String getTexto() {
        return campoTexto.getText();
    }

    public void setTexto(String texto) {
        campoTexto.setText(texto);
    }

    public JTextField getCampoTexto() {
        return campoTexto;
    }

    public JLabel getEtiqueta() {
        return etiqueta;
    }
}
