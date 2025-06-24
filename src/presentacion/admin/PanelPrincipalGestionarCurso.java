package presentacion.admin;

import presentacion.PanelBotonera;
import presentacion.PanelManager;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import static datos.Comandos.*;

public class PanelPrincipalGestionarCurso extends PanelBase {

    private JTable tablaCursos;
    private DefaultTableModel modeloTabla;
    private JButton botonAgregar, botonEditar, botonEliminar, botonVolver;
    private PanelBotonera botoneraSur;
    private PanelBotonera botoneraCentro;
    private PanelManager panelManager;
    private static final String labelPanel = "GESTIONAR CURSO";

    public PanelPrincipalGestionarCurso(PanelManager panelManager) {
        super(panelManager);
        setUIComponentesBase();
    }

    @Override
    protected void agregarBotonABotonera() {
        botoneraSur.agregarBoton(REGRESAR,REGRESAR);
        botoneraSur.agregarBoton(CANCELAR, CANCELAR);
        botoneraCentro.agregarBoton(CREAR,CREAR);
        botoneraCentro.agregarBoton(LEER,LEER);
        botoneraCentro.agregarBoton(ACTUALIZAR,ACTUALIZAR);
        botoneraCentro.agregarBoton(ELIMINAR,ELIMINAR);
    }

    @Override
    protected void setUIComponentesBase() {
        botoneraCentro = new PanelBotonera();
        botoneraSur = new PanelBotonera();

        agregarBotonABotonera();
        add(panelSuperior(), BorderLayout.NORTH);
        add(panelCentral(), BorderLayout.CENTER);
        add(botoneraSur, BorderLayout.SOUTH);
    }

    @Override
    protected JPanel panelSuperior() {
        JPanel tituloPanel = new JPanel();
        JLabel nombreTitulo = new JLabel(labelPanel);
        tituloPanel.add(nombreTitulo);
        return tituloPanel;
    }

    @Override
    protected JPanel panelCentral() {
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        centro.add(botoneraCentro);
        return centro;
    }

    public void setListener(ActionListener listener) {
        botoneraCentro.agregarListener(listener);
        botoneraSur.agregarListener(listener);
    }

//    public void actualizarTabla(List<Curso> cursos) {
//        modeloTabla.setRowCount(0); // Limpiar tabla
//        for (Curso curso : cursos) {
//            Object[] fila = {
//                    curso.getId(),
//                    curso.getNombre(),
//                    curso.getCupoMaximo(),
//                    curso.getPrecio(),
//                    curso.getNotaAprobacion(),
////                    curso.getProfesorDni()
//            };
//            modeloTabla.addRow(fila);
//        }
//    }
//
//    public long getCursoSeleccionadoId() {
//        int filaSeleccionada = tablaCursos.getSelectedRow();
//        if (filaSeleccionada != -1) {
//            return (long) modeloTabla.getValueAt(filaSeleccionada, 0); // ID está en la columna 0
//        }
//        return -1; // Ninguno seleccionado
//    }
//
//    public JTable getTablaCursos() {
//        return tablaCursos;
//    }
//
//    public void mostrarMensaje(String mensaje) {
//        JOptionPane.showMessageDialog(this, mensaje);
//    }
}
