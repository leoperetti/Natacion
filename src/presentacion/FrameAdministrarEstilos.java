package presentacion;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

import entidades.Estilo;
import entidades.Torneo;
import negocio.ControladorCompetencia;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FrameAdministrarEstilos extends JInternalFrame implements InternalFrameListener 
{
	private static FrameAdministrarEstilos instancia = null;
	private JTextField txtNroEstiloNuevo;
	private JTextField txtDescripcionNuevo;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private JTable tablaEstilos;
	
	public static FrameAdministrarEstilos obtenerInstancia()
	{
		if (instancia == null)
		{
			instancia = new FrameAdministrarEstilos();
		}
		return instancia;
	}

	public static FrameAdministrarEstilos devolverInstancia()
	{
		return instancia;
	}
	
	public FrameAdministrarEstilos() 
	{
        initComponents();
        addInternalFrameListener(this);
	}

	private void initComponents() 
	{
		setTitle("Administrar Programas");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 0, 378, 210);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "name_93857737426236");
		
		JPanel pnlNuevoEstilo = new JPanel();
		tabbedPane.addTab("Nuevo Estilo", null, pnlNuevoEstilo, null);
		pnlNuevoEstilo.setLayout(null);
		
		JLabel lblNumeroDeEStiloNuevo = new JLabel("N\u00FAmero de Estilo:");
		lblNumeroDeEStiloNuevo.setBounds(10, 14, 100, 14);
		pnlNuevoEstilo.add(lblNumeroDeEStiloNuevo);
		
		JLabel lblDescripcionNuevo = new JLabel("Descripci\u00F3n:");
		lblDescripcionNuevo.setBounds(10, 39, 100, 14);
		pnlNuevoEstilo.add(lblDescripcionNuevo);
		
		txtNroEstiloNuevo = new JTextField(Integer.toString(cc.buscarUltimoNumeroEstilo() + 1));
		txtNroEstiloNuevo.setEnabled(false);
		txtNroEstiloNuevo.setColumns(10);
		txtNroEstiloNuevo.setBounds(120, 11, 227, 20);
		pnlNuevoEstilo.add(txtNroEstiloNuevo);
		
		txtDescripcionNuevo = new JTextField();
		txtDescripcionNuevo.setColumns(10);
		txtDescripcionNuevo.setBounds(120, 36, 227, 20);
		pnlNuevoEstilo.add(txtDescripcionNuevo);
		
		JButton btnAgregarEstilo = new JButton("Agregar Estilo");
		btnAgregarEstilo.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				cargarEstilo();
			}
		});
		btnAgregarEstilo.setBounds(186, 102, 161, 39);
		pnlNuevoEstilo.add(btnAgregarEstilo);
		
		JPanel pnlEliminarModificarEstilo = new JPanel();
		tabbedPane.addTab("Modificar/Eliminar Estilo", null, pnlEliminarModificarEstilo, null);
		pnlEliminarModificarEstilo.setLayout(null);
		
		tablaEstilos = new JTable(generarModeloTabla(cc.traerLosEstilos()));
		
		JScrollPane spElminarModificarEstilo = new JScrollPane(tablaEstilos);
		spElminarModificarEstilo.setBounds(10, 11, 337, 80);
		pnlEliminarModificarEstilo.add(spElminarModificarEstilo);
		
		JButton btnEliminarEstilo = new JButton("Eliminar Estilo");
		btnEliminarEstilo.setBounds(186, 102, 161, 39);
		pnlEliminarModificarEstilo.add(btnEliminarEstilo);
		
		JButton btnModificarestilo = new JButton("ModificarEstilo");
		btnModificarestilo.setBounds(10, 102, 161, 39);
		pnlEliminarModificarEstilo.add(btnModificarestilo);
		
		JPanel panelModificar = new JPanel();
		getContentPane().add(panelModificar, "name_93916573940825");
		panelModificar.setLayout(null);
	}
	
	private void cargarEstilo()
	{
		cc.cargarEstilo(Integer.parseInt(txtNroEstiloNuevo.getText()), txtDescripcionNuevo.getText());
		JOptionPane.showMessageDialog(getContentPane(), "Estilo cargado.");
		txtNroEstiloNuevo.setText((Integer.toString(cc.buscarUltimoNumeroEstilo() + 1)));
		txtDescripcionNuevo.setText("");
		tablaEstilos.setModel(generarModeloTabla(cc.traerLosEstilos()));
	}
	
	private DefaultTableModel generarModeloTabla(ArrayList<Estilo> listaEstilo)
	{
		DefaultTableModel modeloTabla = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		Object[] identifiers = {"NroEstilo", "Descripción"};
		modeloTabla.setColumnIdentifiers(identifiers);
		for(Estilo est : listaEstilo)
		{
			Object[] o = new Object[2];
			o[0] = est.getNroEstilo();
			o[1] = est.getNombreEstilo();
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
