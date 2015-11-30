package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class FrameAdministrarTorneo extends JInternalFrame implements InternalFrameListener {

	private static final long serialVersionUID = 1L;
	private static FrameAdministrarTorneo instancia = null;
	private JTextField txtClubAnfitrion;
	private JTextField txtLugarDeTorneo;
	private JTextField txtFecha;
	private JTextField txtModificarClubAnfitrion;
	private JTextField txtModificarLugar;
	private JTextField txtModificarFecha;
	private JTable tblEliminarTorneo;

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
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 460, 315);
		getContentPane().add(tabbedPane);
		
		JPanel pnlNuevoTorneo = new JPanel();
		tabbedPane.addTab("Nuevo Torneo", null, pnlNuevoTorneo, null);
		pnlNuevoTorneo.setLayout(null);
		
		JComboBox cbProgramas = new JComboBox();
		cbProgramas.setBounds(127, 11, 200, 20);
		pnlNuevoTorneo.add(cbProgramas);
		
		JLabel lblProgramas = new JLabel("Programa:");
		lblProgramas.setHorizontalAlignment(SwingConstants.LEFT);
		lblProgramas.setBounds(10, 14, 107, 14);
		pnlNuevoTorneo.add(lblProgramas);
		
		JLabel lblClubAnfitrion = new JLabel("Club anfitri\u00F3n:");
		lblClubAnfitrion.setHorizontalAlignment(SwingConstants.LEFT);
		lblClubAnfitrion.setBounds(10, 39, 107, 14);
		pnlNuevoTorneo.add(lblClubAnfitrion);
		
		JLabel lblSeRealizaEn = new JLabel("Se realiza en:");
		lblSeRealizaEn.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeRealizaEn.setBounds(10, 64, 107, 14);
		pnlNuevoTorneo.add(lblSeRealizaEn);
		
		JLabel lblFechaQueSe = new JLabel("Fecha que se corre:");
		lblFechaQueSe.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaQueSe.setBounds(10, 89, 107, 14);
		pnlNuevoTorneo.add(lblFechaQueSe);
		
		txtClubAnfitrion = new JTextField();
		txtClubAnfitrion.setBounds(127, 36, 200, 20);
		pnlNuevoTorneo.add(txtClubAnfitrion);
		txtClubAnfitrion.setColumns(10);
		
		txtLugarDeTorneo = new JTextField();
		txtLugarDeTorneo.setColumns(10);
		txtLugarDeTorneo.setBounds(127, 61, 200, 20);
		pnlNuevoTorneo.add(txtLugarDeTorneo);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(127, 86, 200, 20);
		pnlNuevoTorneo.add(txtFecha);
		
		JButton btnCargarTorneo = new JButton("Cargar Torneo");
		btnCargarTorneo.setBounds(223, 245, 222, 31);
		pnlNuevoTorneo.add(btnCargarTorneo);
		
		JPanel pnlModificarTorneo = new JPanel();
		tabbedPane.addTab("Modificar Torneo", null, pnlModificarTorneo, null);
		pnlModificarTorneo.setLayout(null);
		
		JComboBox cbModificarTorneo = new JComboBox();
		cbModificarTorneo.setBounds(127, 36, 200, 20);
		pnlModificarTorneo.add(cbModificarTorneo);
		
		JLabel lblModificarTorneo = new JLabel("Torneo:");
		lblModificarTorneo.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarTorneo.setBounds(10, 39, 107, 14);
		pnlModificarTorneo.add(lblModificarTorneo);
		
		JLabel lblModificarClubAnfitrion = new JLabel("Club anfitri\u00F3n:");
		lblModificarClubAnfitrion.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarClubAnfitrion.setBounds(10, 64, 107, 14);
		pnlModificarTorneo.add(lblModificarClubAnfitrion);
		
		txtModificarClubAnfitrion = new JTextField();
		txtModificarClubAnfitrion.setColumns(10);
		txtModificarClubAnfitrion.setBounds(127, 61, 200, 20);
		pnlModificarTorneo.add(txtModificarClubAnfitrion);
		
		txtModificarLugar = new JTextField();
		txtModificarLugar.setColumns(10);
		txtModificarLugar.setBounds(127, 86, 200, 20);
		pnlModificarTorneo.add(txtModificarLugar);
		
		JLabel lblModificarLugar = new JLabel("Se realiza en:");
		lblModificarLugar.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarLugar.setBounds(10, 89, 107, 14);
		pnlModificarTorneo.add(lblModificarLugar);
		
		JLabel lblModificarFecha = new JLabel("Fecha que se corre:");
		lblModificarFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarFecha.setBounds(10, 114, 107, 14);
		pnlModificarTorneo.add(lblModificarFecha);
		
		txtModificarFecha = new JTextField();
		txtModificarFecha.setColumns(10);
		txtModificarFecha.setBounds(127, 111, 200, 20);
		pnlModificarTorneo.add(txtModificarFecha);
		
		JLabel lblModificarPrograma = new JLabel("Programa:");
		lblModificarPrograma.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificarPrograma.setBounds(10, 14, 107, 14);
		pnlModificarTorneo.add(lblModificarPrograma);
		
		JComboBox cbModificarPrograma = new JComboBox();
		cbModificarPrograma.setBounds(127, 11, 200, 20);
		pnlModificarTorneo.add(cbModificarPrograma);
		
		JButton btnModificarTorneo = new JButton("Modificar Torneo");
		btnModificarTorneo.setBounds(223, 245, 222, 31);
		pnlModificarTorneo.add(btnModificarTorneo);
		
		JPanel pnlEliminarTorneo = new JPanel();
		tabbedPane.addTab("Eliminar Torneo", null, pnlEliminarTorneo, null);
		pnlEliminarTorneo.setLayout(null);
		
		tblEliminarTorneo = new JTable();
		tblEliminarTorneo.setBounds(10, 10, 435, 224);
		pnlEliminarTorneo.add(tblEliminarTorneo);
		
		JButton btnEliminarTorneo = new JButton("Eliminar Torneo");
		btnEliminarTorneo.setBounds(223, 245, 222, 31);
		pnlEliminarTorneo.add(btnEliminarTorneo);
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
