package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

import entidades.Carrera;
import entidades.Nadador;
import entidades.Programa;
import entidades.Torneo;
import negocio.ControladorCompetencia;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

public class FrameInscribirNadadorCarrera extends JInternalFrame implements InternalFrameListener
{

	private static final long serialVersionUID = 1L;
	private static FrameInscribirNadadorCarrera instancia = null;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private DefaultComboBoxModel<Torneo> modeloTor = new DefaultComboBoxModel<Torneo>();
	private DefaultComboBoxModel<Carrera> modeloCar = new DefaultComboBoxModel<Carrera>();
	private DefaultComboBoxModel<Nadador> modeloNad = new DefaultComboBoxModel<Nadador>();
	private JTable table;
	private DefaultTableModel mt = new DefaultTableModel();

	public FrameInscribirNadadorCarrera()
	{
        initComponents();
        addInternalFrameListener(this);
	}
	
	public static FrameInscribirNadadorCarrera obtenerInstancia()
	{
		if (instancia == null)
		{
			instancia = new FrameInscribirNadadorCarrera();
		}
		return instancia;
	}

	public static FrameInscribirNadadorCarrera devolverInstancia()
	{
		return instancia;
	}
	
	private void initComponents() 
	{
		setTitle("Inscribir Nadador");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setBounds(38, 11, 557, 350);
		getContentPane().setLayout(null);

		//Con el controlador traigo los programas y se lo asigno a un modelo para el CB
		ArrayList<Programa> programas = cc.traerLosProgramas();
		
		DefaultComboBoxModel<Programa> modeloPrg = new DefaultComboBoxModel<Programa>();
		for(Programa prg: programas)
		{
			modeloPrg.addElement(prg);
		}
		
		
		JComboBox<Nadador> cbNadadores = new JComboBox<Nadador>();
		cbNadadores.setBounds(113, 133, 218, 20);
		getContentPane().add(cbNadadores);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JComboBox<Carrera>cbCarreras = new JComboBox<Carrera>();
		cbCarreras.setBounds(113, 70, 218, 20);
		getContentPane().add(cbCarreras);
		
		cbCarreras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				buscarNadadoresYActualizarTabla(cbCarreras, cbNadadores);
			}
		});

		
		JComboBox<Torneo> cbTorneos = new JComboBox<Torneo>();
		cbTorneos.setBounds(113, 42, 218, 20);
		getContentPane().add(cbTorneos);
		
		cbTorneos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				buscarCarrerasPorTorneo(cbTorneos, cbCarreras);
			}
		});
		

		
		JComboBox<Programa> cbProgramas = new JComboBox<Programa>();
		cbProgramas.setBounds(113, 11, 218, 20);
		getContentPane().add(cbProgramas);
		cbProgramas.setModel(modeloPrg);
		cbProgramas.setSelectedIndex(-1);
		
		
		cbProgramas.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				buscarTorneosPorProgramas(cbTorneos, cbProgramas);				
			}
		});
		
		JLabel lblProgramas = new JLabel("Programas:");
		lblProgramas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgramas.setBounds(10, 14, 93, 14);
		getContentPane().add(lblProgramas);
		
		JLabel lblTorneos = new JLabel("Torneos:");
		lblTorneos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTorneos.setBounds(10, 45, 93, 14);
		getContentPane().add(lblTorneos);
		
		
		JLabel lblCarreras = new JLabel("Carreras:");
		lblCarreras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCarreras.setBounds(10, 73, 93, 14);
		getContentPane().add(lblCarreras);

		

		
		JLabel lblNadadores = new JLabel("Nadadores:");
		lblNadadores.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNadadores.setBounds(10, 136, 93, 14);
		getContentPane().add(lblNadadores);
		
		
		JButton btnAgregarNadador = new JButton("Agregar");
		
		btnAgregarNadador.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				Nadador n = (Nadador)cbNadadores.getSelectedItem();
				if (n == null)
					JOptionPane.showMessageDialog(getContentPane(), "No hay nadador para agregar");
				else
				{
					Carrera c = (Carrera)cbCarreras.getSelectedItem();
					cc.cargarNadadorEnCarrera(n.getDni(), c.getNroCarrera());				
					buscarNadadoresYActualizarTabla(cbCarreras, cbNadadores);
				}
			}
		});
		
		btnAgregarNadador.setBounds(341, 133, 89, 20);
		getContentPane().add(btnAgregarNadador);
		
		
		JButton btnQuitarNadador = new JButton("Quitar");
		btnQuitarNadador.setBounds(440, 133, 89, 20);
		getContentPane().add(btnQuitarNadador);
		
		btnQuitarNadador.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(table.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un nadador de la grilla para quitarlo");
				else
				{
					Carrera carrera = (Carrera)cbCarreras.getSelectedItem();
					int dni = (int)table.getValueAt(table.getSelectedRow(), 2);
					cc.quitarNadadorDeCarrera(dni ,carrera.getNroCarrera());
					buscarNadadoresYActualizarTabla(cbCarreras, cbNadadores);
				}
			}
		});
		
		JButton btnGenerarSeries = new JButton("Generar Series");
		btnGenerarSeries.setBounds(341, 10, 188, 112);
		getContentPane().add(btnGenerarSeries);
		
		btnGenerarSeries.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				Carrera car = (Carrera)cbCarreras.getSelectedItem();
				if (cc.generarSeriesPorCarrera(car.getNroCarrera()))
					JOptionPane.showMessageDialog(getContentPane(), "Éxito al generar serie(s)");
				else
					JOptionPane.showMessageDialog(getContentPane(), "La carrera no puede tener menos de 1 nadador");
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 164, 521, 145);
		getContentPane().add(scrollPane);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) 
	{
		instancia = null;		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) 
	{
		this.dispose();
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void buscarNadadoresYActualizarTabla(JComboBox<Carrera> cbCarreras, JComboBox<Nadador> cbNadadores)
	{
		Carrera carrera = (Carrera)cbCarreras.getSelectedItem();
		if (carrera!= null)
		{
			ArrayList<Nadador> listaNadadores = cc.traerTodosNadadores(carrera.getTipoCarrera(), carrera.getNroCarrera());
			
			for(Nadador nad : listaNadadores)
			{
				modeloNad.addElement(nad);
			}
			cbNadadores.setModel(modeloNad);
	
			
			ArrayList<Nadador> listaNadadoresPorCarrera = cc.buscarNadadoresPorCarrera(carrera.getNroCarrera());
			
			DefaultTableModel modeloTabla = new DefaultTableModel()
			{
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};
			
			mt = modeloTabla;
			
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
	}

	private void buscarCarrerasPorTorneo(JComboBox<Torneo> cbTorneos, JComboBox<Carrera> cbCarreras)
	{
		Torneo tor = (Torneo)cbTorneos.getSelectedItem();		
		if (tor != null)
		{
			ArrayList<Carrera> carrerasPorPrograma = cc.traerCarrerasPorTorneo(tor.getNroTorneo());
			modeloCar.removeAllElements();
			for(Carrera cars: carrerasPorPrograma)
			{
					modeloCar.addElement(cars);
			}
			cbCarreras.setModel(modeloCar);
			for (int i = mt.getRowCount()- 1; i >= 0; i--)
			    mt.removeRow(i);
			modeloNad.removeAllElements();
		}
	}
	
	private void buscarTorneosPorProgramas(JComboBox<Torneo> cbTorneos, JComboBox<Programa> cbProgramas)
	{
		Programa prog = (Programa)cbProgramas.getSelectedItem();
		if (prog != null)
		{
			
			for (int i = mt.getRowCount()- 1; i >= 0; i--)
			    mt.removeRow(i);
			
			int nroProg = prog.getNroPrograma();
			
			ArrayList<Torneo> torneosPorPrograma = cc.buscarTorneosPorPrograma(nroProg);
			
			modeloTor.removeAllElements();
			for(Torneo tors: torneosPorPrograma)
			{
				modeloTor.addElement(tors);
			}
			cbTorneos.setModel(modeloTor);
			modeloNad.removeAllElements();
			modeloCar.removeAllElements();
		}
	}
}
