package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    public static Connection getConnection() throws SQLException {
        // Cambia estos valores por los de tu base de datos
        String url = "jdbc:postgresql://localhost:5432/cursera";
        String user = "root";
        String password = "root";

        try {
            // Cargar el driver
            Class.forName("org.postgresql.Driver");
            // Establecer la conexión
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

