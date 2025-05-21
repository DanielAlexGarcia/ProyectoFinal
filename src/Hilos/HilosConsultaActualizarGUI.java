package Hilos;

import java.sql.ResultSet;

import javax.swing.SwingUtilities;

import ConexionBD.ConexionBD;
import Vista.VentanaInicio;

public class HilosConsultaActualizarGUI {
    private final VentanaInicio interfaz;
    private String sql;

    public HilosConsultaActualizarGUI(VentanaInicio interfaz, String instruccionSQL) {
        this.interfaz = interfaz;
       this.sql = instruccionSQL;
    }

    public void consultarYActualizarGUI() {
        new Thread(() -> {								// Hilo que hace la consulta 
            try {
                // Obtener datos desde la base de datos
                ResultSet resultado = ConexionBD.getInstancia().ejecutarInstruccionSQL(sql);
                // Actualizar GUI en el hilo de eventos de Swing
                SwingUtilities.invokeLater(() -> {				//delega la tarea de actualizar la GUI al hilo principal (el que maneja la GUI)
                    //interfaz.actualizarConDatos(resultado);
                });

            } catch (Exception e) {
                System.err.println("Error al consultar datos: " + e.getMessage());
            }
        }).start();
    }
}

