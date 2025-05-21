package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import ConexionBD.ConexionBD;



public class DonadorDAO {

	
	public boolean actualizarDonador(int donadorID, String col, String calle, String numExt, String numInt, String telefon, String email, Integer RelaUniOP, Integer tipoDona, Integer claseOP, Integer progDonaOP) {
	    String sql = "UPDATE Donador SET colonia = ?, calle = ?, numExt = ?, numInt = ?, TelefonoCont = ?, " +
	                 "email = ?, RelacionUnilD = ?, tipoDonadorID = ?, claseID = ?, ProgramaDonacionID = ? " +
	                 "WHERE DonadorID = ?";
	    try (PreparedStatement stmt = ConexionBD.getInstancia().getConnection().prepareStatement(sql)) {
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
	 
	 
	 
	    // Elimina un alumno por ID con PreparedStatement
	    public boolean eliminarFila(int DonadorID) {
	        String sql = "DELETE FROM Donador WHERE DonadorID = ?";
	        try (PreparedStatement stmt = ConexionBD.getInstancia().getConnection().prepareStatement(sql)) {
	            stmt.setInt(1, DonadorID);
	            int filasAfectadas = stmt.executeUpdate();
	            return filasAfectadas > 0;
	        } catch (SQLException e) {
	            System.out.println("Error al eliminar fila: " + e.getMessage());
	            return false;
	        }
	    }

	 // Método de ejemplo para insertar un alumno de forma segura
	    public boolean insertarDonador(Integer idOP, String col, String calle, String numExt, String numInt, String telefon, String email, Integer RelaUniOP, Integer tipoDona, Integer claseOP, Integer progDonaOP) {

	        String sql;
	        if (idOP == null) {
	            sql = "INSERT INTO Donador (colonia, calle, numExt, numInt, TelefonoCont, email, RelacionUnilD, tipoDonadorID, claseID, ProgramaDonacionID) " +
	                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        } else {
	            sql = "INSERT INTO Donador (DonadorID, colonia, calle, numExt, numInt, TelefonoCont, email, RelacionUnilD, tipoDonadorID, claseID, ProgramaDonacionID) " +
	                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        }

	        try (PreparedStatement stmt = ConexionBD.getInstancia().getConnection().prepareStatement(sql)) {
	            int paramIndex = 1;
	            
	            if (idOP != null) {
	                stmt.setInt(paramIndex++, idOP);
	            }
	            
	            stmt.setString(paramIndex++, col);
	            stmt.setString(paramIndex++, calle);
	            stmt.setString(paramIndex++, numExt);
	            stmt.setString(paramIndex++, numInt);
	            stmt.setString(paramIndex++, telefon);
	            stmt.setString(paramIndex++, email);
	            
	            if (RelaUniOP != null) {
	                stmt.setInt(paramIndex++, RelaUniOP);
	            } else {
	                stmt.setNull(paramIndex++, Types.INTEGER);
	            }
	            
	            if (tipoDona != null) {
	                stmt.setInt(paramIndex++, tipoDona);
	            } else {
	                stmt.setNull(paramIndex++, Types.INTEGER);
	            }
	            
	            if (claseOP != null) {
	                stmt.setInt(paramIndex++, claseOP);
	            } else {
	                stmt.setNull(paramIndex++, Types.INTEGER);
	            }
	            
	            if (progDonaOP != null) {
	                stmt.setInt(paramIndex++, progDonaOP);
	            } else {
	                stmt.setNull(paramIndex++, Types.INTEGER);
	            }
	            
	            int filasAfectadas = stmt.executeUpdate();
	            return filasAfectadas > 0;
	        } catch (SQLException e) {
	            System.out.println("Error al insertar Donador: " + e.getMessage());
	            return false;
	        }
	    }


	 // Método de lectura segura con PreparedStatement
	    public ResultSet buscarDonadorPorId(int donadorID) {
	        String sql = "SELECT * FROM Donador WHERE DonadorID = ?";
	        try {
	        	PreparedStatement stmt = ConexionBD.getInstancia().getConnection().prepareStatement(sql);
	            stmt.setInt(1, donadorID);
	            return stmt.executeQuery();
	        } catch (SQLException e) {
	            System.out.println("Error al buscar donador: " + e.getMessage());
	            return null;
	        }
	    }
	    
	 // Método para listar todos los donadores
	    public ResultSet listarDonadores() {
	        String sql = "SELECT * FROM Donador";
	        try {
	        	PreparedStatement stmt = ConexionBD.getInstancia().getConnection().prepareStatement(sql);
	            return stmt.executeQuery(sql);
	        } catch (SQLException e) {
	            System.out.println("Error al listar donadores: " + e.getMessage());
	            return null;
	        }
	    }
	
}






