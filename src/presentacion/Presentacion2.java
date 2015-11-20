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

import entidades.Carrera;
import entidades.Nadador;
import entidades.Programa;
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
				txtNombre.setBounds(61, 17, 100, 20);
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
				txtEdad.setBounds(61, 51, 100, 20);
				internalFrame.getContentPane().add(txtEdad);
				
				JLabel lblEdad = new JLabel("Edad:");
				lblEdad.setBounds(10, 44, 47, 29);
				internalFrame.getContentPane().add(lblEdad);
				
				JLabel lblClub = new JLabel("Club:");
				lblClub.setBounds(10, 84, 57, 26);
				internalFrame.getContentPane().add(lblClub);
				
				txtClub = new JTextField();
				txtClub.setColumns(10);
				txtClub.setBounds(61, 90, 100, 20);
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
				
				JFormattedTextField txtTiempo = new JFormattedTextField(mask);
				txtTiempo.setBounds(61, 132, 100, 20);
				internalFrame.getContentPane().add(txtTiempo);
				
				JLabel lblTiempo = new JLabel("Tiempo:");
				lblTiempo.setBounds(10, 135, 46, 14);
				internalFrame.getContentPane().add(lblTiempo);
				
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
							String[] tiempoString = txtTiempo.getText().split(":");
							System.out.println(tiempoString[0]);
							Time t =  new Time(Integer.parseInt(tiempoString[0]), Integer.parseInt(tiempoString[1]), Integer.parseInt(tiempoString[2]));
							if((Integer.parseInt(tiempoString[0]) == 00) && (Integer.parseInt(tiempoString[1]) == 0) && (Integer.parseInt(tiempoString[2]) == 0))
							{
								JOptionPane.showMessageDialog(internalFrame.getContentPane(), "No ingresó el tiempo");
							}
							else
							{
								cc.cargarNadador(dni, nombre, apellido, club, edad, t);
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
				
				//Con el controlador traigo los nadadores y se lo asigno a un modelo para el CB
				ArrayList<Nadador> listaNadadores = cc.traerTodosNadadores();

				DefaultComboBoxModel<Nadador> modeloNad = new DefaultComboBoxModel<Nadador>();
				for(Nadador nad : listaNadadores)
				{
					modeloNad.addElement(nad);
				}

				//Con el controlador traigo los programas y se lo asigno a un modelo para el CB
				ArrayList<Programa> programasPorTorneo = cc.traerLosProgramas();
				
				DefaultComboBoxModel<Programa> modeloPrg = new DefaultComboBoxModel<Programa>();
				for(Programa prg: programasPorTorneo)
				{
					modeloPrg.addElement(prg);
				}
				
				JComboBox<Carrera> cbCarreras = new JComboBox<Carrera>();
				cbCarreras.setBounds(113, 70, 218, 20);
				frmInscribirACarrera.getContentPane().add(cbCarreras);
				
				JComboBox<Programa> cbProgramas = new JComboBox<Programa>();
				cbProgramas.setBounds(113, 11, 218, 20);
				frmInscribirACarrera.getContentPane().add(cbProgramas);
				cbProgramas.setModel(modeloPrg);
				cbProgramas.setSelectedIndex(-1);
				
				
				//Cuando elijo un programa me muestra las carreras
				cbProgramas.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						
						Programa prog = (Programa)cbProgramas.getSelectedItem();
						int nroProg = prog.getNroPrograma();
						
						DefaultComboBoxModel<Carrera> modeloCar = new DefaultComboBoxModel<Carrera>();
						ArrayList<Carrera> carrerasPorPrograma = cc.traerCarrerasPorPrograma(nroProg);
						
						modeloCar.removeAllElements();
						for(Carrera cars: carrerasPorPrograma)
						{
							if (arg0.getStateChange() == ItemEvent.SELECTED)
								modeloCar.addElement(cars);
						}
						
						
						cbCarreras.setModel(modeloCar);
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
				
				JComboBox cbTorneos = new JComboBox();
				cbTorneos.setBounds(113, 42, 218, 20);
				frmInscribirACarrera.getContentPane().add(cbTorneos);
				
				JLabel lblCarreras = new JLabel("Carreras:");
				lblCarreras.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCarreras.setBounds(10, 73, 93, 14);
				frmInscribirACarrera.getContentPane().add(lblCarreras);

				

				
				JLabel lblNadadores = new JLabel("Nadadores:");
				lblNadadores.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNadadores.setBounds(10, 136, 93, 14);
				frmInscribirACarrera.getContentPane().add(lblNadadores);
				
				JComboBox<Nadador> cbNadadores = new JComboBox<Nadador>();
				cbNadadores.setBounds(113, 133, 218, 20);
				frmInscribirACarrera.getContentPane().add(cbNadadores);
				cbNadadores.setModel(modeloNad);
				
				JButton btnAgregarNadador = new JButton("Agregar");
				btnAgregarNadador.setBounds(341, 133, 89, 20);
				frmInscribirACarrera.getContentPane().add(btnAgregarNadador);
				
				JButton btnQuitarNadador = new JButton("Quitar");
				btnQuitarNadador.setBounds(440, 133, 89, 20);
				frmInscribirACarrera.getContentPane().add(btnQuitarNadador);
				
				JButton btnGenerarSeries = new JButton("Generar Series");
				btnGenerarSeries.setBounds(341, 10, 188, 112);
				frmInscribirACarrera.getContentPane().add(btnGenerarSeries);
				
				listaNadadores = cc.traerTodosNadadores();
				DefaultTableModel modeloTabla = new DefaultTableModel();
				Object[] identifiers = {"Nombre", "Apellido", "Dni"};
				modeloTabla.setColumnIdentifiers(identifiers);
				for(Nadador nad : listaNadadores)
				{
					Object[] o = new Object[3];
					o[0] = nad.getNombre();
					o[1] = nad.getApellido();
					o[2] = nad.getDni();
					modeloTabla.addRow(o);
				}
				
				table = new JTable(modeloTabla);
				
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(10, 164, 521, 145);
				frmInscribirACarrera.getContentPane().add(scrollPane);
				frmInscribirACarrera.setVisible(true);
			}
		});
		mnCarreras.add(mntmInscribirACarrera);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

	}
}

