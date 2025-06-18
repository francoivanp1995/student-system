package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DB_DRIVER = "org.h2.Driver";
    //Cambiar DB_URL a la base de datos deseada.
    private static final String DB_URL = "jdbc:h2://home/francopaco/ejemplo_db/2025FINALLAB1.mv.db";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection connect() {
        Connection c = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            c = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            c.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return c;
    }
}
