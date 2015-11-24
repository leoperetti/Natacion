package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import java.awt.CardLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.event.MenuKeyListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entidades.*;
import negocio.ControladorCompetencia;

import javax.swing.event.MenuKeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Presentacion2 extends JFrame {

	private JDesktopPane contentPane;
	private JTextField textField;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtEdad;
	private JTextField txtClub;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private JTable table;
	private JTextField txt2NroCarrera;
	private JTextField txt2Edad;
	private JTextField txt2Metros;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion2 frame = new Presentacion2();
					ImageIcon img = new ImageIcon("Icono/iconoNadador.png");
					frame.setIconImage(img.getImage());
					frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Presentacion2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 410);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNadadores = new JMenu("Nadadores");
		menuBar.add(mnNadadores);
		
		
		
		JMenuItem mntmAgregarNadador = new JMenuItem("Agregar Nadador");
		mntmAgregarNadador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				JInternalFrame internalFrame = new JInternalFrame("Nuevo Nadador", true, true, true, true);
				internalFrame.setBounds(27, 11, 445, 242);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(10, 11, 57, 26);
				internalFrame.getContentPane().add(lblNombre);
				
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(72, 17, 100, 20);
				internalFrame.getContentPane().add(txtNombre);
				
				txtApellido = new JTextField();
				txtApellido.setColumns(10);
				txtApellido.setBounds(280, 17, 100, 20);
				internalFrame.getContentPane().add(txtApellido);
				
				JLabel lblApellido = new JLabel("Apellido:");
				lblApellido.setBounds(225, 11, 62, 26);
				internalFrame.getContentPane().add(lblApellido);
				
				txtDni = new JTextField();
				txtDni.setColumns(10);
				txtDni.setBounds(280, 48, 100, 20);
				internalFrame.getContentPane().add(txtDni);
				
				JLabel lblDni = new JLabel("Dni:");
				lblDni.setBounds(225, 51, 46, 14);
				internalFrame.getContentPane().add(lblDni);
				
				txtEdad = new JTextField();
				txtEdad.setColumns(10);
				txtEdad.setBounds(72, 51, 100, 20);
				internalFrame.getContentPane().add(txtEdad);
				
				JLabel lblEdad = new JLabel("Edad:");
				lblEdad.setBounds(10, 44, 47, 29);
				internalFrame.getContentPane().add(lblEdad);
				
				JLabel lblClub = new JLabel("Club:");
				lblClub.setBounds(10, 84, 57, 26);
				internalFrame.getContentPane().add(lblClub);
				
				txtClub = new JTextField();
				txtClub.setColumns(10);
				txtClub.setBounds(72, 90, 100, 20);
				internalFrame.getContentPane().add(txtClub);
				
				//Creo una mascara para que no se equivoque el usuario al ingresar el tiempo
				MaskFormatter mask = null;
		        try 
		        {
		            mask = new MaskFormatter("##:##:##");//the # is for numeric values
		            mask.setPlaceholderCharacter('0');
		        } 
		        catch (ParseException e) 
		        {
		            e.printStackTrace();
		        }
		        
				
				JFormattedTextField txtTiempo1 = new JFormattedTextField(mask);
				txtTiempo1.setBounds(72, 132, 100, 20);
				internalFrame.getContentPane().add(txtTiempo1);
				
				JLabel lblTiempo = new JLabel("Tiempo 1:");
				lblTiempo.setBounds(10, 135, 57, 14);
				internalFrame.getContentPane().add(lblTiempo);
				
				JLabel lblNewLabel = new JLabel("Tiempo 2:");
				lblNewLabel.setBounds(10, 168, 57, 14);
				internalFrame.getContentPane().add(lblNewLabel);
				
				JFormattedTextField txtTiempo2 = new JFormattedTextField(mask);
				txtTiempo2.setBounds(72, 165, 100, 20);
				internalFrame.getContentPane().add(txtTiempo2);
				
				JButton btnAgregar = new JButton("Agregar");
				btnAgregar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						
						if(!(txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty() && 
							txtEdad.getText().isEmpty() && txtClub.getText().isEmpty()
							&& txtDni.getText().isEmpty()))
						{
							int dni = Integer.parseInt(txtDni.getText());
							String nombre = txtNombre.getText();
							String apellido = txtApellido.getText();
							String club = txtClub.getText();
							int edad = Integer.parseInt(txtEdad.getText());
							String[] tiempoString1 = txtTiempo1.getText().split(":");
							Time t1 =  new Time(Integer.parseInt(tiempoString1[0]), Integer.parseInt(tiempoString1[1]), Integer.parseInt(tiempoString1[2]));
							
							String[] tiempoString2 = txtTiempo2.getText().split(":");
							Time t2 =  new Time(Integer.parseInt(tiempoString2[0]), Integer.parseInt(tiempoString2[1]), Integer.parseInt(tiempoString2[2]));

							if((Integer.parseInt(tiempoString1[0]) == 00) && (Integer.parseInt(tiempoString1[1]) == 0) && (Integer.parseInt(tiempoString1[2]) == 0)
									&& (Integer.parseInt(tiempoString2[0]) == 00) && (Integer.parseInt(tiempoString2[1]) == 0) && (Integer.parseInt(tiempoString2[2]) == 0))
							{
								JOptionPane.showMessageDialog(internalFrame.getContentPane(), "No ingresó el tiempo");
							}
							else
							{
								cc.cargarNadador(dni, nombre, apellido, club, edad, t1, t2);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(internalFrame.getContentPane(), "Error, hay un campo en blanco");
						
						}
					}

					
				});
				btnAgregar.setBounds(319, 172, 100, 29);
				internalFrame.getContentPane().add(btnAgregar);
				
				internalFrame.setVisible(true);

				
				
			}
		});


		mnNadadores.add(mntmAgregarNadador);
		
		JMenu mnCarreras = new JMenu("Carreras");
		menuBar.add(mnCarreras);
		
		JMenuItem mntmInscribirACarrera = new JMenuItem("Inscribir a Carrera/Generar Serie");
		
		mntmInscribirACarrera.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JInternalFrame frmInscribirACarrera = new JInternalFrame("Inscribir a Carrera", true, true, true ,true);
				frmInscribirACarrera.setBounds(38, 11, 557, 350);
				contentPane.add(frmInscribirACarrera);
				frmInscribirACarrera.getContentPane().setLayout(null);

				//Con el controlador traigo los programas y se lo asigno a un modelo para el CB
				ArrayList<Programa> programas = cc.traerLosProgramas();
				
				DefaultComboBoxModel<Programa> modeloPrg = new DefaultComboBoxModel<Programa>();
				for(Programa prg: programas)
				{
					modeloPrg.addElement(prg);
				}
				
				
				JComboBox<Nadador> cbNadadores = new JComboBox<Nadador>();
				cbNadadores.setBounds(113, 133, 218, 20);
				frmInscribirACarrera.getContentPane().add(cbNadadores);
				
				table = new JTable();
				JComboBox<Carrera>cbCarreras = new JComboBox<Carrera>();
				cbCarreras.addActionListener(new ActionListener() 
				{

					public void actionPerformed(ActionEvent arg0)
					{
						
						//Con el controlador traigo los nadadores y se lo asigno a un modelo para el CB
						Carrera carrera = (Carrera)cbCarreras.getSelectedItem();

						ArrayList<Nadador> listaNadadores = cc.traerTodosNadadores(carrera.getTipoCarrera(), carrera.getNroCarrera());

						DefaultComboBoxModel<Nadador> modeloNad = new DefaultComboBoxModel<Nadador>();
						
						for(Nadador nad : listaNadadores)
						{
							modeloNad.addElement(nad);
						}
						cbNadadores.setModel(modeloNad);

						
						ArrayList<Nadador> listaNadadoresPorCarrera = cc.buscarNadadoresPorCarrera(carrera.getNroCarrera());
						DefaultTableModel modeloTabla = new DefaultTableModel();
						Object[] identifiers = {"Nombre", "Apellido", "Dni"};
						modeloTabla.setColumnIdentifiers(identifiers);
						for(Nadador nad : listaNadadoresPorCarrera)
						{
							Object[] o = new Object[3];
							o[0] = nad.getNombre();
							o[1] = nad.getApellido();
							o[2] = nad.getDni();
							modeloTabla.addRow(o);
						}
						table.setModel(modeloTabla);
					}
				});
				
				cbCarreras.setBounds(113, 70, 218, 20);
				frmInscribirACarrera.getContentPane().add(cbCarreras);
				
				JComboBox<Torneo> cbTorneos = new JComboBox<Torneo>();
				
				

				cbTorneos.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						
						Torneo tor = (Torneo)cbTorneos.getSelectedItem();				
						ArrayList<Carrera> carrerasPorPrograma = cc.traerCarrerasPorTorneo(tor.getNroTorneo());
						DefaultComboBoxModel<Carrera> modeloCar = new DefaultComboBoxModel<Carrera>();
						modeloCar.removeAllElements();
						for(Carrera cars: carrerasPorPrograma)
						{
								modeloCar.addElement(cars);
						}
						
						
						cbCarreras.setModel(modeloCar);
					}
				});
				
				cbTorneos.setBounds(113, 42, 218, 20);
				frmInscribirACarrera.getContentPane().add(cbTorneos);
				
				JComboBox<Programa> cbProgramas = new JComboBox<Programa>();
				cbProgramas.setBounds(113, 11, 218, 20);
				frmInscribirACarrera.getContentPane().add(cbProgramas);
				cbProgramas.setModel(modeloPrg);
				cbProgramas.setSelectedIndex(-1);
				
				
				//Cuando elijo un programa me muestra las carreras
				cbProgramas.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						Programa prog = (Programa)cbProgramas.getSelectedItem();
						int nroProg = prog.getNroPrograma();
						
						DefaultComboBoxModel<Torneo> modeloTor = new DefaultComboBoxModel<Torneo>();
						ArrayList<Torneo> torneosPorPrograma = cc.buscarTorneosPorPrograma(nroProg);
						
						modeloTor.removeAllElements();
						for(Torneo tors: torneosPorPrograma)
						{
							modeloTor.addElement(tors);
						}
						cbTorneos.setModel(modeloTor);
						
					}
				});
				
				JLabel lblProgramas = new JLabel("Programas:");
				lblProgramas.setHorizontalAlignment(SwingConstants.RIGHT);
				lblProgramas.setBounds(10, 14, 93, 14);
				frmInscribirACarrera.getContentPane().add(lblProgramas);
				
				JLabel lblTorneos = new JLabel("Torneos:");
				lblTorneos.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTorneos.setBounds(10, 45, 93, 14);
				frmInscribirACarrera.getContentPane().add(lblTorneos);
				
				
				JLabel lblCarreras = new JLabel("Carreras:");
				lblCarreras.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCarreras.setBounds(10, 73, 93, 14);
				frmInscribirACarrera.getContentPane().add(lblCarreras);

				

				
				JLabel lblNadadores = new JLabel("Nadadores:");
				lblNadadores.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNadadores.setBounds(10, 136, 93, 14);
				frmInscribirACarrera.getContentPane().add(lblNadadores);
				
				
				JButton btnAgregarNadador = new JButton("Agregar");
				
				btnAgregarNadador.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						Nadador n = (Nadador)cbNadadores.getSelectedItem();
						Carrera c = (Carrera)cbCarreras.getSelectedItem();
						cc.cargarNadadorEnCarrera(n.getDni(), c.getNroCarrera());
						
						//DE TODO ESTO HICE COPYPASTE DE MAS ARRIBA
						//HAY QUE HACER UN METODO Y USARLO 2 VECES!!!
						ArrayList<Nadador> listaNadadoresPorCarrera = cc.buscarNadadoresPorCarrera(c.getNroCarrera());
						DefaultTableModel modeloTabla = new DefaultTableModel();
						Object[] identifiers = {"Nombre", "Apellido", "Dni"};
						modeloTabla.setColumnIdentifiers(identifiers);
						for(Nadador nad : listaNadadoresPorCarrera)
						{
							Object[] o = new Object[3];
							o[0] = nad.getNombre();
							o[1] = nad.getApellido();
							o[2] = nad.getDni();
							modeloTabla.addRow(o);
						}
						table.setModel(modeloTabla);
					}
				});
				
				btnAgregarNadador.setBounds(341, 133, 89, 20);
				frmInscribirACarrera.getContentPane().add(btnAgregarNadador);
				
				
				JButton btnQuitarNadador = new JButton("Quitar");
				btnQuitarNadador.setBounds(440, 133, 89, 20);
				frmInscribirACarrera.getContentPane().add(btnQuitarNadador);
				
				JButton btnGenerarSeries = new JButton("Generar Series");
				btnGenerarSeries.setBounds(341, 10, 188, 112);
				frmInscribirACarrera.getContentPane().add(btnGenerarSeries);
				
				btnGenerarSeries.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						Carrera car = (Carrera)cbCarreras.getSelectedItem();
						cc.generarSeriesPorCarrera(car.getNroCarrera());
					}
				});

				
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(10, 164, 521, 145);
				frmInscribirACarrera.getContentPane().add(scrollPane);
				frmInscribirACarrera.setVisible(true);
				
			}
		});
		mnCarreras.add(mntmInscribirACarrera);
		
		
		
		JMenuItem mntmCarrera = new JMenuItem("Cargar nueva carrera");
		mntmCarrera.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				JInternalFrame frmRegistrarCarrera = new JInternalFrame("Registrar Carrera");
				frmRegistrarCarrera.setMaximizable(true);
				frmRegistrarCarrera.setIconifiable(true);
				frmRegistrarCarrera.setClosable(true);
				frmRegistrarCarrera.setBounds(10, 0, 611, 339);
				contentPane.add(frmRegistrarCarrera);
				frmRegistrarCarrera.getContentPane().setLayout(null);
				
				JLabel label = new JLabel("Cargar una nueva carrera a un torneo");
				label.setFont(new Font("Sitka Text", Font.BOLD, 25));
				label.setBounds(61, 11, 490, 46);
				frmRegistrarCarrera.getContentPane().add(label);
				
				txt2NroCarrera = new JTextField();
				txt2NroCarrera.setColumns(10);
				txt2NroCarrera.setBounds(175, 96, 93, 20);
				frmRegistrarCarrera.getContentPane().add(txt2NroCarrera);
				
				JLabel label_1 = new JLabel("Numero de Carrera:");
				label_1.setBounds(61, 99, 104, 14);
				frmRegistrarCarrera.getContentPane().add(label_1);
				
				txt2Edad = new JTextField();
				txt2Edad.setColumns(10);
				txt2Edad.setBounds(175, 139, 93, 20);
				frmRegistrarCarrera.getContentPane().add(txt2Edad);
				
				JLabel label_2 = new JLabel("Edad de los competidores:");
				label_2.setBounds(130, 142, 46, 14);
				frmRegistrarCarrera.getContentPane().add(label_2);
				
				txt2Metros = new JTextField();
				txt2Metros.setColumns(10);
				txt2Metros.setBounds(175, 184, 93, 20);
				frmRegistrarCarrera.getContentPane().add(txt2Metros);
				
				JLabel label_3 = new JLabel("Metros:");
				label_3.setBounds(119, 187, 46, 14);
				frmRegistrarCarrera.getContentPane().add(label_3);
				
				JLabel label_4 = new JLabel("Genero:");
				label_4.setBounds(119, 229, 46, 14);
				frmRegistrarCarrera.getContentPane().add(label_4);
				
				JComboBox cb2Torneo = new JComboBox();
				cb2Torneo.setBounds(278, 184, 307, 20);
				frmRegistrarCarrera.getContentPane().add(cb2Torneo);
				
				JLabel label_5 = new JLabel("Torneo:");
				label_5.setHorizontalAlignment(SwingConstants.CENTER);
				label_5.setFont(new Font("Tahoma", Font.BOLD, 17));
				label_5.setBounds(380, 139, 116, 34);
				frmRegistrarCarrera.getContentPane().add(label_5);
				
				JLabel label_6 = new JLabel("Estilo:");
				label_6.setHorizontalAlignment(SwingConstants.CENTER);
				label_6.setFont(new Font("Tahoma", Font.BOLD, 17));
				label_6.setBounds(362, 57, 134, 29);
				frmRegistrarCarrera.getContentPane().add(label_6);
				
				JComboBox cb2Estilo = new JComboBox();
				cb2Estilo.setBounds(328, 96, 211, 20);
				frmRegistrarCarrera.getContentPane().add(cb2Estilo);
				
				Object[] opciones = {"Masculino", "Femenino"};
				
				JComboBox cb2Genero = new JComboBox(opciones);
				cb2Genero.setBounds(175, 226, 93, 20);
				frmRegistrarCarrera.getContentPane().add(cb2Genero);
				frmRegistrarCarrera.setVisible(true);
				
				ArrayList<Estilo> estilos = cc.traerLosEstilos();
				
				DefaultComboBoxModel<Estilo> modeloEstilos = new DefaultComboBoxModel<Estilo>();
				for(Estilo est: estilos)
				{
					modeloEstilos.addElement(est);
				}
				
				cb2Estilo.setModel(modeloEstilos);
				
				ArrayList<Torneo> torneos = cc.buscarTorneos();
				
				DefaultComboBoxModel<Torneo> modeloTorneos = new DefaultComboBoxModel<Torneo>();
				for(Torneo tor: torneos)
				{
					modeloTorneos.addElement(tor);
				}
				
				cb2Torneo.setModel(modeloTorneos);
				
				JButton btn2NuevaCarrera = new JButton("Registrar Nueva Carrera");
				btn2NuevaCarrera.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						char sexo;
						int tipo = Integer.parseInt(txt2Edad.getText());
						int nro = Integer.parseInt(txt2NroCarrera.getText());
						if(cb2Genero.getSelectedIndex()==0)
						{
							sexo = 'm';
						}
						else
						{
							sexo = 'f';
						}
						int metros = Integer.parseInt(txt2Metros.getText());
						Torneo t  = (Torneo)cb2Torneo.getSelectedItem();
						int nroTor = t.getNroTorneo();
						Estilo e = (Estilo)cb2Estilo.getSelectedItem();
						int nroEst = e.getNroEstilo();
						
						cc.cargarCarrera(nroEst, nro, tipo, metros, sexo, nroTor);
					}
				});
				btn2NuevaCarrera.setBounds(340, 213, 192, 46);
				frmRegistrarCarrera.getContentPane().add(btn2NuevaCarrera);
				
			}
		});
		mnCarreras.add(mntmCarrera);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
	
		
	}
}

