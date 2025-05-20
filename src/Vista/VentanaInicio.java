package Vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import ConexionBD.ConexionBD;



class GUI extends JFrame implements ActionListener{
	ArrayList<String> errores = new ArrayList<String>();
	
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
	JPanel añadi, modi, consultOP, consultAll, elimi;
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
		
		getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Gestor Universidad");
        setSize(1020, 740);
        setLocationRelativeTo(null);
        setVisible(true);
        
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
        setFocusTraversalPolicyProvider(true);
        
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
        
        
        
        Donadores = new JInternalFrame();
        Donadores.setResizable(true);  
        Donadores.setSize(500,500);
        Donadores.setLayout(gbl);
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
        
        añadi = new JPanel();
        	JLabel tituloAñadi = new JLabel("Añadir Donador");
        	tituloAñadi.setHorizontalAlignment(SwingConstants.CENTER);
        	agregarComponente(añadi, tituloAñadi, 0, 0, 5, 1);
        	
        	agregarComponente(Donadores, new JLabel("ID (opcional)"), 0, 1, 1, 1);
        	try {
        	    MaskFormatter formatter = new MaskFormatter("##########");
        	    formatter.setPlaceholder("");
        	    formatter.setPlaceholderCharacter(' ');
        	    formatter.setAllowsInvalid(false);
        	    formatter.setOverwriteMode(true); 
        	    
        	    ID = new JFormattedTextField(formatter);
        	    ID.setColumns(10);
        	    
        	} catch (ParseException e) {
        	    telefono = new JFormattedTextField();
        	    JOptionPane.showMessageDialog(this, 
        	        "Error en formato de teléfono", 
        	        "Error", 
        	        JOptionPane.ERROR_MESSAGE);
        	}
        	ordenTabulacion1.add(ID);
        	agregarComponente(Donadores, ID, 1, 1, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Datos direccion"), 0, 2, 2, 1);
        	
        	agregarComponente(Donadores, new JLabel("Colonia: "), 0, 3, 1, 1);
        	colonia = new JTextField(10);
        	ordenTabulacion1.add(colonia);
        	agregarComponente(Donadores, colonia, 1, 3, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Calle: "), 0, 4, 1, 1);
        	calle = new JTextField(10);
        	ordenTabulacion1.add(calle);
        	agregarComponente(Donadores, calle, 1, 4, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Numero exterior: "), 0, 5, 1, 1);
        	numExt = new JTextField(10);
        	ordenTabulacion1.add(numExt);
        	agregarComponente(Donadores, numExt, 1, 5, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Numero interior ('S/N' si no aplica):"), 0, 6, 1, 1);
        	numInt = new JTextField(10);
        	ordenTabulacion1.add(numInt);
        	agregarComponente(Donadores, numInt, 1, 6, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Teléfono de contacto:"), 0, 7, 1, 1);

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
        	agregarComponente(Donadores, telefono, 1, 7, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Email:"), 0, 8, 1, 1);
        	email = new JTextField(25);
        	ordenTabulacion1.add(email);
        	agregarComponente(Donadores, email, 1, 8, 1, 1);

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
        	
        	agregarComponente(Donadores, new JLabel("Relacion con la universidad (opcional): "), 0, 9, 1, 1);
        	RelacionUni = new JComboBox<String>();
        	for (String[] n : RelacionesUni) {
        		RelacionUni.addItem(n[0]);
        	}
        	ordenTabulacion1.add(RelacionUni);
        	agregarComponente(Donadores, RelacionUni, 1, 9, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Tipo de donador"), 0, 10, 1, 1);
        	tipoDonador = new JComboBox<String>();
        	for (String[] n : tiposDonadores) {
        		tipoDonador.addItem(n[0]);
        	}
        	ordenTabulacion1.add(tipoDonador);
        	agregarComponente(Donadores, tipoDonador, 1, 10, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Clase (opcional): "), 0, 11, 1, 1);
        	clase = new JComboBox<String>();
        	for (String[] n : clases) {
        		clase.addItem(n[0]);
        	}
        	ordenTabulacion1.add(RelacionUni);
        	agregarComponente(Donadores, clase, 1, 11, 1, 1);
        	
        	agregarComponente(Donadores, new JLabel("Programa de donacion (opcional): "), 0, 12, 1, 1);
        	progDona = new JComboBox<String>();
        	for (String[] n : programasDonacion) {
        		progDona.addItem(n[0]);
        	}
        	ordenTabulacion1.add(RelacionUni);
        	agregarComponente(Donadores, progDona, 1, 12, 1, 1);
        	
        	añadire = new JButton("Añadir donador");
        	añadire.addActionListener(this);
        	agregarComponente(Donadores, añadire, 0, 13, 3, 1);
        	
        	
        opcionesPaneles.add(añadi, "Añadir");
        
        
        add(panel);
        
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
		InterFrame.add(componente, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
				System.out.println(telefono.getText());
				JOptionPane.showMessageDialog(this, 
				        m, 
				        "Error", 
				        JOptionPane.ERROR_MESSAGE);
			}else {
				
				
				
				
				
				//añadir funcionalidad para guardar en la base de datos
				
				
				
				
				
				
				
				
				
				
			}
			
		}
		
		
	}
	private void añadirOpciones () {
		setJMenuBar(menuBar);
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
