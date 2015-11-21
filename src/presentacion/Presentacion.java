package presentacion;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.mysql.jdbc.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import negocio.ControladorCompetencia;
import entidades.*;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import reportes.Reporte;
import conexion.DataConnection;
import reportes.AbstractReportes;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Presentacion extends JFrame {
	
	
	private static Connection con = (Connection) DataConnection.getInstancia().getConn();

	
	private JPanel panel_1;
	private JPanel contentPane;
	private JPanel MenuPpal;
	private JPanel abmPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEdad;
	private JTextField txtClub;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private JTextField txtDni;
	private JTable tableNadadoresCarrera;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion frame = new Presentacion();
					
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
	public Presentacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		//Creo una mascara para que no se equivoque el usuario al ingresar el tiempo
		MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##:##:##");//the # is for numeric values
            mask.setPlaceholderCharacter('0');
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		
		
		abmPane = new JPanel();
		abmPane.setBounds(5, 5, 379, 251);
		contentPane.add(abmPane);
		abmPane.setLayout(null);
		//Visibilidad de los paneles
		abmPane.setVisible(false);
		//setBounds(100, 100, 235, 300);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(5, 21, 57, 26);
		abmPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(56, 27, 100, 20);
		abmPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setBounds(220, 21, 62, 26);
		abmPane.add(lblNewLabel_1);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(275, 27, 100, 20);
		abmPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(5, 54, 47, 29);
		abmPane.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(56, 61, 100, 20);
		abmPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Club:");
		lblNewLabel_2.setBounds(5, 94, 57, 26);
		abmPane.add(lblNewLabel_2);
		
		txtClub = new JTextField();
		txtClub.setBounds(56, 100, 100, 20);
		abmPane.add(txtClub);
		txtClub.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tiempo:");
		lblNewLabel_3.setBounds(5, 145, 46, 14);
		abmPane.add(lblNewLabel_3);
		
		txtDni = new JTextField();
		txtDni.setBounds(275, 58, 100, 20);
		abmPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Dni:");
		lblNewLabel_4.setBounds(220, 61, 46, 14);
		abmPane.add(lblNewLabel_4);
		
		JFormattedTextField txtTiempo = new JFormattedTextField(mask);
		txtTiempo.setBounds(56, 142, 100, 20);
		abmPane.add(txtTiempo);
		
		JButton btnCargar = new JButton("Agregar");
		btnCargar.addMouseListener(new MouseAdapter() {
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
						JOptionPane.showMessageDialog(abmPane, "No ingresó el tiempo");
					}
					else
					{
						cc.cargarNadador(dni, nombre, apellido, club, edad, t);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(abmPane, "Error, hay un campo en blanco");
				
				}
			}

			
		});
		btnCargar.setBounds(142, 196, 106, 29);
		abmPane.add(btnCargar);
		
		JButton btnVolver = new JButton("< Atr\u00E1s ");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBounds(100, 100, 230, 300);
				MenuPpal.setVisible(true);
				abmPane.setVisible(false);
				
				
			}
		});
		btnVolver.setBounds(263, 196, 101, 29);
		abmPane.add(btnVolver);
		//MenuPpal.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 467, 261);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		MenuPpal = new JPanel();
		MenuPpal.setBounds(5, 5, 205, 251);
		contentPane.add(MenuPpal);
		MenuPpal.setLayout(null);
		
		ArrayList<Nadador> listaNadadores = cc.traerTodosNadadores();
		
		DefaultComboBoxModel<Nadador> modeloNad = new DefaultComboBoxModel<Nadador>();
		for(Nadador nad : listaNadadores)
		{
			modeloNad.addElement(nad);
		}
		
		ArrayList<Programa> programasPorTorneo = cc.traerLosProgramas();
		DefaultComboBoxModel<Programa> modeloPrg = new DefaultComboBoxModel<Programa>();
		for(Programa prg: programasPorTorneo)
		{
			modeloPrg.addElement(prg);
		}
		
		tableNadadoresCarrera = new JTable();
		tableNadadoresCarrera.setBounds(10, 147, 447, 103);
		panel_1.add(tableNadadoresCarrera);
		
		JComboBox<Nadador> cbNadadores = new JComboBox<Nadador>();
		cbNadadores.setBounds(10, 116, 132, 20);
		panel_1.add(cbNadadores);
		cbNadadores.setModel(modeloNad);
		
		JComboBox<Carrera> cbCarreras = new JComboBox<Carrera>();
		cbCarreras.setBounds(188, 42, 269, 20);
		panel_1.add(cbCarreras);
		
		DefaultComboBoxModel<Carrera> modeloCar = new DefaultComboBoxModel<Carrera>();
		
		JComboBox cbProgramas = new JComboBox();
		cbProgramas.setModel(modeloPrg);
		cbProgramas.setSelectedIndex(-1);
		
		cbProgramas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				Programa prog = (Programa)cbProgramas.getSelectedItem();
				int nroProg = prog.getNroPrograma();
				
				/*ArrayList<Carrera> carrerasPorPrograma = cc.traerCarrerasPorPrograma(nroProg);
				
				modeloCar.removeAllElements();
				for(Carrera cars: carrerasPorPrograma)
				{
					if (arg0.getStateChange() == ItemEvent.SELECTED)
						modeloCar.addElement(cars);
				}
				
				
				cbCarreras.setModel(modeloCar);*/
			}
		});
		cbProgramas.setBounds(188, 11, 269, 20);
		panel_1.add(cbProgramas);
		
		
		

		
		
		
		
		
		
		JButton btnAgregarNadador = new JButton("Agregar Nadador");
		btnAgregarNadador.setBounds(152, 115, 76, 23);
		panel_1.add(btnAgregarNadador);
		
		JButton btnEliminarNadador = new JButton("Eliminar Nadador");
		btnEliminarNadador.setBounds(238, 115, 76, 23);
		panel_1.add(btnEliminarNadador);
		
		JButton btnGenerarSeries = new JButton("Generar Series");
		btnGenerarSeries.setBounds(325, 115, 76, 23);
		panel_1.add(btnGenerarSeries);
		
		JLabel lblTorneos = new JLabel("Programa");
		lblTorneos.setBounds(132, 14, 46, 14);
		panel_1.add(lblTorneos);
		
		JLabel lblCarreras = new JLabel("Carreras:");
		lblCarreras.setBounds(132, 45, 46, 14);
		panel_1.add(lblCarreras);
		
		JLabel lblNadadores = new JLabel("Nadadores:");
		lblNadadores.setBounds(10, 91, 70, 14);
		panel_1.add(lblNadadores);
		
		JButton btnAtras = new JButton("<");
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				panel_1.setVisible(false);
				MenuPpal.setVisible(true);
				setBounds(100, 100, 230, 300);
				
			}
		});
		btnAtras.setBounds(411, 115, 46, 23);
		panel_1.add(btnAtras);
		
		
		
		JButton btnNewButton = new JButton("Alta Baja Nadadores");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setBounds(100, 100, 400, 300);
				MenuPpal.setVisible(false);
				abmPane.setVisible(true);
				
				
			}
		});
		btnNewButton.setBounds(23, 61, 161, 23);
		MenuPpal.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Menu Principal");
		lblNewLabel_5.setFont(new Font("Sitka Text", Font.BOLD, 18));
		lblNewLabel_5.setBounds(23, 11, 141, 28);
		MenuPpal.add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Generar Series");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				setBounds(100, 100, 483, 300);
				MenuPpal.setVisible(false);
				panel_1.setVisible(true);
				
				
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
				
			
	
		btnNewButton_1.setBounds(23, 129, 161, 23);
		MenuPpal.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Generar Premios");
		btnNewButton_2.setBounds(23, 163, 161, 23);
		MenuPpal.add(btnNewButton_2);
		
		JButton btnNadadores = new JButton("Nadadores Inscriptos");
		btnNadadores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				
				AbstractReportes.crearReporte(con, "C:\\Users\\Leo\\JaspersoftWorkspace\\Prueba\\nadadores.jasper");
				AbstractReportes.showViewer();
				
				
			}
		});
		btnNadadores.setBounds(23, 95, 161, 23);
		MenuPpal.add(btnNadadores);
		
		
		
		
		
		
	}
}
