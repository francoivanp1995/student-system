package datos;

import datos.Excepcion.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

    public void crearTabla(Connection c) throws DatabaseException {
        try {
            crearTablaUsuarios(c);
            crearTablaCursos(c);
            crearTablaInscripciones(c);
        } catch (DatabaseException e){
            throw e;
        }
    }

    private void crearTablaUsuarios(Connection c) throws DatabaseException {
        String sql = """
            CREATE TABLE IF NOT EXISTS USUARIOS (
                dni VARCHAR(20) PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL,
                apellido VARCHAR(100) NOT NULL,
                nombre_de_usuario VARCHAR(50) UNIQUE NOT NULL,
                email VARCHAR(150) UNIQUE NOT NULL,
                contrasenia VARCHAR(64) NOT NULL,
                tipo VARCHAR(15) NOT NULL,
                limite_cursos INT
            )
            """;

        ejecutar(c, sql, "USUARIOS");
    }

    private void crearTablaCursos(Connection c) throws DatabaseException {
        String sql = """
            CREATE TABLE IF NOT EXISTS CURSOS (
                id IDENTITY PRIMARY KEY,
                nombre VARCHAR(150) NOT NULL,
                cupo INT NOT NULL,
                precio DECIMAL(10,2) NOT NULL,
                nota_aprobacion DECIMAL(3,1) NOT NULL,
                profesor_dni VARCHAR(20),
                FOREIGN KEY (profesor_dni) REFERENCES USUARIOS(dni)
            )
            """;

        ejecutar(c, sql, "CURSOS");
    }

    private void crearTablaInscripciones(Connection c) throws DatabaseException {
        String sql = """
            CREATE TABLE IF NOT EXISTS INSCRIPCIONES (
                id IDENTITY PRIMARY KEY,
                alumno_dni VARCHAR(20) NOT NULL,
                curso_id BIGINT NOT NULL,
                nota_final DECIMAL(3,1),
                aprobado BOOLEAN,
                FOREIGN KEY (alumno_dni) REFERENCES USUARIOS(dni),
                FOREIGN KEY (curso_id) REFERENCES CURSOS(id),
                UNIQUE (alumno_dni, curso_id)
            )
            """;

        ejecutar(c, sql, "INSCRIPCIONES");
    }

    private void ejecutar(Connection c, String sql, String tabla) throws DatabaseException {
        try (Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("Error al crear la tabla " + tabla, e);
        }
    }
}
