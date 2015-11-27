package presentacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import entidades.Estilo;
import entidades.Torneo;
import negocio.ControladorCompetencia;

public class FrameRegistrarCarrera extends JInternalFrame implements InternalFrameListener
{

	private static final long serialVersionUID = 1L;
	private static FrameRegistrarCarrera instancia = null;
	private JTextField txtNroCarrera, txtEdad, txtMetros;
	private ControladorCompetencia cc = new ControladorCompetencia();

	
	public static FrameRegistrarCarrera obtenerInstancia()
	{
		if (instancia == null)
		{
			instancia = new FrameRegistrarCarrera();
		}
		return instancia;
	}

	public static FrameRegistrarCarrera devolverInstancia()
	{
		return instancia;
	}
	
	public FrameRegistrarCarrera() 
	{
        initComponents();
        addInternalFrameListener(this);
	}

	private void initComponents() 
	{
		setTitle("Registrar Carrera");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 0, 469, 243);
		getContentPane().setLayout(null);
		
		txtNroCarrera = new JTextField();
		txtNroCarrera.setColumns(10);
		txtNroCarrera.setBounds(158, 91, 93, 20);
		getContentPane().add(txtNroCarrera);
		
		JLabel lblNmeroDeCarrera = new JLabel("N\u00FAmero de Carrera:");
		lblNmeroDeCarrera.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmeroDeCarrera.setBounds(10, 94, 138, 14);
		getContentPane().add(lblNmeroDeCarrera);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(158, 116, 93, 20);
		getContentPane().add(txtEdad);
		
		JLabel label_2 = new JLabel("Edad de los competidores:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 119, 138, 14);
		getContentPane().add(label_2);
		
		txtMetros = new JTextField();
		txtMetros.setColumns(10);
		txtMetros.setBounds(158, 141, 93, 20);
		getContentPane().add(txtMetros);
		
		JLabel label_3 = new JLabel("Metros:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(10, 144, 138, 14);
		getContentPane().add(label_3);
		
		JLabel lblGnero = new JLabel("G\u00E9nero:");
		lblGnero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGnero.setBounds(10, 168, 138, 14);
		getContentPane().add(lblGnero);
		
		JComboBox<Torneo> cb2Torneo = new JComboBox<Torneo>();
		cb2Torneo.setBounds(66, 8, 378, 20);
		getContentPane().add(cb2Torneo);
		
		JComboBox<Estilo> cb2Estilo = new JComboBox<Estilo>();
		cb2Estilo.setBounds(66, 39, 378, 20);
		getContentPane().add(cb2Estilo);
		
		Object[] opciones = {"Masculino", "Femenino"};
		
		JComboBox<Object> cb2Genero = new JComboBox<Object>(opciones);
		cb2Genero.setBounds(158, 165, 93, 20);
		getContentPane().add(cb2Genero);
		setVisible(true);
		
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
		
		JButton btn2NuevaCarrera = new JButton("<html><center>Registrar<br>Nueva Carrera</center></html>");
		btn2NuevaCarrera.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				char sexo;
				int tipo = Integer.parseInt(txtEdad.getText());
				int nro = Integer.parseInt(txtNroCarrera.getText());
				if(cb2Genero.getSelectedIndex()==0)
				{
					sexo = 'm';
				}
				else
				{
					sexo = 'f';
				}
				int metros = Integer.parseInt(txtMetros.getText());
				Torneo t  = (Torneo)cb2Torneo.getSelectedItem();
				int nroTor = t.getNroTorneo();
				Estilo e = (Estilo)cb2Estilo.getSelectedItem();
				int nroEst = e.getNroEstilo();
				
				cc.cargarCarrera(nroEst, nro, tipo, metros, sexo, nroTor);
			}
		});
		btn2NuevaCarrera.setBounds(292, 91, 152, 94);
		getContentPane().add(btn2NuevaCarrera);
		
		JLabel lblTorneo = new JLabel("Torneo:");
		lblTorneo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTorneo.setBounds(10, 11, 46, 14);
		getContentPane().add(lblTorneo);
		
		JLabel lblEstilo = new JLabel("Estilo:");
		lblEstilo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstilo.setBounds(10, 42, 46, 14);
		getContentPane().add(lblEstilo);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0)
	{
		instancia = null;
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0)
	{
		this.dispose();
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
