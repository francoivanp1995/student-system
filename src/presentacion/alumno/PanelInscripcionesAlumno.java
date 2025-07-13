package presentacion.alumno;

import presentacion.PanelBotonera;
import presentacion.PanelManager;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import static datos.Comandos.*;

public class PanelInscripcionesAlumno extends PanelBase {

    private JTable tabla;
    private JScrollPane scrollPane;
    private AbstractTableModel tableModel;
    private PanelBotonera botoneraCentro, botoneraSur;
    private final String titulo;

    public PanelInscripcionesAlumno(PanelManager panelManager, AbstractTableModel modelo, String titulo) {
        super(panelManager);
        this.tableModel = modelo;
        this.titulo = titulo;
        setUIComponentesBase();
    }

    @Override
    protected void setUIComponentesBase() {
        setLayout(new BorderLayout());
        botoneraCentro = new PanelBotonera(new GridLayout(1, 2, 10, 10));
        botoneraSur = new PanelBotonera();

        agregarBotonABotonera();

        tabla = new JTable(tableModel);
        scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
        add(panelSuperior(), BorderLayout.NORTH);
        add(panelInferior(), BorderLayout.SOUTH);
    }

    private JPanel panelInferior() {
        JPanel inferior = new JPanel(new BorderLayout());

        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        centro.add(botoneraCentro);

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        sur.add(botoneraSur);

        inferior.add(centro, BorderLayout.NORTH);
        inferior.add(sur, BorderLayout.SOUTH);
        return inferior;
    }

    @Override
    protected void agregarBotonABotonera() {
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
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        centro.add(botoneraCentro);
        return centro;
    }

    @Override
    protected void setListener(ActionListener listener) {
        botoneraCentro.agregarListener(listener);
        botoneraSur.agregarListener(listener);
    }

    public int getFilaSeleccionada() {
        return tabla.getSelectedRow();
    }

    public JTable getTabla() {
        return this.tabla;
    }

    public void actualizarModelo(AbstractTableModel nuevoModelo) {
        this.tableModel = nuevoModelo;
        tabla.setModel(nuevoModelo);
        revalidate();
        repaint();
    }
}
