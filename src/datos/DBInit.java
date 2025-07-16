package datos;

import datos.Excepcion.DatabaseException;
import datos.Excepcion.TablaException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBInit {

    public void inicializarBaseDeDatos() throws TablaException {
        Connection connection = DBManager.connect();
        try {
            TableCreator.crearTabla(connection);
            connection.commit();
            //tambien tengo que catchear el error de sql, che!
        } catch (DatabaseException e) {
            throw new TablaException("Error al crear la base de datos", e);
        } catch (Exception e) {
            throw new TablaException("Error inesperado al inicializar la base de datos", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new TablaException("Error al cerrar conexión: " + e);
            }
        }
    }
}
