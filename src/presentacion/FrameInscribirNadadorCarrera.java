package presentacion;

import java.awt.Component;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;

import entidades.Carrera;
import entidades.Nadador;
import entidades.Programa;
import entidades.Torneo;
import negocio.ControladorCompetencia;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	private JComboBox<Programa> cbProgramas;
	private JComboBox<Torneo> cbTorneos;
	private JComboBox<Nadador> cbNadadores;
	private JComboBox<Carrera> cbCarreras;

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
		setBounds(38, 11, 624, 350);
		getContentPane().setLayout(null);

		
		cbNadadores = new JComboBox<Nadador>();
		cbNadadores.setBounds(81, 133, 319, 20);
		getContentPane().add(cbNadadores);
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		cbCarreras = new JComboBox<Carrera>();
		cbCarreras.setBounds(81, 70, 319, 20);
		getContentPane().add(cbCarreras);
		
				
		ActionListener alCarreras = new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				buscarNadadoresYActualizarTabla();
			}
		};

		cbCarreras.addActionListener(alCarreras);
		
		cbTorneos = new JComboBox<Torneo>();
		cbTorneos.setBounds(81, 42, 319, 20);
		getContentPane().add(cbTorneos);
		
		
		ActionListener alTorneos = new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				buscarCarrerasPorTorneo(alCarreras);
			}
		};
		
		cbTorneos.addActionListener(alTorneos);

		
		cbProgramas = new JComboBox<Programa>();
		cbProgramas.setBounds(81, 11, 319, 20);
		getContentPane().add(cbProgramas);
		cbProgramas.setModel(generarComboProgramas(cc.traerLosProgramas()));
		cbProgramas.setRenderer(new PromptComboBoxRenderer("<-Seleccione un Programa->"));
		cbProgramas.setSelectedIndex(-1);
		
		ActionListener alPrograma = new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				buscarTorneosPorProgramas(alCarreras, alTorneos);;				
			}
		};
		
		cbProgramas.addActionListener(alPrograma);
		
		JLabel lblProgramas = new JLabel("Programas:");
		lblProgramas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgramas.setBounds(10, 14, 61, 14);
		getContentPane().add(lblProgramas);
		
		JLabel lblTorneos = new JLabel("Torneos:");
		lblTorneos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTorneos.setBounds(10, 45, 61, 14);
		getContentPane().add(lblTorneos);
		
		
		JLabel lblCarreras = new JLabel("Carreras:");
		lblCarreras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCarreras.setBounds(10, 73, 61, 14);
		getContentPane().add(lblCarreras);

		JLabel lblNadadores = new JLabel("Nadadores:");
		lblNadadores.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNadadores.setBounds(10, 136, 61, 14);
		getContentPane().add(lblNadadores);
		
		
		JButton btnAgregarNadador = new JButton("Agregar");
		
		btnAgregarNadador.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				agregarNadador();
			}
		});
		
		btnAgregarNadador.setBounds(410, 133, 89, 20);
		getContentPane().add(btnAgregarNadador);
		
		
		JButton btnQuitarNadador = new JButton("Quitar");
		btnQuitarNadador.setBounds(509, 133, 89, 20);
		getContentPane().add(btnQuitarNadador);
		
		btnQuitarNadador.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				quitarNadador();
			}
		});
		
		JButton btnGenerarSeries = new JButton("Generar Series");
		btnGenerarSeries.setBounds(410, 10, 188, 112);
		getContentPane().add(btnGenerarSeries);
		
		btnGenerarSeries.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				generarSerie();
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 164, 588, 145);
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
	
	private void generarSerie()
	{
		Carrera car = (Carrera)cbCarreras.getSelectedItem();
		if (cc.generarSeriesPorCarrera(car.getNroCarrera()))
			JOptionPane.showMessageDialog(getContentPane(), "Éxito al generar serie(s)");
		else
			JOptionPane.showMessageDialog(getContentPane(), "La carrera debe tener más de 1 nadador");
	}
	
	private void quitarNadador()
	{
		if(table.getSelectedRow() == -1)
			JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un nadador de la grilla para quitarlo");
		else
		{
			Carrera carrera = (Carrera)cbCarreras.getSelectedItem();
			int dni = (int)table.getValueAt(table.getSelectedRow(), 2);
			cc.quitarNadadorDeCarrera(dni ,carrera.getNroCarrera());
			buscarNadadoresYActualizarTabla();
		}
	}
	
	private void agregarNadador()
	{
		Nadador n = (Nadador)cbNadadores.getSelectedItem();
		if (n == null)
			JOptionPane.showMessageDialog(getContentPane(), "No hay nadador para agregar");
		else
		{
			Carrera c = (Carrera)cbCarreras.getSelectedItem();
			cc.cargarNadadorEnCarrera(n.getDni(), c.getNroCarrera());				
			buscarNadadoresYActualizarTabla();
		}
	}
	
	private void buscarNadadoresYActualizarTabla()
	{

		Carrera carrera = (Carrera)cbCarreras.getSelectedItem();
		ArrayList<Nadador> listaNadadores = cc.traerTodosNadadores(carrera.getTipoCarrera(), carrera.getNroCarrera());
		
		modeloNad.removeAllElements();
		for(Nadador nad : listaNadadores)
		{
			modeloNad.addElement(nad);
		}
		cbNadadores.setModel(modeloNad);
		cbNadadores.setRenderer(new PromptComboBoxRenderer("<-Seleccione un Nadador->"));
		cbNadadores.setSelectedIndex(-1);
		
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

	private DefaultComboBoxModel<Programa> generarComboProgramas (ArrayList<Programa> programas)
	{
		DefaultComboBoxModel<Programa> modeloPrg = new DefaultComboBoxModel<Programa>();
		
		for(Programa prg: programas)
		{
			modeloPrg.addElement(prg);
		}
		return modeloPrg;
	}
	
	private void buscarCarrerasPorTorneo(ActionListener alCarrera)
	{

		Torneo tor = (Torneo)cbTorneos.getSelectedItem();		
		ArrayList<Carrera> carrerasPorPrograma = cc.traerCarrerasPorTorneo(tor.getNroTorneo());
		
		cbCarreras.removeActionListener(alCarrera);
		modeloCar.removeAllElements();
		for(Carrera cars: carrerasPorPrograma)
		{
			modeloCar.addElement(cars);
		}
		cbCarreras.setModel(modeloCar);
		cbCarreras.setRenderer(new PromptComboBoxRenderer("<-Seleccione una Carrera->"));
		cbCarreras.setSelectedIndex(-1);
		for (int i = mt.getRowCount()- 1; i >= 0; i--)
		    mt.removeRow(i);
		modeloNad.removeAllElements();
		cbNadadores.setRenderer(new JComboBox<Nadador>().getRenderer());
		cbCarreras.addActionListener(alCarrera);

	}
	
	private void buscarTorneosPorProgramas(ActionListener alCarrera, ActionListener alTorneo)
	{
		for (int i = mt.getRowCount()- 1; i >= 0; i--)
		    mt.removeRow(i);
		
		cbCarreras.removeActionListener(alCarrera);
		modeloCar.removeAllElements();
		cbCarreras.setRenderer(new JComboBox<Carrera>().getRenderer());
		cbCarreras.setSelectedItem(-1);
		cbCarreras.addActionListener(alCarrera);
		
		modeloNad.removeAllElements();
		cbNadadores.setRenderer(new JComboBox<Nadador>().getRenderer());
		cbNadadores.setSelectedItem(-1);
		
		Programa prog = (Programa)cbProgramas.getSelectedItem();
		
		cbTorneos.removeActionListener(alTorneo);
		ArrayList<Torneo> torneosPorPrograma = cc.buscarTorneosPorPrograma(prog.getNroPrograma());
		modeloTor.removeAllElements();
		for(Torneo tors: torneosPorPrograma)
		{
			modeloTor.addElement(tors);
		}
		
		cbTorneos.setModel(modeloTor);		
		cbTorneos.setRenderer(new PromptComboBoxRenderer("<-Seleccione un Torneo->"));
		cbTorneos.setSelectedIndex(-1);
		cbTorneos.addActionListener(alTorneo);
	}
}

//ESTO ES PARA SETEAR UN PRIMER VALOR EN LOS COMBOBOX
class PromptComboBoxRenderer extends BasicComboBoxRenderer
{
	private static final long serialVersionUID = 1L;
	private String prompt;

	public PromptComboBoxRenderer(String prompt)
	{
		this.prompt = prompt;
	}

	public Component getListCellRendererComponent(
		JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value == null)
			setText( prompt );

		return this;
	}
}

