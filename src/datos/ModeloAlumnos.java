package datos;

import Servicios.LibreriaValidaciones;
import datos.Excepcion.ModeloException;
import datos.Excepcion.ValidacionException;

import java.util.List;

public class ModeloAlumnos extends BaseInscripcionTableModel {

    private final String[] columnas = {"DNI", "Nombre", "Apellido"," Curso" , "Nota Final"};

    public ModeloAlumnos(List<Inscripcion> inscripciones) {
        super(inscripciones);
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Inscripcion insc = contenido.get(rowIndex);
        switch (columnIndex) {
            case 0: return insc.getAlumno().getId();
            case 1: return insc.getAlumno().getNombre();
            case 2: return insc.getAlumno().getApellido();
            case 3: return insc.getCurso().getNombre();
            case 4: return insc.getNotaFinal();
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 4) {
            Inscripcion insc = contenido.get(rowIndex);
            if (aValue == null || aValue.toString().trim().isEmpty()) {
                insc.setNotaFinal(null);
            } else {
                try {
                    int nota = Integer.parseInt(aValue.toString());
                    try {
                        LibreriaValidaciones.validarNota(nota);
                    } catch (ValidacionException e) {
                        throw new ModeloException(e);
                    }
                    insc.setNotaFinal(nota);
                } catch (NumberFormatException e) {
                    throw new ModeloException("Debe ingresar un número válido");
                }
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
}

