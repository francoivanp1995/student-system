package presentacion;

import datos.Curso;
import presentacion.abstracto.PanelBase;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import static datos.Comandos.CANCELAR;
import static datos.Comandos.REGRESAR;

public class PanelGestionarCurso extends PanelBase {

    private JTable tablaCursos;
    private DefaultTableModel modeloTabla;
    private JButton botonAgregar, botonEditar, botonEliminar, botonVolver;
    private PanelBotonera botoneraSur;
    private PanelBotonera botoneraCentro;
    private PanelManager panelManager;
    private final String textoCrear  = "CREAR";
    private final String crearComando  = "CREAR";
    private final String textoLeer  = "LEER";
    private final String leerComando  = "LEER";
    private final String textoActualizar  = "ACTUALIZAR";
    private final String actualizarComando  = "ACTUALIZAR";
    private final String textoEliminar  = "ELIMINAR";
    private final String eliminarComando  = "ELIMINAR";
    private final String textoRegresar = REGRESAR;
    private final String textoCancelar = CANCELAR;

    public PanelGestionarCurso(PanelManager panelManager) {
        super(panelManager);
        setUIComponentesBase();
//        setLayout(new BorderLayout());
//
//        // Columnas basadas en la tabla CURSOS
//        String[] columnas = { "ID", "Nombre", "Cupo", "Precio", "Nota Aprobación", "Profesor DNI" };
//        modeloTabla = new DefaultTableModel(columnas, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        tablaCursos = new JTable(modeloTabla);
//        JScrollPane scrollPane = new JScrollPane(tablaCursos);
//
//        // Botones
//        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        botonAgregar = new JButton("Agregar");
//        botonEditar = new JButton("Editar");
//        botonEliminar = new JButton("Eliminar");
//        botonVolver = new JButton("Volver");
//
//        panelBotones.add(botonAgregar);
//        panelBotones.add(botonEditar);
//        panelBotones.add(botonEliminar);
//        panelBotones.add(botonVolver);
//
//        add(new JLabel("Gestión de Cursos", SwingConstants.CENTER), BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    protected void agregarBotonABotonera() {
        botoneraSur.agregarBoton(textoRegresar,REGRESAR);
        botoneraSur.agregarBoton(textoCancelar, CANCELAR);
        botoneraCentro.agregarBoton(textoCrear,crearComando);
        botoneraCentro.agregarBoton(textoLeer,leerComando);
        botoneraCentro.agregarBoton(textoActualizar,actualizarComando);
        botoneraCentro.agregarBoton(textoEliminar,eliminarComando);
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
        JLabel nombreTitulo = new JLabel("GESTIONAR CURSO");
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
