package datos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCursoTableModel extends AbstractTableModel {

    protected List<Curso> contenido = new ArrayList<>();

    public BaseCursoTableModel() {}

    public BaseCursoTableModel(List<Curso> cursos) {
        this.contenido = cursos;
    }

    @Override
    public int getRowCount() {
        return contenido.size();
    }

    public void setContenido(List<Curso> contenido) {
        this.contenido = contenido;
        fireTableDataChanged();
    }

    public Curso getCursoAt(int rowIndex) {
        return contenido.get(rowIndex);
    }
}
