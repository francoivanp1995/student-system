package datos;

import datos.Excepcion.DatabaseException;

import java.sql.Connection;

public class DBInit {

    private final TableCreator tableCreator;

    public DBInit(TableCreator tableCreator){
        this.tableCreator = tableCreator;
    }

    public void crearTabla(Connection c) throws DatabaseException{
        tableCreator.crearTabla(c);
    }
}
