package datos;

import Servicios.DBInit;
import Servicios.TableCreator;
import datos.Excepcion.DatabaseException;
import presentacion.SystemUI;

import javax.swing.*;
import java.sql.Connection;

public class Init {

    private final TableCreator tableCreator;

    public Init(){
        this.tableCreator = new TableCreator();
    }

    public void iniciar() {
        try (Connection c = DBManager.connect()) {
            DBInit db = new DBInit(tableCreator);
            db.inicializarDB(c);
            c.commit();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(null, "Error al inicializar base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Iniciar UI
        try {
            SystemUI ui = new SystemUI();
            ui.iniciar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la interfaz: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
