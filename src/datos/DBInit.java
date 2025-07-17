package datos;

import datos.Excepcion.DatabaseException;
import datos.Excepcion.TablaException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBInit {

    public void inicializarBaseDeDatos() throws TablaException {
        Connection connection = null;
        try {
            connection = DBManager.connect();
            TableCreator.crearTabla(connection);
            connection.commit();
        } catch (DatabaseException e) {
            throw new TablaException("Error al crear la base de datos", e);
        } catch (Exception e) {
            throw new TablaException("Error inesperado al inicializar la base de datos", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new TablaException("Error al cerrar conexión: ", e);
                }
            }
        }
    }
}
