package datos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsuarioTableModel extends AbstractTableModel {

    private static final int COLUMNA_DNI = 0;
    private static final int COLUMNA_NOMBRE = 1;
    private static final int COLUMNA_EMAIL = 2;
    private static final int COLUMNA_ROL = 3;

    private final String[] nombreColumnas = {"DNI", "Nombre", "Email", "Rol"};
    private final Class<?>[] tipoColumnas = {String.class, String.class, String.class, String.class};

    private List<Usuario> contenido;

    public UsuarioTableModel() {
        this.contenido = new ArrayList<>();
    }

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.contenido = usuarios;
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
        Usuario usuario = contenido.get(rowIndex);
        return switch (columnIndex) {
            case COLUMNA_DNI -> usuario.getId();
            case COLUMNA_NOMBRE -> usuario.getNombre();
            case COLUMNA_EMAIL -> usuario.getEmail();
            case COLUMNA_ROL -> usuario.getRol().name();
            default -> "";
        };
    }

    public void setContenido(List<Usuario> contenido) {
        this.contenido = contenido;
        fireTableDataChanged();
    }

    public Usuario getUsuarioAt(int rowIndex) {
        return contenido.get(rowIndex);
    }
}
