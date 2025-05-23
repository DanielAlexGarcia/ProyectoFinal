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
    private DonadorDAO dona = new DonadorDAO(null);
    private String ventana;
    

    public HilosConsultaActualizarGUI(Integer consultaID, String ventana, VentanaInicio interfaces) {
       this.id = consultaID;
       this.ventana = ventana;
       this.interfa = interfaces;
    }

    public void consultarYActualizarGUI() {
    	interfa.showMessageDialog(interfa.frame, "Buscando...", true);
        new Thread(() -> {								// Hilo que hace la consulta 
            try {
            	if(id != null && id != 0) {
            		int ID = (int) id;
            		// Obtener datos desde la base de datos
                    Donador donana = dona.buscarDonadorPorId(ID);
                    // Actualizar GUI en el hilo de eventos de Swing
                    SwingUtilities.invokeLater(() -> {				//delega la tarea de actualizar la GUI al hilo principal (el que maneja la GUI)
                    	interfa.showMessageDialog(interfa.frame, "Buscando...", false);
                    	interfa.interfaz.setDonador(donana, ventana);
                        
                    });
            	}
            	else {
            		ResultSet donana = dona.listarDonadores();
            		interfa.showMessageDialog(interfa.frame, "Buscando...", false);
            		interfa.interfaz.setResultSet(donana);
            	}
                

            } catch (Exception e) {
                System.err.println("Error al consultar datos: " + e.getMessage());
            }
        }).start();
    }
}

