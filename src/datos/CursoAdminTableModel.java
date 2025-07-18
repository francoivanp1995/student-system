package datos;

import datos.DAO.InscripcionDAOH2Impl;
import datos.Excepcion.DAOException;
import datos.Excepcion.PresentacionException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CursoAdminTableModel extends BaseCursoTableModel {

    private static final int COLUMNA_NOMBRE = 0;
    private static final int COLUMNA_ANOTADOS = 1;
    private static final int COLUMNA_RECAUDACION = 2;



    private final String[] nombreColumnas = {"Nombre Curso", "Anotados", "Recaudación"};
    private final Class[] tipoColumnas = {String.class, Integer.class, Double.class};

    public CursoAdminTableModel() {
        super();
    }

    public CursoAdminTableModel(java.util.List<Curso> cursos) {
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

        if (columnIndex == COLUMNA_ANOTADOS || columnIndex == COLUMNA_RECAUDACION) {
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
            case COLUMNA_RECAUDACION -> cantidad * curso.getPrecio();
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
