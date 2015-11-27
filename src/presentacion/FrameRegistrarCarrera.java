package presentacion;

import java.awt.Font;
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 0, 611, 339);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Cargar una nueva carrera a un torneo");
		label.setFont(new Font("Sitka Text", Font.BOLD, 25));
		label.setBounds(61, 11, 490, 46);
		getContentPane().add(label);
		
		txtNroCarrera = new JTextField();
		txtNroCarrera.setColumns(10);
		txtNroCarrera.setBounds(175, 96, 93, 20);
		getContentPane().add(txtNroCarrera);
		
		JLabel label_1 = new JLabel("Numero de Carrera:");
		label_1.setBounds(61, 99, 104, 14);
		getContentPane().add(label_1);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(175, 139, 93, 20);
		getContentPane().add(txtEdad);
		
		JLabel label_2 = new JLabel("Edad de los competidores:");
		label_2.setBounds(130, 142, 46, 14);
		getContentPane().add(label_2);
		
		txtMetros = new JTextField();
		txtMetros.setColumns(10);
		txtMetros.setBounds(175, 184, 93, 20);
		getContentPane().add(txtMetros);
		
		JLabel label_3 = new JLabel("Metros:");
		label_3.setBounds(119, 187, 46, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Genero:");
		label_4.setBounds(119, 229, 46, 14);
		getContentPane().add(label_4);
		
		JComboBox<Torneo> cb2Torneo = new JComboBox<Torneo>();
		cb2Torneo.setBounds(278, 184, 307, 20);
		getContentPane().add(cb2Torneo);
		
		JLabel label_5 = new JLabel("Torneo:");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_5.setBounds(380, 139, 116, 34);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Estilo:");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_6.setBounds(362, 57, 134, 29);
		getContentPane().add(label_6);
		
		JComboBox<Estilo> cb2Estilo = new JComboBox<Estilo>();
		cb2Estilo.setBounds(328, 96, 211, 20);
		getContentPane().add(cb2Estilo);
		
		Object[] opciones = {"Masculino", "Femenino"};
		
		JComboBox<Object> cb2Genero = new JComboBox<Object>(opciones);
		cb2Genero.setBounds(175, 226, 93, 20);
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
		
		JButton btn2NuevaCarrera = new JButton("Registrar Nueva Carrera");
		btn2NuevaCarrera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
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
		btn2NuevaCarrera.setBounds(340, 213, 192, 46);
		getContentPane().add(btn2NuevaCarrera);
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
