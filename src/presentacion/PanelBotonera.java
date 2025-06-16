package presentacion;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datos.Excepcion.ErrorOperacionException;
import datos.Excepcion.ValidacionException;

public class PanelBotonera extends JPanel{

	private Map<String, JButton> botones;
	
    public PanelBotonera(Map<String, Consumer<JButton>> accionesPorTexto) throws ErrorOperacionException, ValidacionException {
        this.botones = new LinkedHashMap<>();
        setLayout(new GridLayout(1, accionesPorTexto.size(), 10, 10));

        accionesPorTexto.forEach((texto, accion) -> {
            JButton boton = new JButton(texto);

            boton.addActionListener(e -> {
                try {
                    if (accion != null) {
                        accion.accept(boton);
                    } else {
                        throw new IllegalArgumentException("No hay acción definida para el botón: " + texto);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Error inesperado: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }
            });

            botones.put(texto, boton);
            add(boton);
        });
    }
    
    public void setAcciones() {
    	//Implementar lógica de arriba.
    };
}
