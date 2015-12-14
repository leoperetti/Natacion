package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entidades.Club;
import entidades.Nadador;
import entidades.Programa;
import entidades.Torneo;
import negocio.ControladorCompetencia;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JScrollPane;

public class FrameAdministrarTorneo extends JInternalFrame implements InternalFrameListener {

	private static final long serialVersionUID = 1L;
	private static FrameAdministrarTorneo instancia = null;
	private JTextField txtModificarClubAnfitrion;
	private JTextField txtModificarLugar;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private JTextField txtNroTorneo;
	private JTextField txtProgramaActual;
	private JTextField txtNroTorneoModificar;
	private JTable tablaEliminarModificar;

	public static FrameAdministrarTorneo obtenerInstancia()
	{
		if (instancia == null)
		{
			instancia = new FrameAdministrarTorneo();
		}
		return instancia;
	}

	public static FrameAdministrarTorneo devolverInstancia()
	{
		return instancia;
	}
	
	public FrameAdministrarTorneo() 
	{
        initComponents();
        addInternalFrameListener(this);
	}

	private void initComponents() 
	{
		setTitle("Administrar Torneo");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 0, 496, 367);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "tabbedPane");
		
		JPanel pnlNuevoTorneo = new JPanel();
		tabbedPane.addTab("Nuevo Torneo", null, pnlNuevoTorneo, null);
		pnlNuevoTorneo.setLayout(null);
		
		
		JComboBox<Programa> cbProgramas = new JComboBox<Programa>();
		cbProgramas.setBounds(127, 50, 200, 31);
		pnlNuevoTorneo.add(cbProgramas);
		cbProgramas.setModel(generarModeloComboBoxPrograma(cc.traerLosProgramas()));
		
		JLabel lblProgramas = new JLabel("Programa:");
		lblProgramas.setHorizontalAlignment(SwingConstants.LEFT);
		lblProgramas.setBounds(10, 58, 107, 14);
		pnlNuevoTorneo.add(lblProgramas);
		
		JLabel lblClubAnfitrion = new JLabel("Club anfitri\u00F3n:");
		lblClubAnfitrion.setHorizontalAlignment(SwingConstants.LEFT);
		lblClubAnfitrion.setBounds(10, 100, 107, 14);
		pnlNuevoTorneo.add(lblClubAnfitrion);
		
		JLabel lblFechaQueSe = new JLabel("Fecha que se corre:");
		lblFechaQueSe.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaQueSe.setBounds(10, 142, 107, 14);
		pnlNuevoTorneo.add(lblFechaQueSe);
		

		JFormattedTextField txtFecha = new JFormattedTextField(generarMascara());
		txtFecha.setColumns(10);
		txtFecha.setBounds(127, 134, 200, 31);
		pnlNuevoTorneo.add(txtFecha);
		
		JComboBox<Club> cbClubAnfitrion = new JComboBox<Club>(generarModeloTablaClub(cc.buscarClubes()));
		cbClubAnfitrion.setBounds(127, 92, 200, 31);
		pnlNuevoTorneo.add(cbClubAnfitrion);
		
		JButton btnCargarTorneo = new JButton("Cargar Torneo");
		btnCargarTorneo.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				cargarTorneo(cbProgramas, txtFecha, cbClubAnfitrion);
			}
		});
		btnCargarTorneo.setBounds(243, 267, 222, 31);
		pnlNuevoTorneo.add(btnCargarTorneo);
		
		JLabel lblNroTorneo = new JLabel("N\u00FAmero:");
		lblNroTorneo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNroTorneo.setBounds(10, 18, 107, 14);
		pnlNuevoTorneo.add(lblNroTorneo);
		
		txtNroTorneo = new JTextField();
		txtNroTorneo.setEnabled(false);
		txtNroTorneo.setColumns(10);
		txtNroTorneo.setBounds(127, 11, 200, 28);
		pnlNuevoTorneo.add(txtNroTorneo);
		txtNroTorneo.setText(Integer.toString(cc.buscarUltimoNumeroTorneo() + 1));
				
		JPanel pnlEliminarTorneo = new JPanel();
		tabbedPane.addTab("Modificar/Eliminar Torneo", null, pnlEliminarTorneo, null);
		pnlEliminarTorneo.setLayout(null);
		
		JButton btnEliminarTorneo = new JButton("Eliminar Torneo");
		btnEliminarTorneo.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				eliminarTorneo();
			}
		});
		btnEliminarTorneo.setBounds(243, 267, 222, 31);
		pnlEliminarTorneo.add(btnEliminarTorneo);
		
		JPanel panelModificar = new JPanel();
		getContentPane().add(panelModificar, "panelModificar");
		panelModificar.setLayout(null);
		
		JPanel pnlModificarTorneo = new JPanel();
		pnlModificarTorneo.setBounds(0, 0, 480, 337);
		panelModificar.add(pnlModificarTorneo);
		pnlModificarTorneo.setLayout(null);
		
		JFormattedTextField txtModificarFecha = new JFormattedTextField(generarMascara());
		txtModificarFecha.setColumns(10);
		txtModificarFecha.setBounds(147, 164, 318, 20);
		pnlModificarTorneo.add(txtModificarFecha);
		
		JComboBox<Programa> cbModificarPrograma = new JComboBox<Programa>();
		
		cbModificarPrograma.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Programa prog = (Programa)cbModificarPrograma.getSelectedItem();
				if (prog != null)
				{
					txtProgramaActual.setText(Integer.toString(prog.getNroPrograma()));
				}
			}
		});
		cbModificarPrograma.setBounds(265, 89, 200, 20);
		pnlModificarTorneo.add(cbModificarPrograma);
		
		JButton btnModificarTorneo = new JButton("Modificar Torneo");
		btnModificarTorneo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				modificarTorneo(txtModificarFecha, cbModificarPrograma);
			}
		});
		btnModificarTorneo.setBounds(10, 267, 222, 31);
		pnlEliminarTorneo.add(btnModificarTorneo);
		
		tablaEliminarModificar = new JTable(generarModeloTabla(cc.buscarTorneos()));
		
		JScrollPane scrollPane = new JScrollPane(tablaEliminarModificar);
		scrollPane.setBounds(10, 11, 455, 245);
		pnlEliminarTorneo.add(scrollPane);
				
		JLabel lblModificarTorneo = new JLabel("Torneo:");
		lblModificarTorneo.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarTorneo.setBounds(10, 67, 127, 14);
		pnlModificarTorneo.add(lblModificarTorneo);
		
		JLabel lblModificarClubAnfitrion = new JLabel("Club anfitri\u00F3n:");
		lblModificarClubAnfitrion.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarClubAnfitrion.setBounds(10, 117, 127, 14);
		pnlModificarTorneo.add(lblModificarClubAnfitrion);
		
		txtModificarClubAnfitrion = new JTextField();
		txtModificarClubAnfitrion.setColumns(10);
		txtModificarClubAnfitrion.setBounds(147, 114, 318, 20);
		pnlModificarTorneo.add(txtModificarClubAnfitrion);
		
		txtModificarLugar = new JTextField();
		txtModificarLugar.setColumns(10);
		txtModificarLugar.setBounds(147, 139, 318, 20);
		pnlModificarTorneo.add(txtModificarLugar);
		
		JLabel lblModificarLugar = new JLabel("Se realiza en:");
		lblModificarLugar.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarLugar.setBounds(10, 142, 127, 14);
		pnlModificarTorneo.add(lblModificarLugar);
		
		JLabel lblModificarFecha = new JLabel("Fecha que se corre:");
		lblModificarFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarFecha.setBounds(10, 167, 127, 14);
		pnlModificarTorneo.add(lblModificarFecha);
		
		
		JLabel lblModificarPrograma = new JLabel("Programa:");
		lblModificarPrograma.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarPrograma.setBounds(10, 92, 127, 14);
		pnlModificarTorneo.add(lblModificarPrograma);
		
		
		JButton btnGuardarDatosModificados = new JButton("Guardar Datos");
		btnGuardarDatosModificados.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				guardarModificacion(txtModificarFecha);
			}
		});
		
		btnGuardarDatosModificados.setBounds(243, 295, 222, 31);
		pnlModificarTorneo.add(btnGuardarDatosModificados);
		
		txtProgramaActual = new JTextField();
		txtProgramaActual.setEnabled(false);
		txtProgramaActual.setColumns(10);
		txtProgramaActual.setBounds(147, 89, 108, 20);
		pnlModificarTorneo.add(txtProgramaActual);
		
		txtNroTorneoModificar = new JTextField();
		txtNroTorneoModificar.setEnabled(false);
		txtNroTorneoModificar.setBounds(147, 64, 318, 20);
		pnlModificarTorneo.add(txtNroTorneoModificar);
		txtNroTorneoModificar.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
				cardLayout.show(getContentPane(), "tabbedPane");
			}
		});
		btnVolver.setBounds(10, 295, 222, 31);
		pnlModificarTorneo.add(btnVolver);
	}

	
	private void cargarTorneo(JComboBox<Programa> cbProgramas, JFormattedTextField txtFecha, JComboBox<Club> cbClubes)
	{
		Programa programa = (Programa) cbProgramas.getSelectedItem();
		Club clubSeleccionado = (Club) cbClubes.getSelectedItem();
		cc.cargarTorneo(Integer.parseInt(txtNroTorneo.getText()), programa.getNroPrograma(), clubSeleccionado.getNroClub(), txtFecha.getText());
		JOptionPane.showMessageDialog(getContentPane(), "Torneo cargado.");
		tablaEliminarModificar.setModel(generarModeloTabla(cc.buscarTorneos()));
		txtNroTorneo.setText(Integer.toString(cc.buscarUltimoNumeroTorneo() + 1));
		txtFecha.setText("");
	}
	
	private DefaultTableModel generarModeloTabla(ArrayList<Torneo> listaTorneo)
	{
		DefaultTableModel modeloTabla = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		Object[] identifiers = {"NroTorneo", "Fecha", "Club", "Programa"};
		modeloTabla.setColumnIdentifiers(identifiers);
		for(Torneo tor : listaTorneo)
		{
			Object[] o = new Object[4];
			o[0] = tor.getNroTorneo();
			o[1] = tor.getFecha();
			o[2] = tor.getNroClub();
			o[3] = tor.getNroPrograma();
			modeloTabla.addRow(o);
		}
		return modeloTabla;
		
	}
	
	private DefaultComboBoxModel<Club> generarModeloTablaClub(ArrayList<Club> listaClub)
	{
		DefaultComboBoxModel<Club> modeloCombo = new DefaultComboBoxModel<Club>();
		
		for(Club clu : listaClub)
		{
			modeloCombo.addElement(clu);
		}
		return modeloCombo;
		
	}
	
	private void eliminarTorneo()
	{
		int nroTorneo = (int) tablaEliminarModificar.getValueAt(tablaEliminarModificar.getSelectedRow(), 0);
		if ((JOptionPane.showConfirmDialog(getContentPane(), "Si elimina un torneo los datos del mismo no se podrán volver a recuperar.\nSólo se recomienda usar este botón si se equivocó al cargar por primera vez el torneo.\n¿Está seguro que desea hacerlo?") == JOptionPane.YES_OPTION))
		{
			cc.eliminarTorneo(nroTorneo);
			JOptionPane.showMessageDialog(getContentPane(), "Torneo eliminado.");
			tablaEliminarModificar.setModel(generarModeloTabla(cc.buscarTorneos()));
			txtNroTorneo.setText(Integer.toString(cc.buscarUltimoNumeroTorneo() + 1));
		}
		else
		{
			JOptionPane.showMessageDialog(getContentPane(), "El torneo no se eliminará.");
		}
	}
	
	private void modificarTorneo(JFormattedTextField txtModificarFecha, JComboBox<Programa> cbModificarPrograma)
	{
		int nroTorneo = (int) tablaEliminarModificar.getValueAt(tablaEliminarModificar.getSelectedRow(), 0);
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "panelModificar");
		Torneo torneoActual = cc.buscarTorneosPorNroTorneo(nroTorneo);
		txtNroTorneoModificar.setText(Integer.toString(nroTorneo));
		txtProgramaActual.setText(Integer.toString(torneoActual.getNroPrograma()));
		txtModificarClubAnfitrion.setText(Integer.toString(torneoActual.getNroClub()));
		txtModificarFecha.setText(torneoActual.getFecha());
		cbModificarPrograma.setModel(generarModeloComboBoxPrograma(cc.traerLosProgramas()));
	}
	
	private void guardarModificacion(JFormattedTextField txtModificarFecha)
	{
		cc.modificarTorneo(Integer.parseInt(txtProgramaActual.getText()), txtModificarFecha.getText(), txtModificarClubAnfitrion.getText(), txtModificarLugar.getText(), Integer.parseInt(txtNroTorneoModificar.getText()));
		JOptionPane.showMessageDialog(getContentPane(), "Torneo Modificado.");
		tablaEliminarModificar.setModel(generarModeloTabla(cc.buscarTorneos()));
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "tabbedPane");
	}
	
	private DefaultComboBoxModel<Programa> generarModeloComboBoxPrograma(ArrayList<Programa> programas)
	{
		DefaultComboBoxModel<Programa> modeloPrg = new DefaultComboBoxModel<Programa>();
		for(Programa prg: programas)
		{
			modeloPrg.addElement(prg);
		}
		
		return modeloPrg;
	}
	
	private MaskFormatter generarMascara()
	{
		MaskFormatter mask = null;
        try 
        {
            mask = new MaskFormatter("##/##/####");//the # is for numeric values
            mask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        return mask;
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
