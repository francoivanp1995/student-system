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
        dbIniciador.inicializarBaseDeDatos();
        uiIniciador.iniciarFrame();
    }
}
