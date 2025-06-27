package main;

import datos.DBInit;
import datos.DBManager;
import datos.Excepcion.DatabaseException;
import datos.Excepcion.TablaException;
import datos.Excepcion.UIException;
import datos.Excepcion.PanelException;
import datos.TableCreator;
import presentacion.UIIniciador;

import java.sql.Connection;

public class AppManager {

    private final TableCreator tableCreator;

    public AppManager() throws TablaException, UIException {
        this.tableCreator = new TableCreator();
        iniciarCreacionTablas();
        iniciarPanelManager();
    }

    public void iniciarCreacionTablas() throws TablaException {
        try (Connection c = DBManager.connect()) {
            DBInit db = new DBInit(tableCreator);
            db.crearTabla(c);
            c.commit();
        } catch (DatabaseException e) {
            throw new TablaException("Error al crear la base de datos: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new TablaException("Error inesperado desde el iniciador de Creacion Tablas: " + e.getMessage(), e);
        }
    }

    public void iniciarPanelManager() throws UIException {
        //Chequear Exception
        try {
            UIIniciador ui = new UIIniciador();
            ui.iniciarFrame();
        } catch (Exception e) {
            throw new UIException("Error con el inicio de la UI: " + e.getMessage(), e);
        }
    }
}
