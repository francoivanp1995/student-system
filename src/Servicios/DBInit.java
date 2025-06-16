package Servicios;

import datos.Excepcion.DatabaseException;
import datos.TableManager;

import javax.xml.crypto.Data;
import java.sql.Connection;

public class DBInit {

    private final TableCreator tableCreator;

    public DBInit(TableCreator tableCreator){
        this.tableCreator = tableCreator;
    }

    public void inicializarDB(Connection c) throws DatabaseException{
        tableCreator.crearTabla(c);
    }
}
