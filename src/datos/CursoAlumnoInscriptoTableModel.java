package datos;

import java.util.List;

public class CursoAlumnoInscriptoTableModel extends BaseCursoTableModel{

    private List<Curso> cursos;
    private final String[] columnas = {"ID", "Nombre", "Descripción"};

    public CursoAlumnoInscriptoTableModel(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public int getRowCount() {
        return cursos.size();
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
        Curso curso = cursos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> curso.getId();
            case 1 -> curso.getNombre();
//            case 2 -> curso.getDescripcion();
            default -> null;
        };
    }
}
