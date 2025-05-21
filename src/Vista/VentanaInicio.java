package Vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ConexionBD.ConexionBD;
import Modelo.Donador;



class GUI extends JFrame implements ActionListener{
	ArrayList<String> errores = new ArrayList<String>();
	Donador donadona;
	
	ConexionBD conexionBD;
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	JMenuBar menubar = new JMenuBar();
	JMenu acciones, Opciones;
	JMenuBar menuBar = new JMenuBar();
	JMenuItem donadores, añadir, eliminar, modificar, cargaAll, cargaOpcion;
	JPasswordField contraseña;
	JTextField Usuario;
	JLabel problema1 = new JLabel();
	JLabel problema2 = new JLabel();
	JButton boton1;
	JInternalFrame InicioSecion;
	JInternalFrame Donadores;
	JPanel añadi, modi, consultOP, consultAll, elimini;
	JTextField colonia, calle, numExt, numInt, email;
	JFormattedTextField ID;
	JFormattedTextField telefono;
	JComboBox RelacionUni, tipoDonador, clase, progDona;
	JTextField colonia2, calle2, numExt2, numInt2, email2;
	JFormattedTextField ID2;
	JFormattedTextField telefono2;
	JComboBox RelacionUni2, tipoDonador2, clase2, progDona2;
	JTextField colonia3, calle3, numExt3, numInt3, email3;
	JFormattedTextField ID3;
	JFormattedTextField telefono3;
	JComboBox RelacionUni3, tipoDonador3, clase3, progDona3;
	JTextField colonia4, calle4, numExt4, numInt4, email4;
	JFormattedTextField ID4;
	JFormattedTextField telefono4;
	JComboBox RelacionUni4, tipoDonador4, clase4, progDona4;
	List<JComponent> ordenTabulacion1 = new ArrayList<>();
	JPanel opcionesPaneles = new JPanel(new CardLayout());
	JButton añadire, eliminare, modificare, consultares, consultar;
	JToolBar opcionesd;
	JFrame frame = new JFrame();
	JButton Restor1, Restor2, Restor3, Restor4;
	JButton buscarModi, buscarConsultOP, buscarElimi;
	
	String[][] RelacionesUni = {{"Selecciona","0"},
			{"Facultad de Ingenieria ", "1"},
			{"Facultad de Ciencias Sociales ","2"},
			{"Personal Administrativo ","3"},
			{"Egresado Posgrado ", "4"},
			{"Amigo de la Universidad ", "5"}};
	String[][] tiposDonadores = {{"Selecciona","0"},
		    {"Exalumno", "1"},
		    {"Empresa", "2"},
		    {"Fundacion", "3"},
		    {"Empleado", "4"},
		    {"Publico General", "5"}};
	String[][] clases = {{"Selecciona","0"},
		    {"2020 - 2024", "11"},
		    {"2021 - 2025", "12"},
		    {"2022 - 2026", "13"},
		    {"2019 - 2023", "14"},
		    {"2023 - 2027", "15"}};
	String[][] programasDonacion = {{"Selecciona","0"},
		    {"Programa General de Apoyo", "1"},
		    {"Becas para Estudiantes", "2"},
		    {"Fondo de Investigacion", "3"},
		    {"Donaciones en Especie", "4"},
		    {"Apoyo a Infraestructura", "5"}};
	
	public GUI() {
		boolean Secion = false;
		FondoPanel fondo = new FondoPanel("/imagenes/fondo.jpg");
		fondo.setLayout(new BorderLayout());
		
		
		frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Gestor Universidad");
        frame.setSize(1020, 740);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        opcionesd = new JToolBar();
        
        modificar = new JMenuItem("Modificar");
        modificar.addActionListener(this);
        cargaOpcion = new JMenuItem("Consultar");
        cargaOpcion.addActionListener(this);
        cargaAll = new JMenuItem("Cargar todos");
        cargaAll.addActionListener(this);
        eliminar = new JMenuItem("Eliminar");
        eliminar.addActionListener(this);
        añadir = new JMenuItem("añadir nuevo");
        añadir.addActionListener(this);
        donadores = new JMenuItem("donadores");
        donadores.addActionListener(this);
        acciones = new JMenu("abrir");
        acciones.add(donadores);
        Opciones = new JMenu("Opciones");
        Opciones.add(añadir);
        Opciones.add(eliminar);
        Opciones.add(modificar);
        Opciones.add(cargaOpcion);
        Opciones.add(cargaAll);
        menubar.add(Opciones);
        frame.setFocusTraversalPolicyProvider(true);
        
        problema1.setHorizontalAlignment(SwingConstants.RIGHT);
        problema2.setHorizontalAlignment(SwingConstants.RIGHT);
        menuBar.add(acciones);
        
        boton1 = new JButton("Acceder");
        boton1.addActionListener(this);
       
        
        JDesktopPane panel = new JDesktopPane();
        
       InicioSecion = new JInternalFrame();
       
        InicioSecion.setSize(500,500);
        InicioSecion.setLayout(gbl);
        int x = (panel.getHeight() - InicioSecion.getHeight());
        int y = (panel.getWidth() - InicioSecion.getWidth());
        int yy = (y * (-1)) /5;
        int xx = (x * (-1)) / 2;
        InicioSecion.setLocation(xx, yy); // Posición inicial
        InicioSecion.setVisible(true);
        InicioSecion.setTitle("Iniciar sesion");
        
        
        
        JLabel tituloinicioSecion = new JLabel("Bienbenido, inicia secion para comenzar");
        agregarComponente(InicioSecion, tituloinicioSecion, 0, 0, 2, 1);
        
        JLabel txtUsu = new JLabel("Usuario: ");
        agregarComponente(InicioSecion, txtUsu, 0, 1, 1, 1);
        
        
        Usuario = new JTextField(10);
        agregarComponente(InicioSecion, Usuario, 1, 1, 1, 1);
        
        agregarComponente(InicioSecion, problema1, 0, 2, 2, 1);
        
        JLabel txtContra = new JLabel("Contraseña: ");
        agregarComponente(InicioSecion, txtContra, 0, 3, 1, 1);
        
        contraseña = new JPasswordField(10);
        agregarComponente(InicioSecion, contraseña, 1, 3, 1, 1);
        
        agregarComponente(InicioSecion, problema2, 0, 4, 2, 1);
        
        agregarComponente(InicioSecion, boton1, 0, 5, 2, 1);
        
        
        
        panel.add(InicioSecion);
        
        // ventana Donadores
        Donadores = new JInternalFrame();
        Donadores.setResizable(true);  
        Donadores.setSize(500,500);
        Donadores.setLayout(new BorderLayout()); // ¡Cambiado a BorderLayout!
        int x1 = (panel.getHeight() - InicioSecion.getHeight());
        int y1 = (panel.getWidth() - InicioSecion.getWidth());
        int yy1 = (y * (-1)) /5;
        int xx1 = (x * (-1)) / 2;
        Donadores.setLocation(xx1, yy1);
        Donadores.setTitle("Gestion Donadores");
        Donadores.setClosable(true);
        Donadores.setIconifiable(true);
        Donadores.setDefaultCloseOperation(HIDE_ON_CLOSE);
        Donadores.setJMenuBar(menubar);
        
        panel.add(Donadores);
        
        // Preparamos los paneles para CardLayout
        añadi = new JPanel();
        añadi.setLayout(gbl);
        modi = new JPanel();
        modi.setLayout(gbl);
        consultOP = new JPanel();
        consultOP.setLayout(gbl);
        consultAll = new JPanel();
        consultAll.setLayout(gbl);
        elimini = new JPanel();
        elimini.setLayout(gbl);
        
        // Añadir todos los paneles al CardLayout
        opcionesPaneles.add(añadi, "Añadir");
        opcionesPaneles.add(modi, "Modificar");
        opcionesPaneles.add(consultOP, "ConsultarOpcion");
        opcionesPaneles.add(consultAll, "ConsultarTodo");
        opcionesPaneles.add(elimini, "Eliminar");
        
        // Agregar el panel de opciones al frame Donadores
        Donadores.add(opcionesPaneles, BorderLayout.CENTER);
        
        // CardLayout de añadir
        JLabel tituloAñadi = new JLabel("Añadir Donador");
        tituloAñadi.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(añadi, tituloAñadi, 0, 0, 5, 1);
        
        agregarComponente(añadi, new JLabel("ID (opcional)"), 0, 1, 1, 1);
        try {
            MaskFormatter formatter = new MaskFormatter("##########");
            formatter.setPlaceholder("");
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true); 
            
            ID = new JFormattedTextField(formatter);
            ID.setColumns(10);
            
        } catch (ParseException e) {
            ID = new JFormattedTextField();
            JOptionPane.showMessageDialog(this, 
                "Error en el formato del id", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        agregarComponente(añadi, ID, 1, 1, 1, 1);
        
        agregarComponente(añadi, new JLabel("Datos direccion"), 0, 2, 2, 1);
        
        agregarComponente(añadi, new JLabel("Colonia: "), 0, 3, 1, 1);
        colonia = new JTextField(10);
        agregarComponente(añadi, colonia, 1, 3, 1, 1);
        
        agregarComponente(añadi, new JLabel("Calle: "), 0, 4, 1, 1);
        calle = new JTextField(10);
        agregarComponente(añadi, calle, 1, 4, 1, 1);
        
        agregarComponente(añadi, new JLabel("Numero exterior: "), 0, 5, 1, 1);
        numExt = new JTextField(10);
        agregarComponente(añadi, numExt, 1, 5, 1, 1);
        
        agregarComponente(añadi, new JLabel("Numero interior ('S/N' si no aplica):"), 0, 6, 1, 1);
        numInt = new JTextField(10);
        agregarComponente(añadi, numInt, 1, 6, 1, 1);
        
        agregarComponente(añadi, new JLabel("Teléfono de contacto:"), 0, 7, 1, 1);

        try {
            MaskFormatter formatter = new MaskFormatter("+## ##########");
            formatter.setPlaceholderCharacter('_');
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true); 
            
            telefono = new JFormattedTextField(formatter);
            telefono.setColumns(15);
            
        } catch (ParseException e) {
            telefono = new JFormattedTextField();
            JOptionPane.showMessageDialog(this, 
                "Error en formato de teléfono", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }

        ordenTabulacion1.add(telefono);
        agregarComponente(añadi, telefono, 1, 7, 1, 1);
        
        agregarComponente(añadi, new JLabel("Email:"), 0, 8, 1, 1);
        email = new JTextField(25);
        agregarComponente(añadi, email, 1, 8, 1, 1);

        email.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String email = ((JTextField) input).getText();
                return email.isEmpty() || email.equalsIgnoreCase("S/N") || isValidEmail(email);
            }
            
            @Override
            public boolean shouldYieldFocus(JComponent input) { // metodo que hace la validacion y permite el cambiar de foco o no
                String text = ((JTextField) input).getText();
                
                if (!text.isEmpty() && !isValidEmail(text)) {
                     if (text.equalsIgnoreCase("S/N")) {
                         input.setBackground(new Color(220, 255, 220));
                     } else if (!isValidEmail(text)) {
                         input.setBackground(new Color(255, 200, 200));
                     } else {
                         input.setBackground(Color.WHITE);
                     }
                } else {
                    input.setBackground(Color.WHITE); 
                }
                
                return true;
            }
        });
        
        agregarComponente(añadi, new JLabel("Relacion con la universidad (opcional): "), 0, 9, 1, 1);
        RelacionUni = new JComboBox<String>();
        for (String[] n : RelacionesUni) {
            RelacionUni.addItem(n[0]);
        }
        agregarComponente(añadi, RelacionUni, 1, 9, 1, 1);
        
        agregarComponente(añadi, new JLabel("Tipo de donador"), 0, 10, 1, 1);
        tipoDonador = new JComboBox<String>();
        for (String[] n : tiposDonadores) {
            tipoDonador.addItem(n[0]);
        }
        agregarComponente(añadi, tipoDonador, 1, 10, 1, 1);
        
        agregarComponente(añadi, new JLabel("Clase (opcional): "), 0, 11, 1, 1);
        clase = new JComboBox<String>();
        for (String[] n : clases) {
            clase.addItem(n[0]);
        }
        agregarComponente(añadi, clase, 1, 11, 1, 1);
        
        agregarComponente(añadi, new JLabel("Programa de donacion (opcional): "), 0, 12, 1, 1);
        progDona = new JComboBox<String>();
        for (String[] n : programasDonacion) {
            progDona.addItem(n[0]);
        }
        agregarComponente(añadi, progDona, 1, 12, 1, 1);
        
        añadire = new JButton("Añadir donador");
        añadire.addActionListener(this);
        agregarComponente(añadi, añadire, 0, 13, 3, 1);
        
        Restor1 = new JButton("Restablecer campos");
        Restor1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                restablecer(ID, colonia, calle, numExt, numInt, telefono, email, tipoDonador, RelacionUni, clase, progDona);
                
            }
        });
        
        agregarComponente(añadi, Restor1, 0, 14, 3, 1);
        
        
        
        // cardLayaout de modificar donador
        
        
        JLabel tituloModi = new JLabel("Modificar Donador");
        tituloModi.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(modi, tituloModi, 0, 0, 5, 1);
        
        agregarComponente(modi, new JLabel("ID"), 0, 1, 1, 1);
        try {
            MaskFormatter formatter = new MaskFormatter("##########");
            formatter.setPlaceholder("");
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true); 
            
            ID2 = new JFormattedTextField(formatter);
            ID2.setColumns(10);
            
        } catch (ParseException e) {
            ID2 = new JFormattedTextField();
            JOptionPane.showMessageDialog(this, 
                "Error en formato del id", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        agregarComponente(modi, ID2, 1, 1, 1, 1);
        
        buscarModi = new JButton("Buscar");
        buscarModi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = ID2.getText().replaceAll("[^0-9]", "");
				if (!ID2.getText().isEmpty()) {
					if (true) {															//condicion para comprovar si el id pertenece a un donador
						activarComponentes(colonia2, calle2, numExt2, numInt2, telefono2, email2, tipoDonador2, RelacionUni2, clase2, progDona2);
					}
				}
				
			}
		});
        GridBagConstraints gbcBuscar = new GridBagConstraints();
        gbcBuscar.gridx = 2;
        gbcBuscar.gridy = 1;
        gbcBuscar.gridwidth = 1;
        gbcBuscar.gridheight = 1;
        gbcBuscar.fill = GridBagConstraints.NONE; // No se expande
        gbcBuscar.weightx = 0; // No toma espacio extra
        gbcBuscar.insets = new Insets(5, 5, 5, 5);

        modi.add(buscarModi, gbcBuscar);
        
        
        agregarComponente(modi, new JLabel("Datos direccion"), 0, 2, 2, 1);
        
        agregarComponente(modi, new JLabel("Colonia: "), 0, 3, 1, 1);
        colonia2 = new JTextField(10);
        colonia2.setEnabled(false);
        agregarComponente(modi, colonia2, 1, 3, 2, 1);
        
        agregarComponente(modi, new JLabel("Calle: "), 0, 4, 1, 1);
        calle2 = new JTextField(10);
        calle2.setEnabled(false);
        agregarComponente(modi, calle2, 1, 4, 2, 1);
        
        agregarComponente(modi, new JLabel("Numero exterior: "), 0, 5, 1, 1);
        numExt2 = new JTextField(10);
        numExt2.setEnabled(false);
        agregarComponente(modi, numExt2, 1, 5, 2, 1);
        
        agregarComponente(modi, new JLabel("Numero interior ('S/N' si no aplica):"), 0, 6, 1, 1);
        numInt2 = new JTextField(10);
        numInt2.setEnabled(false);
        agregarComponente(modi, numInt2, 1, 6, 2, 1);
        
        agregarComponente(modi, new JLabel("Teléfono de contacto:"), 0, 7, 1, 1);

        try {
            MaskFormatter formatter = new MaskFormatter("+## ##########");
            formatter.setPlaceholderCharacter('_');
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true); 
            
            telefono2 = new JFormattedTextField(formatter);
            telefono2.setEnabled(false);
            telefono2.setColumns(15);
            
        } catch (ParseException e) {
            telefono2 = new JFormattedTextField();
            JOptionPane.showMessageDialog(this, 
                "Error en formato de teléfono", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }

        ordenTabulacion1.add(telefono2);
        agregarComponente(modi, telefono2, 1, 7, 2, 1);
        
        agregarComponente(modi, new JLabel("Email:"), 0, 8, 1, 1);
        email2 = new JTextField(25);
        email2.setEnabled(false);
        agregarComponente(modi, email2, 1, 8, 2, 1);

        email2.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String email = ((JTextField) input).getText();
                return email.isEmpty() || email.equalsIgnoreCase("S/N") || isValidEmail(email);
            }
            
            @Override
            public boolean shouldYieldFocus(JComponent input) { // metodo que hace la validacion y permite el cambiar de foco o no
                String text = ((JTextField) input).getText();
                
                if (!text.isEmpty() && !isValidEmail(text)) {
                     if (text.equalsIgnoreCase("S/N")) {
                         input.setBackground(new Color(220, 255, 220));
                     } else if (!isValidEmail(text)) {
                         input.setBackground(new Color(255, 200, 200));
                     } else {
                         input.setBackground(Color.WHITE);
                     }
                } else {
                    input.setBackground(Color.WHITE); 
                }
                
                return true;
            }
        });
        
        agregarComponente(modi, new JLabel("Relacion con la universidad (opcional): "), 0, 9, 1, 1);
        RelacionUni2 = new JComboBox<String>();
        RelacionUni2.setEnabled(false);
        for (String[] n : RelacionesUni) {
            RelacionUni2.addItem(n[0]);
        }
        agregarComponente(modi, RelacionUni2, 1, 9, 2, 1);
        
        agregarComponente(modi, new JLabel("Tipo de donador"), 0, 10, 1, 1);
        tipoDonador2 = new JComboBox<String>();
        tipoDonador2.setEnabled(false);
        for (String[] n : tiposDonadores) {
            tipoDonador2.addItem(n[0]);
        }
        agregarComponente(modi, tipoDonador2, 1, 10, 2, 1);
        
        agregarComponente(modi, new JLabel("Clase (opcional): "), 0, 11, 1, 1);
        clase2 = new JComboBox<String>();
        clase2.setEnabled(false);
        for (String[] n : clases) {
            clase2.addItem(n[0]);
        }
        agregarComponente(modi, clase2, 1, 11, 2, 1);
        
        agregarComponente(modi, new JLabel("Programa de donacion (opcional): "), 0, 12, 1, 1);
        progDona2 = new JComboBox<String>();
        progDona2.setEnabled(false);
        for (String[] n : programasDonacion) {
            progDona2.addItem(n[0]);
        }
        agregarComponente(modi, progDona2, 1, 12, 2, 1);
        
        modificare = new JButton("Modificar donador");
        modificare.addActionListener(this);
        agregarComponente(modi, modificare, 0, 13, 3, 1);
        
        Restor2 = new JButton("Restablecer campos");
        Restor2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                restablecer(ID2, colonia2, calle2, numExt2, numInt2, telefono2, email2, tipoDonador2, RelacionUni2, clase2, progDona2);
                
            }
        });
        
        
        JLabel tituloConsultOP = new JLabel("Consultar un Donador");
        tituloConsultOP.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(consultOP, tituloConsultOP, 0, 0, 5, 1);
        
        JLabel tituloConsultAll = new JLabel("Consultar todos los Donadores");
        tituloConsultAll.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(consultAll, tituloConsultAll, 0, 0, 5, 1);
        
        JLabel tituloElimini = new JLabel("Eliminar Donador");
        tituloElimini.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(elimini, tituloElimini, 0, 0, 5, 1);
        
        frame.add(panel);
        
	}	
		//Metodo para llenar tablas en base al resultSet con los datos de la base de datos
	private void llenarTablaDesdeResultSet(ResultSet rs, JTable tabla) {
	    try {
	        ResultSetMetaData metaData = rs.getMetaData();
	        int columnas = metaData.getColumnCount();

	        // Crear modelo de la tabla
	        DefaultTableModel modelo = new DefaultTableModel();

	        // Añadir nombres de columnas al modelo
	        for (int i = 1; i <= columnas; i++) {
	            modelo.addColumn(metaData.getColumnLabel(i));
	        }

	        // Añadir filas al modelo
	        while (rs.next()) {
	            Object[] fila = new Object[columnas];
	            for (int i = 1; i <= columnas; i++) {
	                fila[i - 1] = rs.getObject(i);
	            }
	            modelo.addRow(fila);
	        }

	        tabla.setModel(modelo);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	// Retorna el id != 0 para los campos de combobox
	public Integer retornarID (String[][] lista, String valor) {
		for (String[] m : lista) {
			if (m[0].equalsIgnoreCase(valor)) {
				return Integer.parseInt(m[1]);
			}
		}
		
		return null;
	}
	
	private void activarComponentes(JTextField col, JTextField call, JTextField numext, JTextField numint, 
			JFormattedTextField telefon, JTextField email5, JComboBox<String> tipoDona, JComboBox<String> RelacionUni, 
			JComboBox<String> clase, JComboBox<String> progDona) {
		col.setEnabled(true);
		call.setEnabled(true);
		numext.setEnabled(true);
		numint.setEnabled(true);
		telefon.setEnabled(true);
		email5.setEnabled(true);
		tipoDona.setEnabled(true);
		RelacionUni.setEnabled(true);
		clase.setEnabled(true);
		progDona.setEnabled(true);
		
	}
	
	private void restablecer(JFormattedTextField IDe, JTextField col, JTextField call, JTextField numext, JTextField numint, 
			JFormattedTextField telefon, JTextField email5, JComboBox<String> tipoDona, JComboBox<String> RelacionUni, 
			JComboBox<String> clase, JComboBox<String> progDona) {
		IDe.setText("");
		col.setText("");
		call.setText("");
		numext.setText("");
		numint.setText("");
		telefon.setText("");
		email5.setText("");
		tipoDona.setSelectedIndex(0);
		RelacionUni.setSelectedIndex(0);
		clase.setSelectedIndex(0);
		progDona.setSelectedIndex(0);
	}
	
	
	//verifica que solo los datos que no aceptan nulo extan correctamente completos
	
	public boolean verificadorDatos(JTextField col, JTextField call, JTextField numext, JTextField numint, JFormattedTextField telefon, JTextField email5, JComboBox<String> tipoDona) {
		int hayError = 0;
		errores.clear();
		 if (col.getText().isEmpty() || col.getText().length() >45) {
			 col.setBackground(new Color(255, 200, 200));
			 errores.add("campo colonia obligatorio, maximo 45 caracteres");
			 hayError ++;
		 }
		 if(call.getText().isEmpty() || call.getText().length() > 45) {
			 call.setBackground(new Color(255, 200, 200));
			 errores.add("campo calle obligatorio, maximo 45 caracteres");
			 hayError ++;
		 }
		 if (numext.getText().isEmpty() || numext.getText().length() >45) {
			 numext.setBackground(new Color(255, 200, 200));
			 errores.add("campo numero exterior obligatorio, maximo 45 caracteres");
			 hayError ++;
		 }
		 if (numint.getText().isEmpty() || numint.getText().length() > 45) {
			 numint.setBackground(new Color(255, 200, 200));
			 errores.add("campo numero interior obligatorio, maximo 45 caracteres o 'S/N' si no aplica");
			 hayError ++;
		 }
		 if (telefon.getText().equalsIgnoreCase("+__ __________")) {
			 telefon.setBackground(new Color(255, 200, 200));
			 errores.add("campo telefono obligatorio, numero de telefono con codigo de pais");
			 hayError ++;
		 }
		 if (email5.getText().isEmpty() || email5.getText().length() > 70) {
			 email5.setBackground(new Color(255, 200, 200));
			 errores.add("campo Email obligatorio, maximo 70 caracteres");
			 hayError ++;
		 }
		 if (tipoDona.getSelectedIndex() == 0) {
			 tipoDona.setBackground(new Color(255, 200, 200));
			 errores.add("opcion tipo de donador obligatorio, elige una opcion diferente");
			 hayError ++;
		 }
		 
		 if(hayError > 0) {
			 return false;
		 }
		 
		
		return true;
	}
	
	private boolean isValidEmail(String email) {
	    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    return email.matches(regex) && email.length() <= 70; // 70 es el varchar de tu tabla
	}
	private void agregarComponente(JPanel InterFrame, JComponent componente, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		InterFrame.add(componente, gbc);
	}
	
	class FondoPanel extends JPanel {
	    private Image imagen;

	    public FondoPanel(String pathImagen) {
	        this.imagen = new ImageIcon(pathImagen).getImage();
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (imagen != null) {
	            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
	        }
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == añadir) {
			Donadores.setVisible(true);
			CardLayout cardLayout = (CardLayout) opcionesPaneles.getLayout();
		    cardLayout.show(opcionesPaneles, "Añadir");
		}
		else if (e.getSource() == modificar) {
			Donadores.setVisible(true);
			CardLayout cardLayout = (CardLayout) opcionesPaneles.getLayout();
		    cardLayout.show(opcionesPaneles, "Modificar");
		}
		else if (e.getSource() == eliminar) {
			Donadores.setVisible(true);
			CardLayout cardLayout = (CardLayout) opcionesPaneles.getLayout();
		    cardLayout.show(opcionesPaneles, "Eliminar");
		}
		else if (e.getSource() == cargaOpcion) {
			Donadores.setVisible(true);
			CardLayout cardLayout = (CardLayout) opcionesPaneles.getLayout();
		    cardLayout.show(opcionesPaneles, "ConsultarOpcion");
		}
		else if (e.getSource() == cargaAll) {
			Donadores.setVisible(true);
			CardLayout cardLayout = (CardLayout) opcionesPaneles.getLayout();
		    cardLayout.show(opcionesPaneles, "ConsultarTodo");
		}
		
		if (e.getSource() == donadores) {
			Donadores.setVisible(true);
			CardLayout cardLayout = (CardLayout) opcionesPaneles.getLayout();
		    cardLayout.show(opcionesPaneles, "Añadir");
		}else if (e.getSource() == boton1) {
			problema1.setText("");
			problema2.setText("");
			String usuarios = Usuario.getText();
			String contrs = new String(contraseña.getPassword());

			boolean usuarioValido = false;
			boolean passwordValido = false;

			// Validación de usuario
			if (usuarios.isEmpty()) {
			    problema1.setText("Ingresa el usuario");
			} else if (!comprovarUsuario(usuarios)) {
			    problema1.setText("Usuario incorrecto");
			} else {
			    usuarioValido = true;
			}

			// Validación de contraseña
			if (contrs.isEmpty()) {
			    problema2.setText("Ingresa la contraseña");
			} else if (!comprovarPassword(contrs)) {
			    problema2.setText("Contraseña incorrecta");
			} else {
			    passwordValido = true;
			}

			if (usuarioValido && passwordValido) {
				JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuarios + "!","Login Exitoso", // Título del diálogo
				        JOptionPane.INFORMATION_MESSAGE );
				try {
					InicioSecion.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				añadirOpciones();
			}

		}else if (e.getSource() == añadire) {
			if (!verificadorDatos(colonia, calle, numExt, numInt, telefono, email, tipoDonador)) {
				String m = "";
				for (String n : errores) {
					m = m + n + "\n";
				}
				System.out.println("id =="+ID.getText()+ "id");
				JOptionPane.showMessageDialog(this, 
				        m, 
				        "Error", 
				        JOptionPane.ERROR_MESSAGE);
			}else {
				
				// Código para guardar en la base de datos
				JOptionPane.showMessageDialog(this, 
				        "Datos correctos, el donador se guardará en la base de datos", 
				        "Éxito", 
				        JOptionPane.INFORMATION_MESSAGE);
				
			}
			
		}
		
		
	}
	private void añadirOpciones () {
		frame.setJMenuBar(menuBar);
	}
	
	private void agregarComponente(JInternalFrame InterFrame, JComponent componente, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		InterFrame.add(componente, gbc);
	}
	
	private boolean comprovarUsuario(String usuario){
		
		if (usuario.equals("admin")) {
			return true;
		}
		return false;
	}
	private boolean comprovarPassword(String pasword) {
		if(pasword.equals("2008admin")) {
			return true;
		}
		return false;
	}
	
}



public class VentanaInicio {
	
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();

            }
        });
	}

}