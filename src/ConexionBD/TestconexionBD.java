package ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestconexionBD {
	Scanner ins = new Scanner(System.in);

   /* public static void main(String[] args) {
        ConexionBD conexion = new ConexionBD();

        // 🔹 Paso 1: Insertar un donador
        boolean insertado = conexion.insertarDonador(
            null,                         // DonadorID
            "Colonia Centro",           // col
            "Av. Independencia",        // calle
            "123",                      // numExt
            "A",                        // numInt
            "+525512345678",               // telefon
            "donador@correoll.com",       // email
            1,                          // RelacionUnilD
            2,                          // tipoDonadorID
            13,                          // claseID
            4                           // ProgramaDonacionID
        );

        if (insertado) {
            System.out.println("✅ Donador insertado exitosamente.");
        } else {
            System.out.println("❌ Falló la inserción del donador.");
        }

        // 🔹 Paso 2: Buscar el donador por ID
        ResultSet rs = conexion.buscarDonadorPorId(14);
        try {
            if (rs != null && rs.next()) {
                System.out.println("🔍 Donador encontrado:");
                System.out.println("ID: " + rs.getInt("DonadorID"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Teléfono: " + rs.getString("TelefonoCont"));
            } else {
                System.out.println("⚠️ No se encontró el donador con ID.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al leer los datos del donador: " + e.getMessage());
        }
        
        // Probar actualización con datos de prueba
		/*boolean exito = conexion.actualizarDonador(
		    13,                  // donadorID
		    "Centro",           // colonia
		    "Av. Reforma",      // calle
		    "123",              // numExt
		    "4B",               // numInt
		    "555-1234",         // telefono
		    "correo@ejemplo.com", // email
		    2,                  // RelaUniOP
		    1,                  // tipoDona
		    13,                  // claseOP
		    5                   // progDonaOP
		);

		if (exito) {
		    System.out.println("Donador actualizado correctamente.");
		} else {
		    System.out.println("No se pudo actualizar el donador.");
		}

        // 🔹 Paso 3: Eliminar el donador
        boolean eliminado = conexion.eliminarFila(11);

        if (eliminado) {
            System.out.println("🗑️ Donador eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar el donador.");
        }
        
        }
        */
    
}

