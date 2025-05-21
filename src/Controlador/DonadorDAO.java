package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import ConexionBD.ConexionBD;



public class DonadorDAO {
	
	private Connection connection;

	
	 public boolean actualizarDonador(int donadorID, String col, String calle, String numExt, String numInt, String telefon, String email, Integer RelaUniOP, Integer tipoDona, Integer claseOP, Integer progDonaOP) {
	        String sql = "UPDATE Donador SET colonia = ?, calle = ?, numExt = ?, numInt = ?, TelefonoCont = ?, " +
	                     "email = ?, RelacionUnilD = ?, tipoDonadorID = ?, claseID = ?, ProgramaDonacionID = ? " +
	                     "WHERE DonadorID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, col);
	            stmt.setString(2, calle);
	            stmt.setString(3, numExt);
	            stmt.setString(4, numInt);
	            stmt.setString(5, telefon);
	            stmt.setString(6, email);
	            stmt.setObject(7, RelaUniOP, Types.INTEGER);
	            stmt.setObject(8, tipoDona, Types.INTEGER);
	            stmt.setObject(9, claseOP, Types.INTEGER);
	            stmt.setObject(10, progDonaOP, Types.INTEGER);
	            stmt.setInt(11, donadorID);
	            int filasAfectadas = stmt.executeUpdate();
	            return filasAfectadas > 0;
	        } catch (SQLException e) {
	            System.out.println("Error al actualizar donador: " + e.getMessage());
	            return false;
	        }
	    }
	
}
