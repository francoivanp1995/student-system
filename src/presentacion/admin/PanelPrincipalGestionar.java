package presentacion.admin;

import datos.CursoTableModel;
import presentacion.PanelBotonera;
import presentacion.PanelManager;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import static datos.Comandos.*;

public class PanelPrincipalGestionar extends PanelBase {

    private JTable tabla;
    private JScrollPane scrollPane;
    private AbstractTableModel tableModel;
    private PanelBotonera botoneraCentro, botoneraSur;
    private final String textoReporte = "MOSTRAR REPORTE", textoExportarReporte = "EXPORTAR REPORTE";
    private final String textoReporteComando = "MOSTRARREPORTE", textoExportarReporteComando = "EXPORTARREPORTE";

    private final String titulo;

    public PanelPrincipalGestionar(PanelManager panelManager, AbstractTableModel modelo, String titulo) {
        super(panelManager);
        this.tableModel = modelo;
        this.titulo = titulo;
//        this.tabla = new JTable(new CursoTableModel());
//        add(new JScrollPane(tabla));
        setUIComponentesBase();
    }

    @Override
    protected void setUIComponentesBase() {
        setLayout(new BorderLayout());

        botoneraCentro = new PanelBotonera(new GridLayout(2, 3, 10, 10)); // dos filas de tres botones
        botoneraSur = new PanelBotonera();

        agregarBotonABotonera();
        tabla = new JTable();
        add(new JScrollPane(tabla));

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

    protected void agregarBotonABotonera() {
        botoneraCentro.agregarBoton(CREAR, CREAR);
//        botoneraCentro.agregarBoton(LEER, LEER);
        botoneraCentro.agregarBoton(ACTUALIZAR, ACTUALIZAR);
        botoneraCentro.agregarBoton(ELIMINAR, ELIMINAR);
        botoneraCentro.agregarBoton(textoExportarReporte,textoExportarReporteComando);
        botoneraCentro.agregarBoton(textoReporte,textoReporteComando);

        botoneraSur.agregarBoton(REGRESAR, REGRESAR);
        botoneraSur.agregarBoton(CANCELAR, CANCELAR);
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

    public void setListener(ActionListener listener) {
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

    public PanelManager getPanelManager(){
        return panelManager;
    }
}
