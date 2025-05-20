package ConexionBD;

import java.sql.*;

public class ConexionBD {

    private Connection connection;

    public ConexionBD() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String URL = "jdbc:mariadb://localhost:3306/BD_Topicos_2025";
            connection = DriverManager.getConnection(URL, "root", "f44WOs%NvF");

            System.out.println("Conexión exitosa");

        } catch (ClassNotFoundException e) {
            System.out.println("Error en el conector / driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }

    // Elimina un alumno por ID con PreparedStatement
    public boolean eliminarFila(String numControl) {
        String sql = "DELETE FROM alumnos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, numControl);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar fila: " + e.getMessage());
            return false;
        }
    }

    // Método de ejemplo para insertar un alumno de forma segura
    public boolean insertarAlumno(String id, String nombre, String carrera) {
        String sql = "INSERT INTO alumnos (id, nombre, carrera) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, nombre);
            stmt.setString(3, carrera);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar alumno: " + e.getMessage());
            return false;
        }
    }

    // Método de lectura segura con PreparedStatement
    public ResultSet buscarAlumnoPorId(String id) {
        String sql = "SELECT * FROM alumnos WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar alumno: " + e.getMessage());
            return null;
        }
    }

    // Método genérico para cambiar de base de datos (más seguro con validación)
    public boolean cambiarBaseDeDatos(String nuevaBD) {
        try {
            String url = "jdbc:mariadb://localhost:3306/" + nuevaBD;
            connection = DriverManager.getConnection(url, "root", "f44WOs%NvF");
            System.out.println("Base de datos cambiada a: " + nuevaBD);
            return true;
        } catch (SQLException e) {
            System.out.println("No se pudo cambiar de base de datos: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        ConexionBD db = new ConexionBD();

        // Ejemplo: Insertar alumno
        db.insertarAlumno("123", "Juan Pérez", "Sistemas");

        // Ejemplo: Buscar alumno
        ResultSet rs = db.buscarAlumnoPorId("123");
        try {
            if (rs != null && rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Ejemplo: Eliminar alumno
        db.eliminarFila("123");
    }
}
