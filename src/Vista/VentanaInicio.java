package Vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.Toolkit;
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
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ConexionBD.ConexionBD;
import Controlador.DonadorDAO;
import Hilos.HilosConsultaActualizarGUI;
import Modelo.Donador;



public class VentanaInicio  extends JFrame implements ActionListener{
	ArrayList<String> errores = new ArrayList<String>();
	Donador donadona;
	public static VentanaInicio interfaz;
	
	JDialog cargando;
	
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
	JTextField colonia, calle, numExt, numInt, email, ID;
	JFormattedTextField telefono;
	JComboBox RelacionUni, tipoDonador, clase, progDona;
	JTextField colonia2, calle2, numExt2, numInt2, email2, ID2;
	JFormattedTextField telefono2;
	JComboBox RelacionUni2, tipoDonador2, clase2, progDona2;
	JTextField colonia3, calle3, numExt3, numInt3, email3, ID3;
	JFormattedTextField telefono3;
	JComboBox RelacionUni3, tipoDonador3, clase3, progDona3;
	JTextField colonia4, calle4, numExt4, numInt4, email4,ID4;
	JFormattedTextField telefono4;
	JComboBox RelacionUni4, tipoDonador4, clase4, progDona4;
	JTable tablaDonadores = new JTable();
	JScrollPane ScrollPanelDonadores = new JScrollPane(tablaDonadores);
	ResultSet listaDonadores;
	
	List<JComponent> ordenTabulacion1 = new ArrayList<>();
	JPanel opcionesPaneles = new JPanel(new CardLayout());
	JButton añadire, eliminare, modificare, consultares, consultar;
	JToolBar opcionesd;
	public static JFrame frame = new JFrame();
	JButton Restor1, Restor2, Restor3, Restor4;
	JButton buscarModi, buscarConsultOP, buscarElimi, btnActualizarTabla;
	
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
	
	public void setListadonadores(ResultSet donadores) {
		listaDonadores = donadores;
	}
	
	
	public VentanaInicio() {
		boolean Secion = false;
		
		
		frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Gestor Universidad");
        frame.setSize(1020, 740);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        JLabel fondoLabel = new JLabel();
        
        // Buscar la imagen en diferentes ubicaciones
        ImageIcon imagenFondo = new ImageIcon("imagenes/fondo.jpg");
        if (imagenFondo != null) {
            // Escalar la imagen al tamaño de la ventana
            Image img = imagenFondo.getImage();
            Image imgEscalada = img.getScaledInstance(1020, 740, Image.SCALE_SMOOTH);
            imagenFondo = new ImageIcon(imgEscalada);
            fondoLabel.setIcon(imagenFondo);
        } else {
            // Si no se encuentra la imagen, usar un color de fondo
            fondoLabel.setOpaque(true);
            fondoLabel.setBackground(new Color(200, 220, 240));
            System.out.println("✗ No se encontró imagen, usando color de fondo");
        }
        fondoLabel.setLayout(new BorderLayout());
        frame.setContentPane(fondoLabel); // Establecer como contenido principal
        
        opcionesd = new JToolBar();
        
        modificar = new JMenuItem("Modificar");
        modificar.addActionListener(this);
        //cargaOpcion = new JMenuItem("Consultar");
        //cargaOpcion.addActionListener(this);
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
        Opciones.add(cargaAll);
        menubar.add(Opciones);
        frame.setFocusTraversalPolicyProvider(true);
        
        problema1.setHorizontalAlignment(SwingConstants.RIGHT);
        problema1.setOpaque(true);
        problema2.setHorizontalAlignment(SwingConstants.RIGHT);
        problema2.setOpaque(true);
        menuBar.add(acciones);
        
        boton1 = new JButton("Acceder");
        boton1.addActionListener(this);
       
        
        JDesktopPane panel = new JDesktopPane();
        panel.setOpaque(false);
       InicioSecion = new JInternalFrame();


       JLabel fondoLabel2 = new JLabel();
       
       // Buscar la imagen en diferentes ubicaciones
       ImageIcon imagenFondo2 = new ImageIcon("imagenes/Fondo Login.jpg");
       if (imagenFondo2 != null) {
           // Escalar la imagen al tamaño de la ventana
           Image img = imagenFondo2.getImage();
           Image imgEscalada = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
           imagenFondo2 = new ImageIcon(imgEscalada);
           fondoLabel2.setIcon(imagenFondo2);
       } else {
           // Si no se encuentra la imagen, usar un color de fondo
           fondoLabel2.setOpaque(true);
           fondoLabel2.setBackground(new Color(200, 220, 240));
           System.out.println("✗ No se encontró imagen, usando color de fondo");
       }
       fondoLabel2.setLayout(new BorderLayout());
       InicioSecion.setContentPane(fondoLabel2); // Establecer como contenido principal
       
       
       
       
        InicioSecion.setSize(500,500);
        InicioSecion.setLayout(gbl);
        int x = (panel.getHeight() - InicioSecion.getHeight());
        int y = (panel.getWidth() - InicioSecion.getWidth());
        int yy = (y * (-1)) /5;
        int xx = (x * (-1)) / 2;
        InicioSecion.setLocation(xx, yy); // Posición inicial
        InicioSecion.setVisible(true);
        InicioSecion.setTitle("Iniciar sesion");
        
        
       // <html><u><b style='color:blue;'>Bienbenido, inicia secion para comenzar</b></u></html>
        JLabel tituloinicioSecion = new JLabel("Bienbenido, inicia secion para comenzar");
        tituloinicioSecion.setOpaque(true);
        agregarComponente(InicioSecion, tituloinicioSecion, 0, 0, 2, 1);
        
        JLabel txtUsu = new JLabel("Usuario: ");
        txtUsu.setOpaque(true);
        agregarComponente(InicioSecion, txtUsu, 0, 1, 1, 1);
        
        
        Usuario = new JTextField(10);
        agregarComponente(InicioSecion, Usuario, 1, 1, 1, 1);
        
        agregarComponente(InicioSecion, problema1, 0, 2, 2, 1);
        
        JLabel txtContra = new JLabel("Contraseña: ");
        txtContra.setOpaque(true);
        agregarComponente(InicioSecion, txtContra, 0, 3, 1, 1);
        
        contraseña = new JPasswordField(10);
        agregarComponente(InicioSecion, contraseña, 1, 3, 1, 1);
        
        agregarComponente(InicioSecion, problema2, 0, 4, 2, 1);
        
        agregarComponente(InicioSecion, boton1, 0, 5, 2, 1);
        
        
        panel.add(InicioSecion);
        
        
        // ventana Donadores
        Donadores = new JInternalFrame();
        Donadores.setResizable(true);  
        Donadores.setSize(810,700);
        Donadores.setLayout(new BorderLayout());
        int x1 = (panel.getHeight() - Donadores.getHeight());
        int y1 = (panel.getWidth() - Donadores.getWidth());
        int yy1 = (y * (-1)) /5;
        int xx1 = (x * (-1)) / 2;
        Donadores.setLocation(xx1, yy1);
        Donadores.setTitle("Gestion Donadores");
        Donadores.setClosable(true);
        Donadores.setIconifiable(true);
        Donadores.setDefaultCloseOperation(HIDE_ON_CLOSE);
        Donadores.setJMenuBar(menubar);
        Donadores.setOpaque(false);
        JLabel fondoLabel3 = new JLabel();
        
        
        
        panel.add(Donadores);
        
        // Preparamos los paneles para CardLayout
        añadi = new JPanel();
        añadi.setOpaque(false);
        añadi.setLayout(gbl);
        modi = new JPanel();
        modi.setOpaque(false);
        modi.setLayout(gbl);
        consultAll = new JPanel();
        consultAll.setOpaque(false);
        consultAll.setLayout(gbl);
        elimini = new JPanel();
        elimini.setOpaque(false);
        elimini.setLayout(gbl);
        
        // Añadir todos los paneles al CardLayout
        opcionesPaneles.add(añadi, "Añadir");
        opcionesPaneles.add(modi, "Modificar");
        //opcionesPaneles.add(consultOP, "ConsultarOpcion");
        opcionesPaneles.add(consultAll, "ConsultarTodo");
        opcionesPaneles.add(elimini, "Eliminar");
        opcionesPaneles.setOpaque(false);
        
        // Agregar el panel de opciones al frame Donadores
        Donadores.add(opcionesPaneles, BorderLayout.CENTER);
        
        // CardLayout de añadir
        
        añadi.setBackground(new Color(173, 216, 230));
        JLabel tituloAñadi = new JLabel("Añadir Donador");
        tituloAñadi.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(añadi, tituloAñadi, 0, 0, 5, 1);
        
        agregarComponente(añadi, new JLabel("ID (opcional)"), 0, 1, 1, 1);
        ID = crearCampoIDValidado();
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
        
        JPanel paneles = new JPanel();
        paneles.setLayout(gbl);
        agregarComponente(añadi, paneles, 0, 13, 5, 1);
        

        GridBagConstraints gbcAñadi = new GridBagConstraints();
        gbcAñadi.gridx = 0;
        gbcAñadi.gridy = 0;
        gbcAñadi.gridwidth = 1;
        gbcAñadi.gridheight = 1;
        gbcAñadi.fill = GridBagConstraints.NONE; // No se expande
        gbcAñadi.weightx = 0; // No toma espacio extra
        gbcAñadi.insets = new Insets(5, 5, 5, 5);
        ImageIcon icono = new ImageIcon("imagenes/icon save.jpg");
        Image scaledImage = icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Tamaño deseado
        ImageIcon setIcono = new ImageIcon(scaledImage);
        añadire = new JButton(setIcono);
        añadire.setPreferredSize(new Dimension(40, 40));
        añadire.setMaximumSize(new Dimension(40, 40));
        añadire.setMinimumSize(new Dimension(40, 40));
        añadire.setSize(new Dimension(40, 40));
        añadire.addActionListener(this);
        
        paneles.add(añadire, gbcAñadi);
        
        
        
       
        GridBagConstraints gbcDelet = new GridBagConstraints();
        gbcDelet.gridx = 1;
        gbcDelet.gridy = 0;
        gbcDelet.gridwidth = 1;
        gbcDelet.gridheight = 1;
        gbcDelet.fill = GridBagConstraints.NONE; // No se expande
        gbcDelet.weightx = 0; // No toma espacio extra
        gbcDelet.insets = new Insets(5, 5, 5, 5);
        ImageIcon icono1 = new ImageIcon("imagenes/icono restablecer.jpg");
        Image scaledImage1 = icono1.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Tamaño deseado
        ImageIcon setIcono1 = new ImageIcon(scaledImage1);
        Restor1 = new JButton(setIcono1);
        Restor1.setPreferredSize(new Dimension(40, 40));
        Restor1.setMaximumSize(new Dimension(40, 40));
        Restor1.setMinimumSize(new Dimension(40, 40));
        Restor1.setSize(new Dimension(40, 40));
        Restor1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                restablecer(ID, colonia, calle, numExt, numInt, telefono, email, tipoDonador, RelacionUni, clase, progDona);
                
            }
        });
        GridBagConstraints gbcRB = new GridBagConstraints();
        gbcRB.gridx = 1;
        gbcRB.gridy = 0;
        gbcRB.gridwidth = 1;
        gbcRB.gridheight = 1;
        gbcRB.fill = GridBagConstraints.NONE; // No se expande
        gbcRB.weightx = 0; // No toma espacio extra
        gbcRB.insets = new Insets(5, 5, 5, 5);
        paneles.add(Restor1, gbcRB);
        
        
        
        
        // cardLayaout de modificar donador
        
        
        JLabel tituloModi = new JLabel("Modificar Donador");
        tituloModi.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(modi, tituloModi, 0, 0, 5, 1);
        
        agregarComponente(modi, new JLabel("ID"), 0, 1, 1, 1);
        ID2 = crearCampoIDValidado();
        agregarComponente(modi, ID2, 1, 1, 1, 1);
        
        ImageIcon icono2 = new ImageIcon("imagenes/icon buscar.png");
        Image scaledImage2 = icono2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Tamaño deseado
        ImageIcon setIcono2 = new ImageIcon(scaledImage2);
        buscarModi = new JButton(setIcono2);
        buscarModi.setPreferredSize(new Dimension(20, 20));
        buscarModi.setMaximumSize(new Dimension(20, 20));
        buscarModi.setMinimumSize(new Dimension(20, 20));
        buscarModi.setSize(new Dimension(20, 20));
        buscarModi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = ID2.getText().trim();
                System.out.println("Texto ingresado: '" + texto + "'");
                
                if (!texto.isEmpty()) {
                    try {
                        int id = Integer.parseInt(texto);
                        System.out.println("ID: " + id);
                        
                        HilosConsultaActualizarGUI hilos = new HilosConsultaActualizarGUI(id, "modifique", interfaz);
                        hilos.consultarYActualizarGUI();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(VentanaInicio.this, 
                            "ID inválido. Ingrese solo números.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                	JOptionPane.showMessageDialog(VentanaInicio.this, 
                            "Campo de ID vacio.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
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
        
        JPanel botones = new JPanel();
        botones.setLayout(gbl);
        
        ImageIcon icono3 = new ImageIcon("imagenes/icon save.jpg");
        Image scaledImage3 = icono3.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Tamaño deseado
        ImageIcon setIcono3 = new ImageIcon(scaledImage3);
        modificare = new JButton(setIcono3);
        modificare.setPreferredSize(new Dimension(40, 40));
        modificare.setMaximumSize(new Dimension(40, 40));
        modificare.setMinimumSize(new Dimension(40, 40));
        modificare.setSize(new Dimension(40, 40));
        modificare.addActionListener(this);
        agregarBotonIcon(botones, modificare, 0, 0, 1, 1);
        
        ImageIcon icono4 = new ImageIcon("imagenes/icono restablecer.jpg");
        Image scaledImage4 = icono4.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Tamaño deseado
        ImageIcon setIcono4 = new ImageIcon(scaledImage4);
        Restor2 = new JButton(setIcono4);
        Restor2.setPreferredSize(new Dimension(40, 40));
        Restor2.setMaximumSize(new Dimension(40, 40));
        Restor2.setMinimumSize(new Dimension(40, 40));
        Restor2.setSize(new Dimension(40, 40));
        Restor2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                restablecer(ID2, colonia2, calle2, numExt2, numInt2, telefono2, email2, tipoDonador2, RelacionUni2, clase2, progDona2);
                
            }
        });
        
        agregarBotonIcon(botones, Restor2, 1, 0, 1, 1);
        agregarComponente(modi, botones, 0, 13, 5, 1);
        
        
        // CardLayaout de consultar donadores
        /*
        JLabel tituloConsultOP = new JLabel("Consultar Donador");
        tituloConsultOP.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(consultOP, tituloConsultOP, 0, 0, 5, 1);
        */
        
        
        // CardLayaout de consultar todos los Donadores
        
        JLabel tituloConsultAll = new JLabel("Consultar todos los Donadores");
        tituloConsultAll.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(consultAll, tituloConsultAll, 0, 0, 5, 1);
        

        agregarComponente(consultAll, ScrollPanelDonadores, 0, 2, 5, 1);
        
        
        
        ImageIcon icono5 = new ImageIcon("imagenes/icono restablecer.jpg");
        Image scaledImage5 = icono5.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Tamaño deseado
        ImageIcon setIcono5 = new ImageIcon(scaledImage5);
        btnActualizarTabla = new JButton(setIcono5);
        btnActualizarTabla.setPreferredSize(new Dimension(40, 40));
        btnActualizarTabla.setMaximumSize(new Dimension(40, 40));
        btnActualizarTabla.setMinimumSize(new Dimension(40, 40));
        btnActualizarTabla.setSize(new Dimension(40, 40)); 
        btnActualizarTabla.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HilosConsultaActualizarGUI hilosGUI = new HilosConsultaActualizarGUI(null, null, interfaz);
				hilosGUI.consultarYActualizarGUI();
				
			}
		});
        
        agregarBotonIcon(consultAll, btnActualizarTabla, 0, 1, 5, 1);
        
        
        
        JLabel tituloElimini = new JLabel("Eliminar Donador");
        tituloElimini.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponente(elimini, tituloElimini, 0, 0, 5, 1);
        
        frame.add(panel);
        
	}
	
	public void setResultSet(ResultSet rsd) {
		if (rsd != null) {
			try {
				tablaDonadores.setModel(modeloTabla(rsd));
				tablaDonadores.setPreferredScrollableViewportSize(new Dimension(600, 300));
			} catch (SQLException e) {
				ShowMessage("Ocurrio un problema al adquirir los datos\n"+
			e.getMessage());
			}
		}else {
			ShowMessage("No se pudieron obtener los datos");
		}
		
	}
	
	public DefaultTableModel modeloTabla(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int cantColumns = metaData.getColumnCount();
		
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> datos = new Vector<Vector<Object>>();
	    
	    ArrayList<Integer> cambios = new ArrayList<Integer>();
	    int clase =0, relacionUni=0, TipoDona=0, progDonacion=0;
	    
	    for (int i = 1; i<= cantColumns; i++) {
	    	String nom = metaData.getColumnName(i);
	    	if (nom.equalsIgnoreCase("RelacionUnilD")) {
	    		columnNames.add("RelacionUniversidad");
	    		cambios.add(i);
	    		relacionUni = i;
	    	}else if (nom.equalsIgnoreCase("TipoDonadorID")) {
	    		columnNames.add("Tipo de donador");
	    		cambios.add(i);
	    		TipoDona = i;
	    	}else if (nom.equalsIgnoreCase("claseID")) {
	    		columnNames.add("Clase");
	    		cambios.add(i);
	    		clase = i;
	    	}else if (nom.equalsIgnoreCase("ProgramaDonacionID")) {
	    		columnNames.add("Programa de donacion");
	    		cambios.add(i);
	    		progDonacion = i;
	    	}else {
	    		columnNames.add(nom);
	    	}
	    }
	    
	    while(rs.next()) {
	    	Vector<Object> fila = new Vector<Object>();
	    	for(int i = 1; i<=cantColumns; i++) {
	    		Object valor = rs.getObject(i);
	    		
	    		if (cambios.contains(i)) {
	                String cambio = "Selecciona";
	                if (valor != null) {
	                    try {
	                        int val = Integer.parseInt(valor.toString());
	                        if (i == clase) {
	                            cambio = retornaValor(clases, val);
	                        } else if (i == relacionUni) {
	                            cambio = retornaValor(RelacionesUni, val);
	                        } else if (i == TipoDona) {
	                            cambio = retornaValor(tiposDonadores, val);
	                        } else if (i == progDonacion) {
	                            cambio = retornaValor(programasDonacion, val);
	                        }
	                    } catch (NumberFormatException e) {
	                        cambio = "No aplica";
	                    }
	                } else {
	                    cambio = "No aplica";
	                }

	                fila.add(cambio);
	            } else {
	                fila.add(valor);
	            }
	    	}
	    	datos.add(fila);
	    }
	    
		
		
		return new DefaultTableModel(datos, columnNames);
	}

	
	public void showMessageDialog(JFrame fame, String n, boolean activar) {
		  // ventana = JFrame padre
        if (activar) {
        	cargando = new JDialog(frame, "Espere...", false);
        	JLabel mensaje = new JLabel(n, SwingConstants.CENTER);
            mensaje.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            cargando.add(mensaje);
            cargando.pack();
            cargando.setLocationRelativeTo(this);
            cargando.setVisible(true);
    	}else {
    		if(cargando != null) {
    			cargando.dispose();
    			cargando = null;		// libera la referencia para la proxima creacion de la ventana
    		}
    	}
        }
	
	
	// Retorna el id != 0 para los campos de combobox
	private Integer retornarID (String[][] lista, String valor) {
		for (String[] m : lista) {
			if (m[0].equalsIgnoreCase(valor)) {
				if(m[1].equalsIgnoreCase("0")) {
					return null;
				}else {
					return Integer.parseInt(m[1]);
				}
			}
		}
		
		return null;
	}
	private String retornaValor(String[][] lista, Integer id) {
		for (String[] m : lista) {
			if (m[1].equalsIgnoreCase(String.valueOf(id))) {
				return m[0];
			}
		}
		
		return "Selecciona";
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
	
	private void restablecer(JTextField IDe, JTextField col, JTextField call, JTextField numext, JTextField numint, 
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
	
		// metodo para actualizar la GUI recibiendo como parametro el objeto de donador y los componentes a actualizar
	private void ActualizarDatosGUIDonador(Donador modelDonador,
			JTextField IDe, JTextField col, JTextField call, JTextField numext, JTextField numint, 
			JFormattedTextField telefon, JTextField email5, JComboBox<String> tipoDona, JComboBox<String> RelacionUni, 
			JComboBox<String> clase, JComboBox<String> progDona) {
		
		if (modelDonador != null) {
			IDe.setText(String.valueOf(modelDonador.getID()));
			col.setText(modelDonador.getColonia());
			call.setText(modelDonador.getCalle());
			numext.setText(modelDonador.getNumExt());
			numint.setText(modelDonador.getNumInt());
			telefon.setText(modelDonador.getNumContacto());
			email5.setText(modelDonador.getEmail());
			tipoDona.setSelectedItem(retornaValor(tiposDonadores, modelDonador.getIDTipoDonador()));
			Integer op;
			op = modelDonador.getIDRelacionUni();
			System.out.println(modelDonador.getIDRelacionUni());
			System.out.println(retornaValor(RelacionesUni, op));
			if(op== null) {
				RelacionUni.setSelectedIndex(0);
			}else if (op != null) {
				RelacionUni.setSelectedItem(retornaValor(RelacionesUni, op));
			}
			op = modelDonador.getIDClase();
			if(op == null) {
				clase.setSelectedIndex(0);
			}else if(op != null) {
				clase.setSelectedItem(retornaValor(RelacionesUni, op));
			}
			op = modelDonador.getIDProgDonacion();
			if(op==null) {
				progDona.setSelectedIndex(0);
			}else if (op != null) {
				progDona.setSelectedItem(retornaValor(programasDonacion, op));
			}
		}else {
			JOptionPane.showMessageDialog(this, 
	                "No se encontro ningun donador con el id "+ ID2.getText(), 
	                "Error", 
	                JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}
	
	
	//verifica que solo los datos que no aceptan nulo extan correctamente completos
	
	public boolean verificadorDatos(JTextField col, JTextField call, JTextField numext, JTextField numint, JFormattedTextField telefon, JTextField email5, JComboBox<String> tipoDona) {
		int hayError = 0;
		errores.clear();
		Color rojoClaro = new Color(220, 255, 220);
		 if (col.getText().isEmpty() || col.getText().length() >40) {
			 col.setBackground(new Color(255, 200, 200));
			 errores.add("campo colonia obligatorio, maximo 40 caracteres");
			 hayError ++;
		 }
		 if(call.getText().isEmpty() || call.getText().length() > 40) {
			 call.setBackground(new Color(255, 200, 200));
			 errores.add("campo calle obligatorio, maximo 40 caracteres");
			 hayError ++;
		 }
		 if (numext.getText().isEmpty() || numext.getText().length() >10) {
			 numext.setBackground(new Color(255, 200, 200));
			 errores.add("campo numero exterior obligatorio, maximo 10 caracteres");
			 hayError ++;
		 }
		 if (numint.getText().isEmpty() || numint.getText().length() > 10) {
			 numint.setBackground(new Color(255, 200, 200));
			 errores.add("campo numero interior obligatorio, maximo 10 caracteres o 'S/N' si no aplica");
			 hayError ++;
		 }
		 if (telefon.getText().contains("_")) {
			 telefon.setBackground(new Color(255, 200, 200));
			 errores.add("campo telefono obligatorio, numero de telefono con codigo de pais");
			 hayError ++;
		 }
		 if (email5.getText().isEmpty() || !isValidEmail(email5.getText())) {
			 email5.setBackground(new Color(255, 200, 200));
			 if (!isValidEmail(email5.getText())) {
				 errores.add("Formato de Email icorrecto debe cumplir con el formato 'alguien@dominio.com'");
			 }else {
				 errores.add("campo Email obligatorio, maximo 60 caracteres");
			 }
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
		String regex = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
	    return email.matches(regex) && email.length() <= 60;
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
			HilosConsultaActualizarGUI hilosGUI = new HilosConsultaActualizarGUI(null, null, interfaz);
			hilosGUI.consultarYActualizarGUI();
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
				JOptionPane.showMessageDialog(this, 
				        m, 
				        "Error", 
				        JOptionPane.ERROR_MESSAGE);
			}else {
				DonadorDAO dondao =new DonadorDAO(interfaz);
				String RU = String.valueOf(RelacionUni.getSelectedItem());
				String TD = String.valueOf(tipoDonador.getSelectedItem());
				String CL = String.valueOf(clase.getSelectedItem());
				String PD = String.valueOf(progDona.getSelectedItem());
				String nnum = ID.getText();
				boolean num = nnum.equalsIgnoreCase("");
				boolean newDona = false;
				try {
					if (!num) {
						newDona = dondao.insertarDonador(Integer.parseInt(ID.getText()),
								colonia.getText(), calle.getText(), numExt.getText(), numInt.getText(), 
								telefono.getText(), email.getText(), retornarID(RelacionesUni, RU),retornarID(tiposDonadores, TD), retornarID(clases, CL), 
								retornarID(programasDonacion, PD));
					}else {
						newDona = dondao.insertarDonador(null,
								colonia.getText(), calle.getText(), numExt.getText(), numInt.getText(), 
								telefono.getText(), email.getText(), retornarID(RelacionesUni, RU),retornarID(tiposDonadores, TD), retornarID(clases, CL), 
								retornarID(programasDonacion, PD));
					}
					
					
					if (newDona) {
						JOptionPane.showMessageDialog(this, 
						        "Datos correctos, el donador se guardaron en la base de datos", 
						        "Éxito", 
						        JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, 
					        "Se produjo un error al añadir al donador", 
					        "Error\n"+e2.getMessage(), 
					        JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		}else if(e.getSource() == modificare) {
			String v = ID2.getText().trim();
			if(!v.isEmpty()) {
				if (!verificadorDatos(colonia2, calle2, numExt2, numInt2, telefono2, email2, tipoDonador2)) {
					String m = "";
					for (String n : errores) {
						m = m + n + "\n";
					}
					JOptionPane.showMessageDialog(this, 
					        m, 
					        "Error", 
					        JOptionPane.ERROR_MESSAGE);
				}else {
					DonadorDAO dondao =new DonadorDAO(interfaz);
					String RU = String.valueOf(RelacionUni2.getSelectedItem());
					String TD = String.valueOf(tipoDonador2.getSelectedItem());
					String CL = String.valueOf(clase2.getSelectedItem());
					String PD = String.valueOf(progDona2.getSelectedItem());
					boolean newDona = dondao.actualizarDonador(Integer.parseInt(ID2.getText()),
							colonia2.getText(), calle2.getText(), numExt2.getText(), numInt2.getText(), 
							telefono2.getText(), email2.getText(), retornarID(RelacionesUni, RU),retornarID(tiposDonadores, TD), retornarID(clases, CL), 
							retornarID(programasDonacion, PD));
					
					if (newDona) {
						JOptionPane.showMessageDialog(this, 
						        "Datos correctos, el donador se guardaron en la base de datos", 
						        "Éxito", 
						        JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}else {
				JOptionPane.showMessageDialog(this, 
				        "Ingresa no hay ningun id, haz primero la busqueda para poder actualiza al donador", 
				        "Error", 
				        JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
	}
	
	
	public void ShowMessage(String message) {
		JOptionPane.showMessageDialog(this, 
		        "Ocurrio un error\n"+
		        		message, 
		        "Error", 
		        JOptionPane.INFORMATION_MESSAGE);
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
	
	private void agregarBotonIcon(JPanel modi2, JComponent componente, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.fill = GridBagConstraints.NONE;
	    gbc.weightx = 0;
		modi2.add(componente, gbc);
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

	
	public void setDonador(Donador dona, String ventana) {
		this.donadona = dona;
		
		if (ventana.equalsIgnoreCase("modifique")) {
			ActualizarDatosGUIDonador(dona, ID2,colonia2, calle2, numExt2, numInt2, 
					telefono2, email2, tipoDonador2, RelacionUni2, 
					clase2, progDona2);
			activarComponentes(colonia2, calle2, numExt2, numInt2, telefono2, email2, tipoDonador2, RelacionUni2, clase2, progDona2);
		}
	}




	
	public static void main (String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                interfaz = new VentanaInicio();

            }
        });
	}

	private JTextField crearCampoIDValidado() {
	    JTextField campoID = new JTextField(10);
	    
	    // Agregar KeyListener para validación en tiempo real
	    campoID.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyTyped(java.awt.event.KeyEvent evt) {
	            char c = evt.getKeyChar();
	            // Solo permitir dígitos y teclas de control
	            if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE 
	                && c != java.awt.event.KeyEvent.VK_DELETE) {
	                evt.consume(); // Cancelar el evento
	            }
	            
	            // Limitar a 10 caracteres
	            if (campoID.getText().length() >= 10 && Character.isDigit(c)) {
	                evt.consume();
	            }
	        }
	    });
	    
	    return campoID;
	}
	
}