package datos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseInscripcionTableModel extends AbstractTableModel {

    protected List<Inscripcion> contenido = new ArrayList<>();

    public BaseInscripcionTableModel() {}

    public BaseInscripcionTableModel(List<Inscripcion> inscripciones) {
        this.contenido = inscripciones;
    }

    @Override
    public int getRowCount() {
        return contenido.size();
    }

    public void setContenido(List<Inscripcion> contenido) {
        this.contenido = contenido;
        fireTableDataChanged();
    }

    public Inscripcion getInscripcionAt(int rowIndex) {
        return contenido.get(rowIndex);
    }

    public List<Inscripcion> getInscripciones() {
        return contenido;
    }

}
