package Hilos;

import java.sql.ResultSet;

import javax.swing.SwingUtilities;

import ConexionBD.ConexionBD;
import Controlador.DonadorDAO;
import Modelo.Donador;
import Vista.VentanaInicio;

public class HilosConsultaActualizarGUI {
	private VentanaInicio interfa;
    private Integer id;
    private DonadorDAO dona = new DonadorDAO();
    private String ventana;
    

    public HilosConsultaActualizarGUI(Integer consultaID, String ventana) {
       this.id = consultaID;
       this.ventana = ventana;
    }

    public void consultarYActualizarGUI() {
        new Thread(() -> {								// Hilo que hace la consulta 
            try {
            	if(id != null && id != 0) {
            		int ID = (int) id;
            		// Obtener datos desde la base de datos
                    Donador donana = dona.buscarDonadorPorId(ID);
                    // Actualizar GUI en el hilo de eventos de Swing
                    SwingUtilities.invokeLater(() -> {				//delega la tarea de actualizar la GUI al hilo principal (el que maneja la GUI)
                        interfa.interfaz.setDonador(donana, ventana);
                    });
            	}
                

            } catch (Exception e) {
                System.err.println("Error al consultar datos: " + e.getMessage());
            }
        }).start();
    }
}

