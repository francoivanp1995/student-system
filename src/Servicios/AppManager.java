package Servicios;

import datos.DBManager;
import datos.Excepcion.*;
import datos.TableCreator;
import presentacion.UIIniciador;

import java.sql.Connection;
import java.sql.SQLException;

public class AppManager {

    private final DBInit databaseInitializer;
    private final UIIniciador uiIniciador;

    public AppManager() {
        this.databaseInitializer = new DBInit();
        this.uiIniciador = new UIIniciador();
    }

    public void iniciarAplicacion() throws TablaException, UIException {
        databaseInitializer.inicializarBaseDeDatos();
        uiIniciador.iniciarFrame();
    }

//    public void iniciarCreacionTablas() throws TablaException {
//        Connection connection = DBManager.connect();
//        try {
//            DBInit db = new DBInit(tableCreator);
//            db.crearTabla(connection);
//            connection.commit();
//        } catch (DatabaseException e) {
//            e.printStackTrace();
//            throw new TablaException("Error al crear la base de datos.",e);
//        } catch (Exception e) {
//            throw new TablaException("Error inesperado desde el iniciador de Creacion Tablas",e);
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                throw new TablaException("Error al cerrar conexión: " + e.getMessage(), e);
//            }
//        }
//    }

//    public void iniciarPanelManager() throws UIException {
//        //Chequear Exception
//        try {
//            UIIniciador ui = new UIIniciador();
//            ui.iniciarFrame();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new UIException("Error con el inicio de la UI",e);
//        }
//    }
}
