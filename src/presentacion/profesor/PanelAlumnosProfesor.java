package presentacion.profesor;

import presentacion.PanelBotonera;
import presentacion.PanelManager;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import static datos.Comandos.*;

public class PanelAlumnosProfesor extends PanelBase {

    private JTable tabla;
    private JScrollPane scrollPane;
    private AbstractTableModel tableModel;
    private PanelBotonera botoneraSur;
    private final String titulo;

    public PanelAlumnosProfesor(PanelManager panelManager, AbstractTableModel modelo, String titulo) {
        super(panelManager);
        this.tableModel = modelo;
        this.titulo = titulo;
        setUIComponentesBase();
    }

    @Override
    protected void setUIComponentesBase() {
        setLayout(new BorderLayout());

        botoneraSur = new PanelBotonera();
        agregarBotonABotonera();

        tabla = new JTable(tableModel);
        scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
        add(panelSuperior(), BorderLayout.NORTH);
        add(panelInferior(), BorderLayout.SOUTH);
    }

    private JPanel panelInferior() {
        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        inferior.add(botoneraSur);
        return inferior;
    }

    @Override
    protected void agregarBotonABotonera() {
        botoneraSur.agregarBoton(GUARDAR, GUARDAR);
        botoneraSur.agregarBoton(REGRESAR, REGRESAR);
        botoneraSur.agregarBoton(CERRAR, CERRAR);
    }

    @Override
    protected JPanel panelSuperior() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(titulo.toUpperCase());
        panel.add(label);
        return panel;
    }

    @Override
    protected JPanel panelCentral() {
        return null;
    }

    @Override
    protected void setListener(ActionListener listener) {
        botoneraSur.agregarListener(listener);
    }

    public int getFilaSeleccionada() {
        return tabla.getSelectedRow();
    }

    public JTable getTabla() {
        return tabla;
    }

    public void actualizarModelo(AbstractTableModel nuevoModelo) {
        this.tableModel = nuevoModelo;
        if (tabla == null) {
            tabla = new JTable(tableModel);
            scrollPane = new JScrollPane(tabla);
            add(scrollPane, BorderLayout.CENTER);
        } else {
            tabla.setModel(nuevoModelo);
        }
        revalidate();
        repaint();
    }
}
