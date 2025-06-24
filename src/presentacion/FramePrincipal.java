package presentacion;

import javax.swing.*;

public class FramePrincipal {

    private JFrame frame;
    private PanelManager panelManager;
    private static final String nombreSistema = "SISTEMA DE ALUMNOS";

    public FramePrincipal(PanelManager panelManager) {
        this.panelManager = panelManager;
        frame = new JFrame(nombreSistema);
        configurarFrame();
        panelManager.setFrame(frame);
    }

    private void configurarFrame() {
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
