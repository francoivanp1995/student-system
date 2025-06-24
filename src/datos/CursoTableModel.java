package datos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CursoTableModel extends AbstractTableModel {

    private static final int COLUMNA_NOMBRE = 0;
    private static final int COLUMNA_ANOTADOS = 1;
    private static final int COLUMNA_RECAUDACION = 2;

    private final String[] nombreColumnas = {"Nombre Curso", "Anotados", "Recaudación"};
    private final Class[] tipoColumnas = {String.class, Integer.class, Double.class};

    private List<Curso> contenido;

    public CursoTableModel() {
        this.contenido = new ArrayList<>();
    }

    public CursoTableModel(List<Curso> cursos) {
        this.contenido = cursos;
    }

    @Override
    public int getRowCount() {
        return contenido.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }

    @Override
    public String getColumnName(int col) {
        return nombreColumnas[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return tipoColumnas[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Curso curso = contenido.get(rowIndex);
        return switch (columnIndex) {
            case COLUMNA_NOMBRE -> curso.getNombre();
            case COLUMNA_ANOTADOS -> curso.getInscripciones();
            case COLUMNA_RECAUDACION -> curso.getPrecio();
            default -> "";
        };
    }

    public void setContenido(List<Curso> contenido) {
        this.contenido = contenido;
        fireTableDataChanged();
    }

    public Curso getCursoAt(int rowIndex) {
        return contenido.get(rowIndex);
    }
}
