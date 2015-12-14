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
	private JTextField txtModificarNroEstilo;
	private JTextField txtModificarDescripcion;
	
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
		setTitle("Administrar Estilos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 0, 378, 210);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "tabbedPane");
		
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
		btnEliminarEstilo.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				eliminarEstilo();
			}
		});
		btnEliminarEstilo.setBounds(186, 102, 161, 39);
		pnlEliminarModificarEstilo.add(btnEliminarEstilo);
		
		JButton btnModificarestilo = new JButton("ModificarEstilo");
		btnModificarestilo.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				cargarPanelModificarEstilo();
			}
		});
		btnModificarestilo.setBounds(10, 102, 161, 39);
		pnlEliminarModificarEstilo.add(btnModificarestilo);
		
		JPanel panelModificar = new JPanel();
		getContentPane().add(panelModificar, "panelModificar");
		panelModificar.setLayout(null);
		
		JLabel label = new JLabel("N\u00FAmero de Estilo:");
		label.setBounds(10, 26, 100, 14);
		panelModificar.add(label);
		
		JLabel label_1 = new JLabel("Descripci\u00F3n:");
		label_1.setBounds(10, 51, 100, 14);
		panelModificar.add(label_1);
		
		txtModificarNroEstilo = new JTextField("1");
		txtModificarNroEstilo.setEnabled(false);
		txtModificarNroEstilo.setColumns(10);
		txtModificarNroEstilo.setBounds(120, 23, 227, 20);
		panelModificar.add(txtModificarNroEstilo);
		
		txtModificarDescripcion = new JTextField();
		txtModificarDescripcion.setColumns(10);
		txtModificarDescripcion.setBounds(120, 48, 227, 20);
		panelModificar.add(txtModificarDescripcion);
		
		JButton btnGuardarEstilo = new JButton("Guardar Estilo");
		btnGuardarEstilo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				modificarEstilo();
			}
		});
		btnGuardarEstilo.setBounds(191, 130, 161, 39);
		panelModificar.add(btnGuardarEstilo);
	}
	
	private void cargarEstilo()
	{
		cc.cargarEstilo(Integer.parseInt(txtNroEstiloNuevo.getText()), txtDescripcionNuevo.getText());
		JOptionPane.showMessageDialog(getContentPane(), "Estilo cargado.");
		txtNroEstiloNuevo.setText((Integer.toString(cc.buscarUltimoNumeroEstilo() + 1)));
		txtDescripcionNuevo.setText("");
		tablaEstilos.setModel(generarModeloTabla(cc.traerLosEstilos()));
	}
	
	private void eliminarEstilo()
	{
		int nroEstiloActual = (int)tablaEstilos.getValueAt(tablaEstilos.getSelectedRow(), 0);
		if ((JOptionPane.showConfirmDialog(getContentPane(), "Si elimina un estilo los datos del mismo no se podrán volver a recuperar.\nSólo se recomienda usar este botón si se equivocó al cargar por primera vez el estilo.\n¿Está seguro que desea hacerlo?") == JOptionPane.YES_OPTION))
		{
			cc.eliminarEstilo(nroEstiloActual);
			JOptionPane.showMessageDialog(getContentPane(), "Estilo eliminado");
			tablaEstilos.setModel(generarModeloTabla(cc.traerLosEstilos()));
			txtNroEstiloNuevo.setText(Integer.toString(cc.buscarUltimoNumeroEstilo() + 1));
		}
		else
		{
			JOptionPane.showMessageDialog(getContentPane(), "El estilo no se eliminará");
		}
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
	
	private void cargarPanelModificarEstilo()
	{
		int nroEstiloActual = (int)tablaEstilos.getValueAt(tablaEstilos.getSelectedRow(), 0);		
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "panelModificar");
		Estilo estiloActual = cc.buscarEstiloPorNroEstilo(nroEstiloActual);
		txtModificarDescripcion.setText(estiloActual.getNombreEstilo());
		txtModificarNroEstilo.setText(Integer.toString(estiloActual.getNroEstilo()));
		tablaEstilos.setModel(generarModeloTabla(cc.traerLosEstilos()));
	}
	
	private void modificarEstilo()
	{
		cc.modificarEstilo(Integer.parseInt(txtModificarNroEstilo.getText()), txtModificarDescripcion.getText());
		JOptionPane.showMessageDialog(getContentPane(), "Estilo modificado.");
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "tabbedPane");
		tablaEstilos.setModel(generarModeloTabla(cc.traerLosEstilos()));
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
