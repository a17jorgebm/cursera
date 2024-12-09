package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = PostgresConnection.getConnection()) {
            if (conn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }
            System.out.println("Conectado con exito");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDatos(Connection conn, String dni, String nombre, String apel1, String apel2, String email, String tipo, boolean activo) throws SQLException {
        String query = "INSERT INTO Usuario (dni, nombre, apel1, apel2, email, tipo, activo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, dni);
        pstmt.setString(2, nombre);
        pstmt.setString(3, apel1);
        pstmt.setString(4, apel2);
        pstmt.setString(5, email);
        pstmt.setString(6, tipo);
        pstmt.setBoolean(7, activo);

        pstmt.executeUpdate();
        System.out.println("Usuario creado correctamente.");
    }

    public void listDatos(Connection conn) throws SQLException {
        String query = "SELECT * FROM Usuario";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String dni = rs.getString("dni");
            String nombre = rs.getString("nombre");
            String apel1 = rs.getString("apel1");
            String apel2 = rs.getString("apel2");
            String email = rs.getString("email");
            String tipo = rs.getString("tipo");
            boolean activo = rs.getBoolean("activo");
            System.out.println("DNI: " + dni + ", Nombre: " + nombre + " " + apel1 + " " + apel2 + ", Email: " + email + ", Tipo: " + tipo + ", Activo: " + activo);
        }
    }

    public void editDatos(Connection conn, String dni, String nuevoNombre, String nuevoEmail, boolean nuevoActivo) throws SQLException {
        String query = "UPDATE Usuario SET nombre = ?, email = ?, activo = ? WHERE dni = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.setString(1, nuevoNombre);
        pstmt.setString(2, nuevoEmail);
        pstmt.setBoolean(3, nuevoActivo);
        pstmt.setString(4, dni);

        int filasAfectadas = pstmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.out.println("No se encontró el usuario con el DNI: " + dni);
        }
    }

    public void deleteDatos(Connection conn, String dni) throws SQLException {
        String query = "DELETE FROM Usuario WHERE dni = ?";

        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.setString(1, dni);

        int filasAfectadas = pstmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("No se encontró el usuario con el DNI: " + dni);
        }
    }
}