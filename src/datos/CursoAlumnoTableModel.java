package datos;

import datos.DAO.InscripcionDAOH2Impl;
import datos.Excepcion.DAOException;
import datos.Excepcion.PresentacionException;

public class CursoAlumnoTableModel extends BaseCursoTableModel {

    private static final int COLUMNA_NOMBRE = 0;
    private static final int COLUMNA_ANOTADOS = 1;
    private static final int COLUMNA_CUPO = 2;

    private final String[] nombreColumnas = {"Nombre Curso", "Anotados", "Cupo"};
    private final Class[] tipoColumnas = {String.class, Integer.class, Integer.class};

    public CursoAlumnoTableModel() {
        super();
    }

    public CursoAlumnoTableModel(java.util.List<Curso> cursos) {
        super(cursos);
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
        int cantidad = 0;

        if (columnIndex == COLUMNA_ANOTADOS) {
            try {
                InscripcionDAOH2Impl dao = new InscripcionDAOH2Impl();
                cantidad = dao.contarInscripcionesPorCurso(curso);
            } catch (DAOException e) {
                e.printStackTrace();
                throw new PresentacionException(e);
            }
        }

        return switch (columnIndex) {
            case COLUMNA_NOMBRE -> curso.getNombre();
            case COLUMNA_ANOTADOS -> cantidad;
            case COLUMNA_CUPO -> curso.getCupoMaximo();
            default -> "";
        };
    }
}
