package Servicios;

import datos.DBInit;
import datos.Excepcion.*;
import presentacion.UIIniciador;

public class AppManager {

    private final DBInit dbIniciador;
    private final UIIniciador uiIniciador;

    public AppManager() {
        this.dbIniciador = new DBInit();
        this.uiIniciador = new UIIniciador();
    }

    public void iniciarAplicacion() throws TablaException, UIException {
        try {
            dbIniciador.inicializarBaseDeDatos();
            uiIniciador.iniciarFrame();
        } catch (TablaException e) {
            e.printStackTrace();
            //Subo la excepcion hasta el main.
            throw e;
        } catch (UIException e) {
            e.printStackTrace();
            //Subo la excepcion hasta el main.
            throw e;
        }
    }

}
