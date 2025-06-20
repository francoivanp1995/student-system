package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class PanelBotonera extends JPanel {

    private Map<String, JButton> botones;
    private String actionCommand;

    public PanelBotonera() {
        botones = new LinkedHashMap<>();
        setLayout(new GridLayout(1, 0, 10, 10));
    }

    public void agregarBoton(String texto, String actionCommand) {
        if (botones.containsKey(texto)) return;
        JButton boton = new JButton(texto);
        boton.setActionCommand(actionCommand);
        botones.put(texto, boton);
        add(boton);
        revalidate();
        repaint();
    }

    public void agregarListener(ActionListener listener) {
        for (JButton boton : botones.values()) {
            boton.addActionListener(listener);
        }
    }

    public JButton getBoton(String texto) {
        return botones.get(texto);
    }
}
