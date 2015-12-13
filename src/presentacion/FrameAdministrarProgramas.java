package presentacion;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

import entidades.Programa;
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

public class FrameAdministrarProgramas extends JInternalFrame implements InternalFrameListener  {

	private static FrameAdministrarProgramas instancia = null;
	private JTextField txtNroProgramaNuevo;
	private JTextField txtDescripcionNuevo;
	private JTextField txtNroProgramaModificar;
	private JTextField txtDescripcionModificar;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private JTable tablaProgramas;
	
	public static FrameAdministrarProgramas obtenerInstancia()
	{
		if (instancia == null)
		{
			instancia = new FrameAdministrarProgramas();
		}
		return instancia;
	}

	public static FrameAdministrarProgramas devolverInstancia()
	{
		return instancia;
	}
	
	public FrameAdministrarProgramas() 
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
		getContentPane().add(tabbedPane, "tabbedPane");
		
		JPanel pnlNuevoPrograma = new JPanel();
		tabbedPane.addTab("Nuevo Programa", null, pnlNuevoPrograma, null);
		pnlNuevoPrograma.setLayout(null);
		
		JLabel lblNmeroDeProgramaNuevo = new JLabel("N\u00FAmero de Programa:");
		lblNmeroDeProgramaNuevo.setBounds(10, 11, 117, 14);
		pnlNuevoPrograma.add(lblNmeroDeProgramaNuevo);
		
		JLabel lblDescripcionNuevo = new JLabel("Descripci\u00F3n:");
		lblDescripcionNuevo.setBounds(10, 36, 117, 14);
		pnlNuevoPrograma.add(lblDescripcionNuevo);
		
		txtNroProgramaNuevo = new JTextField(Integer.toString(cc.buscarUltimoNumeroPrograma() + 1));
		txtNroProgramaNuevo.setEnabled(false);
		txtNroProgramaNuevo.setBounds(137, 8, 210, 20);
		pnlNuevoPrograma.add(txtNroProgramaNuevo);
		txtNroProgramaNuevo.setColumns(10);
		
		txtDescripcionNuevo = new JTextField();
		txtDescripcionNuevo.setColumns(10);
		txtDescripcionNuevo.setBounds(137, 33, 210, 20);
		pnlNuevoPrograma.add(txtDescripcionNuevo);
		
		JButton btnAgregarPrograma = new JButton("Agregar Programa");
		btnAgregarPrograma.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				cargarPrograma();
			}
		});
		btnAgregarPrograma.setBounds(186, 102, 161, 39);
		pnlNuevoPrograma.add(btnAgregarPrograma);
		
		JPanel pnlEliminarModificarPrograma = new JPanel();
		tabbedPane.addTab("Modificar/Eliminar Programa", null, pnlEliminarModificarPrograma, null);
		pnlEliminarModificarPrograma.setLayout(null);
		
		JButton btnEliminarPrograma = new JButton("Eliminar Programa");
		
		btnEliminarPrograma.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				eliminarPrograma();
			}
		});
		btnEliminarPrograma.setBounds(186, 102, 161, 39);
		pnlEliminarModificarPrograma.add(btnEliminarPrograma);
		
		JButton btnModificarPrograma = new JButton("Modificar Programa");
		btnModificarPrograma.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				modificarPrograma();
			}
		});
		btnModificarPrograma.setBounds(10, 102, 161, 39);
		pnlEliminarModificarPrograma.add(btnModificarPrograma);
		
		tablaProgramas = new JTable(generarModeloTabla(cc.traerLosProgramas()));
	
		JScrollPane spProgramas = new JScrollPane(tablaProgramas);
		spProgramas.setBounds(10, 11, 337, 80);
		pnlEliminarModificarPrograma.add(spProgramas);
		
		JPanel panelModificar = new JPanel();
		getContentPane().add(panelModificar, "panelModificar");
		panelModificar.setLayout(null);
		
		JLabel lblNroProgramaModificar = new JLabel("N\u00FAmero de Programa:");
		lblNroProgramaModificar.setBounds(10, 14, 117, 14);
		panelModificar.add(lblNroProgramaModificar);
		
		JLabel lblDescripcionModificar = new JLabel("Descripci\u00F3n:");
		lblDescripcionModificar.setBounds(10, 39, 117, 14);
		panelModificar.add(lblDescripcionModificar);
		
		txtNroProgramaModificar = new JTextField();
		txtNroProgramaModificar.setEnabled(false);
		txtNroProgramaModificar.setColumns(10);
		txtNroProgramaModificar.setBounds(137, 11, 215, 20);
		panelModificar.add(txtNroProgramaModificar);
		
		txtDescripcionModificar = new JTextField();
		txtDescripcionModificar.setColumns(10);
		txtDescripcionModificar.setBounds(137, 36, 215, 20);
		panelModificar.add(txtDescripcionModificar);
		
		JButton btnGuardarDatosModificados = new JButton("GuardarDatos");
		btnGuardarDatosModificados.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				guardarModificacion();
			}
		});
		btnGuardarDatosModificados.setBounds(191, 130, 161, 39);
		panelModificar.add(btnGuardarDatosModificados);
	}
	
	private void modificarPrograma()
	{
		int nroProgramaActual = (int)tablaProgramas.getValueAt(tablaProgramas.getSelectedRow(), 0);		
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "panelModificar");
		Programa programaActual = cc.buscarProgramaPorNroPrograma(nroProgramaActual);
		txtNroProgramaModificar.setText(Integer.toString(nroProgramaActual));
		txtDescripcionModificar.setText(programaActual.getDescripcion());
	}
	
	private void cargarPrograma()
	{
		cc.cargarPrograma(Integer.parseInt(txtNroProgramaNuevo.getText()), txtDescripcionNuevo.getText());
		JOptionPane.showMessageDialog(getContentPane(), "Programa cargado.");
		txtNroProgramaNuevo.setText(Integer.toString(cc.buscarUltimoNumeroPrograma() + 1));
		txtDescripcionNuevo.setText("");
		tablaProgramas.setModel(generarModeloTabla(cc.traerLosProgramas()));
	}
	
	private void eliminarPrograma()
	{
		int nroProgramaActual = (int)tablaProgramas.getValueAt(tablaProgramas.getSelectedRow(), 0);
		if ((JOptionPane.showConfirmDialog(getContentPane(), "Si elimina un programa los datos del mismo no se podrán volver a recuperar.\nSólo se recomienda usar este botón si se equivocó al cargar por primera vez el programa.\n¿Está seguro que desea hacerlo?") == JOptionPane.YES_OPTION))
		{
			cc.eliminarPrograma(nroProgramaActual);
			JOptionPane.showMessageDialog(getContentPane(), "Programa eliminado");
			tablaProgramas.setModel(generarModeloTabla(cc.traerLosProgramas()));
			txtNroProgramaNuevo.setText(Integer.toString(cc.buscarUltimoNumeroPrograma() + 1));
		}
		else
		{
			JOptionPane.showMessageDialog(getContentPane(), "El programa no se eliminará");
		}
	}
	
	private DefaultTableModel generarModeloTabla(ArrayList<Programa> listaProgramas)
	{
		DefaultTableModel modeloTabla = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		Object[] identifiers = {"NroPrograma", "Descripción"};
		modeloTabla.setColumnIdentifiers(identifiers);
		for(Programa pro : listaProgramas)
		{
			Object[] o = new Object[2];
			o[0] = pro.getNroPrograma();
			o[1] = pro.getDescripcion();
			modeloTabla.addRow(o);
		}
		return modeloTabla;
		
	}	
	
	private void guardarModificacion()
	{
		cc.modificarProgrma(txtDescripcionModificar.getText(), Integer.parseInt(txtNroProgramaModificar.getText()));
		JOptionPane.showMessageDialog(getContentPane(), "Torneo Modificado.");
		tablaProgramas.setModel(generarModeloTabla(cc.traerLosProgramas()));
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "tabbedPane");
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
