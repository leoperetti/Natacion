package presentacion;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

import entidades.Carrera;
import entidades.Estilo;
import entidades.Programa;
import entidades.Torneo;
import negocio.ControladorCompetencia;

import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameAdministrarCarrera extends JInternalFrame implements InternalFrameListener
{
	private static FrameAdministrarCarrera instancia = null;
	private JTextField txtNroCarreraNuevo;
	private JTextField txtEdadCompetidoresNuevo;
	private JTextField txtMetrosNuevo;
	private JTextField txtNroCarreraModificar;
	private JTextField txtEdadCompetidoresModificar;
	private JTextField txtMetrosModificar;
	private JTextField txtProgramasModificar;
	private JTextField txtTorneosModificar;
	private JComboBox<Programa> cbProgramasNuevo;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private JComboBox<Torneo> cbTorneosNuevo;
	private JComboBox<Estilo> cbEstilosNuevo;
	private DefaultComboBoxModel<Torneo> modeloTor = new DefaultComboBoxModel<Torneo>();
	private JComboBox cbGeneroNuevo;
	private JTable tablaCarrera;

	
	
	public static FrameAdministrarCarrera obtenerInstancia()
	{
		if (instancia == null)
		{
			instancia = new FrameAdministrarCarrera();
		}
		return instancia;
	}

	public static FrameAdministrarCarrera devolverInstancia()
	{
		return instancia;
	}
	
	public FrameAdministrarCarrera() 
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
		setBounds(10, 0, 542, 275);
		
        getContentPane().setLayout(new CardLayout(0, 0));
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, "tabbedPane");
        
        JPanel panelNuevo = new JPanel();
        tabbedPane.addTab("Nueva Carrera", null, panelNuevo, null);
        panelNuevo.setLayout(null);
        
        JLabel lblNroCarreraNuevo = new JLabel("N\u00FAmero de Carrera:");
        lblNroCarreraNuevo.setBounds(10, 93, 138, 14);
        panelNuevo.add(lblNroCarreraNuevo);
        
        txtNroCarreraNuevo = new JTextField(Integer.toString(cc.buscarUltimoNumeroCarrera() + 1));
        txtNroCarreraNuevo.setEnabled(false);
        txtNroCarreraNuevo.setColumns(10);
        txtNroCarreraNuevo.setBounds(175, 87, 171, 20);
        panelNuevo.add(txtNroCarreraNuevo);
        
        JLabel lblEdadCompetidoresNuevo = new JLabel("Edad de los competidores:");
        lblEdadCompetidoresNuevo.setBounds(10, 118, 138, 14);
        panelNuevo.add(lblEdadCompetidoresNuevo);
        
        txtEdadCompetidoresNuevo = new JTextField();
        txtEdadCompetidoresNuevo.setColumns(10);
        txtEdadCompetidoresNuevo.setBounds(175, 112, 171, 20);
        panelNuevo.add(txtEdadCompetidoresNuevo);
        
        JLabel lblMetrosNuevo = new JLabel("Metros:");
        lblMetrosNuevo.setBounds(10, 143, 138, 14);
        panelNuevo.add(lblMetrosNuevo);
        
        txtMetrosNuevo = new JTextField();
        txtMetrosNuevo.setColumns(10);
        txtMetrosNuevo.setBounds(175, 137, 171, 20);
        panelNuevo.add(txtMetrosNuevo);
        
        JLabel lblGeneroNuevo = new JLabel("G\u00E9nero:");
        lblGeneroNuevo.setBounds(10, 167, 138, 14);
        panelNuevo.add(lblGeneroNuevo);
        
        cbProgramasNuevo = new JComboBox<Programa>();
        cbProgramasNuevo.setModel(generarComboProgramas(cc.traerLosProgramas()));
        cbProgramasNuevo.setRenderer(new PromptComboBoxRenderer("<-Seleccione un Programa->"));
        cbProgramasNuevo.setSelectedIndex(-1);
        cbProgramasNuevo.setBounds(175, 11, 336, 20);
        panelNuevo.add(cbProgramasNuevo);
        ActionListener alPrograma = new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				buscarTorneosPorProgramas();;				
			}
		};
		
		cbProgramasNuevo.addActionListener(alPrograma);
        
        Object[] opciones = {"Masculino", "Femenino"};
        
        cbGeneroNuevo = new JComboBox(opciones);
        cbGeneroNuevo.setBounds(175, 161, 171, 20);
        panelNuevo.add(cbGeneroNuevo);

        
        cbTorneosNuevo = new JComboBox<Torneo>();
        cbTorneosNuevo.setBounds(175, 39, 336, 20);
        panelNuevo.add(cbTorneosNuevo);
        
        DefaultComboBoxModel<Estilo> modeloEstilo = new DefaultComboBoxModel<Estilo>();
        
		for(Estilo est: cc.traerLosEstilos())
		{
			modeloEstilo.addElement(est);
		}
		
        cbEstilosNuevo = new JComboBox<Estilo>(modeloEstilo);
        cbEstilosNuevo.setBounds(175, 186, 171, 20);
        panelNuevo.add(cbEstilosNuevo);
        
        JLabel lblEstiloNuevo = new JLabel("Estilo:");
        lblEstiloNuevo.setBounds(10, 192, 138, 14);
        panelNuevo.add(lblEstiloNuevo);
        
        JLabel lblProgramaNuevo = new JLabel("Programas:");
        lblProgramaNuevo.setBounds(10, 14, 138, 14);
        panelNuevo.add(lblProgramaNuevo);
        
        JLabel lblTorneoNuevo = new JLabel("Torneos:");
        lblTorneoNuevo.setBounds(10, 42, 138, 14);
        panelNuevo.add(lblTorneoNuevo);
        
        JButton btnAgregarCarrera = new JButton("Agregar Carrera");
        btnAgregarCarrera.addMouseListener(new MouseAdapter()
        {
        	@Override
        	public void mouseReleased(MouseEvent arg0)
        	{
        		cargarCarrera();
        	}
        });
        btnAgregarCarrera.setBounds(356, 87, 155, 121);
        panelNuevo.add(btnAgregarCarrera);
        
        JPanel pnlEliminarModificar = new JPanel();
        tabbedPane.addTab("Modificar/Eliminar Carrera", null, pnlEliminarModificar, null);
        pnlEliminarModificar.setLayout(null);
        
        tablaCarrera = new JTable(generarModeloTabla(cc.buscarCarreras()));
        
        JScrollPane scrollPane = new JScrollPane(tablaCarrera);
        scrollPane.setBounds(10, 11, 501, 150);
        pnlEliminarModificar.add(scrollPane);
        
        JButton btnModificarCarrera = new JButton("Modificar Carrera");
        btnModificarCarrera.setBounds(10, 172, 226, 34);
        pnlEliminarModificar.add(btnModificarCarrera);
        
        JButton btnEliminarCarrera = new JButton("Eliminar Carrera");
        btnEliminarCarrera.setBounds(285, 172, 226, 34);
        pnlEliminarModificar.add(btnEliminarCarrera);
        
        JPanel panelModificar = new JPanel();
        getContentPane().add(panelModificar, "name_933343693797");
        panelModificar.setLayout(null);
        
        JLabel lblProgramasModificar = new JLabel("Programas:");
        lblProgramasModificar.setBounds(10, 26, 138, 14);
        panelModificar.add(lblProgramasModificar);
        
        JLabel label_1 = new JLabel("Torneos:");
        label_1.setBounds(10, 54, 138, 14);
        panelModificar.add(label_1);
        
        JComboBox cbPogramasModificar = new JComboBox();
        cbPogramasModificar.setBounds(260, 23, 251, 20);
        panelModificar.add(cbPogramasModificar);
        
        JComboBox cbTorneosModificar = new JComboBox();
        cbTorneosModificar.setBounds(260, 51, 251, 20);
        panelModificar.add(cbTorneosModificar);
        
        JLabel lblNroCarreraModificar = new JLabel("N\u00FAmero de Carrera:");
        lblNroCarreraModificar.setBounds(10, 105, 138, 14);
        panelModificar.add(lblNroCarreraModificar);
        
        txtNroCarreraModificar = new JTextField();
        txtNroCarreraModificar.setEnabled(false);
        txtNroCarreraModificar.setColumns(10);
        txtNroCarreraModificar.setBounds(175, 99, 171, 20);
        panelModificar.add(txtNroCarreraModificar);
        
        JLabel lblEdadCompetidoresModificar = new JLabel("Edad de los competidores:");
        lblEdadCompetidoresModificar.setBounds(10, 130, 138, 14);
        panelModificar.add(lblEdadCompetidoresModificar);
        
        txtEdadCompetidoresModificar = new JTextField();
        txtEdadCompetidoresModificar.setColumns(10);
        txtEdadCompetidoresModificar.setBounds(175, 124, 171, 20);
        panelModificar.add(txtEdadCompetidoresModificar);
        
        JLabel lblMetrosModificar = new JLabel("Metros:");
        lblMetrosModificar.setBounds(10, 155, 138, 14);
        panelModificar.add(lblMetrosModificar);
        
        txtMetrosModificar = new JTextField();
        txtMetrosModificar.setColumns(10);
        txtMetrosModificar.setBounds(175, 149, 171, 20);
        panelModificar.add(txtMetrosModificar);
        
        JLabel lblGeneroModificar = new JLabel("G\u00E9nero:");
        lblGeneroModificar.setBounds(10, 179, 138, 14);
        panelModificar.add(lblGeneroModificar);
        
        JLabel lblEstiloModificar = new JLabel("Estilo:");
        lblEstiloModificar.setBounds(10, 204, 138, 14);
        panelModificar.add(lblEstiloModificar);
        
        JComboBox<Object> txtEstiloModificar = new JComboBox<Object>(new Object[]{});
        txtEstiloModificar.setBounds(175, 198, 171, 20);
        panelModificar.add(txtEstiloModificar);
        
        JComboBox<Object> cbGeneroModificar = new JComboBox<Object>(new Object[]{});
        cbGeneroModificar.setBounds(175, 173, 171, 20);
        panelModificar.add(cbGeneroModificar);
        
        JButton btnGuardarDatos = new JButton("Guardar Datos");
        btnGuardarDatos.setBounds(356, 99, 155, 121);
        panelModificar.add(btnGuardarDatos);
        
        txtProgramasModificar = new JTextField();
        txtProgramasModificar.setEnabled(false);
        txtProgramasModificar.setBounds(175, 23, 75, 20);
        panelModificar.add(txtProgramasModificar);
        txtProgramasModificar.setColumns(10);
        
        txtTorneosModificar = new JTextField();
        txtTorneosModificar.setEnabled(false);
        txtTorneosModificar.setColumns(10);
        txtTorneosModificar.setBounds(175, 51, 75, 20);
        panelModificar.add(txtTorneosModificar);
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
	
	private void cargarCarrera()
	{
		Estilo estiloActual = (Estilo)cbEstilosNuevo.getSelectedItem();
		Torneo torneoActual = (Torneo)cbTorneosNuevo.getSelectedItem();
		char sexo;
		if(cbGeneroNuevo.getSelectedIndex() == 0)
			sexo = 'm';
		else
			sexo = 'f';
		cc.cargarCarrera(estiloActual.getNroEstilo() ,Integer.parseInt(txtNroCarreraNuevo.getText()), Integer.parseInt(txtEdadCompetidoresNuevo.getText()), Integer.parseInt(txtMetrosNuevo.getText()), sexo, torneoActual.getNroTorneo());
		JOptionPane.showMessageDialog(getContentPane(), "Carrera cargada.");
		txtNroCarreraNuevo.setText(Integer.toString(cc.buscarUltimoNumeroCarrera()));
	}
	
	private void buscarTorneosPorProgramas()
	{
		
		Programa prog = (Programa)cbProgramasNuevo.getSelectedItem();
		
		ArrayList<Torneo> torneosPorPrograma = cc.buscarTorneosPorPrograma(prog.getNroPrograma());
		modeloTor.removeAllElements();
		for(Torneo tors: torneosPorPrograma)
		{
			modeloTor.addElement(tors);
		}
		
		cbTorneosNuevo.setModel(modeloTor);		
		cbTorneosNuevo.setRenderer(new PromptComboBoxRenderer("<-Seleccione un Torneo->"));
		cbTorneosNuevo.setSelectedIndex(-1);
	}
	
	private DefaultTableModel generarModeloTabla(ArrayList<Carrera> listaCarrera)
	{
		DefaultTableModel modeloTabla = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		Object[] identifiers = {"NroCarrera", "Edad", "Metros", "Sexo", "Torneo", "Estilo"};
		modeloTabla.setColumnIdentifiers(identifiers);
		for(Carrera car : listaCarrera)
		{
			Object[] o = new Object[6];
			o[0] = car.getNroCarrera();
			o[1] = car.getTipoCarrera();
			o[2] = car.getMetros();
			o[3] = car.getSexo();
			o[4] = car.getNroTorneo();//Acá se debería hacer inner join y buscar el nombre
			o[5] = car.getNroEstilo();//Acá igual
			modeloTabla.addRow(o);
		}
		return modeloTabla;
		
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
}
