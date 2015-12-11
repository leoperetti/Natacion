package presentacion;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.sqlite.util.StringUtils;

import entidades.Nadador;
import negocio.ControladorCompetencia;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class FrameAdministrarNadador extends JInternalFrame implements InternalFrameListener
{

	private static final long serialVersionUID = 1L;
	private static FrameAdministrarNadador instancia = null;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private JTextField txtNuevoNombre;
	private JTextField txtNuevoApellido;
	private JTextField txtNuevoClub;
	private JTextField txtModificaNombre;
	private JTextField txtModificaApellido;
	private JTextField txtModificaClub;
	private JTable tblEliminarNadador;
	private JTextField txtBuscar;
	private JTextField txtBuscarE;
	
	public FrameAdministrarNadador() 
	{
        initComponents();
        addInternalFrameListener(this);
    }
	
	public static FrameAdministrarNadador obtenerInstancia()
	{
		if (instancia == null)
		{
			instancia = new FrameAdministrarNadador();
		}
		return instancia;
	}
	
	public static FrameAdministrarNadador devolverInstancia()
	{
		return instancia;
	}

	public void initComponents() 
	{
		setTitle("Administrar Nadador");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setBounds(27, 11, 542, 362);
		this.getContentPane().setLayout(null);
		
		JTabbedPane tbPnlAdministrarNadadores = new JTabbedPane(JTabbedPane.TOP);
		tbPnlAdministrarNadadores.setBounds(10, 11, 506, 310);
		getContentPane().add(tbPnlAdministrarNadadores);
		
		JPanel pnlAgregarNadador = new JPanel();
		tbPnlAdministrarNadadores.addTab("Nuevo Nadador", null, pnlAgregarNadador, null);
		pnlAgregarNadador.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 36, 79, 14);
		pnlAgregarNadador.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 61, 79, 14);
		pnlAgregarNadador.add(lblApellido);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(10, 86, 79, 14);
		pnlAgregarNadador.add(lblEdad);
		
		JLabel lblClub = new JLabel("Club:");
		lblClub.setBounds(10, 111, 79, 14);
		pnlAgregarNadador.add(lblClub);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 11, 79, 14);
		pnlAgregarNadador.add(lblDni);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(10, 136, 79, 14);
		pnlAgregarNadador.add(lblSexo);
		
		JLabel lblTiempo1 = new JLabel("Tiempo 1:");
		lblTiempo1.setBounds(10, 161, 79, 14);
		pnlAgregarNadador.add(lblTiempo1);
		
		JLabel lblTiempo2 = new JLabel("Tiempo 2:");
		lblTiempo2.setBounds(10, 186, 79, 14);
		pnlAgregarNadador.add(lblTiempo2);
		
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setCommitsOnValidEdit(true);
		
		
		JFormattedTextField txtNuevoDni = new JFormattedTextField(formatter);
		txtNuevoDni.setBounds(95, 8, 200, 20);
		pnlAgregarNadador.add(txtNuevoDni);
		txtNuevoDni.setColumns(10);
		
		txtNuevoNombre = new JTextField();
		txtNuevoNombre.setColumns(10);
		txtNuevoNombre.setBounds(95, 33, 200, 20);
		pnlAgregarNadador.add(txtNuevoNombre);
		
		txtNuevoApellido = new JTextField();
		txtNuevoApellido.setColumns(10);
		txtNuevoApellido.setBounds(95, 58, 200, 20);
		pnlAgregarNadador.add(txtNuevoApellido);
		
		JFormattedTextField txtNuevoEdad = new JFormattedTextField(formatter);
		txtNuevoEdad.setColumns(10);
		txtNuevoEdad.setBounds(95, 83, 200, 20);
		pnlAgregarNadador.add(txtNuevoEdad);
		
		txtNuevoClub = new JTextField();
		txtNuevoClub.setColumns(10);
		txtNuevoClub.setBounds(95, 108, 200, 20);
		pnlAgregarNadador.add(txtNuevoClub);
		
		
		Object[] opciones = {"Masculino", "Femenino"};
		JComboBox cbNuevoSexo = new JComboBox(opciones);
		cbNuevoSexo.setBounds(95, 133, 200, 20);
		pnlAgregarNadador.add(cbNuevoSexo);
		
		//Creo una mascara para que no se equivoque el usuario al ingresar el tiempo
		MaskFormatter mask = null;
        try 
        {
            mask = new MaskFormatter("##:##:##");//the # is for numeric values
            mask.setPlaceholderCharacter('0');
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
		
		JFormattedTextField txtNuevoTiempo1 = new JFormattedTextField(mask);
		txtNuevoTiempo1.setBounds(95, 158, 200, 20);
		pnlAgregarNadador.add(txtNuevoTiempo1);
		
		JFormattedTextField txtNuevoTiempo2 = new JFormattedTextField(mask);
		txtNuevoTiempo2.setBounds(95, 183, 200, 20);
		pnlAgregarNadador.add(txtNuevoTiempo2);
		
		JButton btnCargarJugador = new JButton("Cargar Nadador");
		btnCargarJugador.setBounds(269, 240, 222, 31);
		pnlAgregarNadador.add(btnCargarJugador);
		btnCargarJugador.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				char sexo;
				
				if(!(txtNuevoNombre.getText().isEmpty() && txtNuevoApellido.getText().isEmpty() && 
					txtNuevoEdad.getText().isEmpty() && txtNuevoClub.getText().isEmpty()
					&& txtNuevoDni.getText().isEmpty()))
				{
					
					if(cbNuevoSexo.getSelectedIndex()==0)
					{
						sexo = 'm';
					}
					else
					{
						sexo = 'f';
					}
					int dni = (int)txtNuevoDni.getValue();
					String nombre = txtNuevoNombre.getText();
					String apellido = txtNuevoApellido.getText();
					String club = txtNuevoClub.getText();
					int edad = (int)txtNuevoEdad.getValue();
					
					cc.cargarNadador(dni, nombre, apellido, club, edad, txtNuevoTiempo1.getText(), txtNuevoTiempo2.getText(), sexo);
					JOptionPane.showMessageDialog(getContentPane(), "Nadador cargado correctamente!");
					
					
					DefaultTableModel modeloTabla = new DefaultTableModel()
					{
						private static final long serialVersionUID = 1L;
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					};
					
					ArrayList<Nadador> listaNadadores = cc.buscarTodosNadadores();
					Object[] identifiers = {"Nombre", "Apellido", "Dni"};
					modeloTabla.setColumnIdentifiers(identifiers);
					for(Nadador nad : listaNadadores)
					{
						Object[] o = new Object[3];
						o[0] = nad.getNombre();
						o[1] = nad.getApellido();
						o[2] = nad.getDni();
						modeloTabla.addRow(o);
					}
					tblEliminarNadador.setModel(modeloTabla);
				}
				else
				{
					JOptionPane.showMessageDialog(getContentPane(), "Error, hay un campo en blanco");
				
				}
			}
		});
		
		
		JPanel pnlModificarNadador = new JPanel();
		tbPnlAdministrarNadadores.addTab("Modificar Nadador", null, pnlModificarNadador, null);
		pnlModificarNadador.setLayout(null);
		
		JLabel label = new JLabel("DNI:");
		label.setBounds(10, 11, 79, 14);
		pnlModificarNadador.add(label);
		
		JFormattedTextField txtModificaDni = new JFormattedTextField(formatter);
		txtModificaDni.setEditable(false);
		txtModificaDni.setColumns(10);
		txtModificaDni.setBounds(95, 8, 200, 20);
		pnlModificarNadador.add(txtModificaDni);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setBounds(10, 36, 79, 14);
		pnlModificarNadador.add(label_1);
		
		txtModificaNombre = new JTextField();
		txtModificaNombre.setColumns(10);
		txtModificaNombre.setBounds(95, 33, 200, 20);
		pnlModificarNadador.add(txtModificaNombre);
		
		JLabel label_2 = new JLabel("Apellido:");
		label_2.setBounds(10, 61, 79, 14);
		pnlModificarNadador.add(label_2);
		
		txtModificaApellido = new JTextField();
		txtModificaApellido.setColumns(10);
		txtModificaApellido.setBounds(95, 58, 200, 20);
		pnlModificarNadador.add(txtModificaApellido);
		
		JLabel label_3 = new JLabel("Edad:");
		label_3.setBounds(10, 86, 79, 14);
		pnlModificarNadador.add(label_3);
		
		JFormattedTextField txtModificaEdad = new JFormattedTextField(formatter);
		txtModificaEdad.setColumns(10);
		txtModificaEdad.setBounds(95, 83, 200, 20);
		pnlModificarNadador.add(txtModificaEdad);
		
		JLabel label_4 = new JLabel("Club:");
		label_4.setBounds(10, 111, 79, 14);
		pnlModificarNadador.add(label_4);
		
		txtModificaClub = new JTextField();
		txtModificaClub.setColumns(10);
		txtModificaClub.setBounds(95, 108, 200, 20);
		pnlModificarNadador.add(txtModificaClub);
		
		JLabel label_5 = new JLabel("Sexo:");
		label_5.setBounds(10, 136, 79, 14);
		pnlModificarNadador.add(label_5);
		
		JComboBox cbModificaSexo = new JComboBox(opciones);
		cbModificaSexo.setBounds(95, 133, 200, 20);
		pnlModificarNadador.add(cbModificaSexo);
		
		JLabel label_6 = new JLabel("Tiempo 1:");
		label_6.setBounds(10, 161, 79, 14);
		pnlModificarNadador.add(label_6);
		
		JFormattedTextField txtModificaTiempo1 = new JFormattedTextField(mask);
		txtModificaTiempo1.setBounds(95, 158, 200, 20);
		pnlModificarNadador.add(txtModificaTiempo1);
		
		JLabel label_7 = new JLabel("Tiempo 2:");
		label_7.setBounds(10, 186, 79, 14);
		pnlModificarNadador.add(label_7);
		
		JFormattedTextField txtModificaTiempo2 = new JFormattedTextField(mask);
		txtModificaTiempo2.setBounds(95, 183, 200, 20);
		pnlModificarNadador.add(txtModificaTiempo2);
		
		JButton btnModificarJugador = new JButton("Modificar Jugador");
		btnModificarJugador.setBounds(269, 240, 222, 31);
		pnlModificarNadador.add(btnModificarJugador);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(305, 11, 51, 14);
		pnlModificarNadador.add(lblBuscar);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				if (!txtBuscar.getText().isEmpty())
				{
				if (isNumeric(txtBuscar.getText()))
				{
					Nadador n = cc.buscarNadadorPorDni(Integer.parseInt(txtBuscar.getText()));
					txtModificaDni.setText(Integer.toString(n.getDni()));
					txtModificaEdad.setText(Integer.toString(n.getEdad()));
					txtModificaTiempo1.setText(n.getTiempoPreCompetencia1());
					txtModificaTiempo2.setText(n.getTiempoPreCompetencia2());
					txtModificaNombre.setText(n.getNombre());
					txtModificaApellido.setText(n.getApellido());
					txtModificaClub.setText(n.getNombreClub());
					int nro = 0;
					if (n.getSexo() == 'm')
						nro = 0;
					else
						nro = 1;
					cbModificaSexo.setSelectedIndex(nro);
				}
				else if (Pattern.matches("[a-zA-Z]+", txtBuscar.getText()))
				{
					JOptionPane.showMessageDialog(getContentPane(), "La búsqueda es por DNI: Sólo se pueden ingresar números.");
					txtBuscar.setText("");
				}
				else if (txtBuscar.getText().charAt(0) == ' ')
				{
					JOptionPane.showMessageDialog(getContentPane(), "La búsqueda es por DNI: Sólo se pueden ingresar números.");
					txtBuscar.setText("");
				}
				
			}

			}
		});
		
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(366, 8, 125, 20);
		pnlModificarNadador.add(txtBuscar);
		
		JPanel pnlEliminarNadador = new JPanel();
		tbPnlAdministrarNadadores.addTab("Eliminar Nadador", null, pnlEliminarNadador, null);
		pnlEliminarNadador.setLayout(null);
		
		JButton btnEliminarNadador = new JButton("Eliminar Nadador");
		btnEliminarNadador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = tblEliminarNadador.getSelectedRow();
				int dni = (int) tblEliminarNadador.getValueAt(i, 2);
				cc.eliminarNadador(dni);
				JOptionPane.showMessageDialog(getContentPane(), "Nadador eliminado Correctamente");
				//ACA FALTA UN METODO PARA QUE ME HAGA UN "REFRESH" A LA TABLA Y ME SAQUE LA COLUMNA QUE BORRE
				//PROBE VARIAS COSAS PERO NO FUNCIONAN, si se les ocurre algo avisen ... nico
			}
		});
		btnEliminarNadador.setBounds(269, 240, 222, 31);
		pnlEliminarNadador.add(btnEliminarNadador);
		
		JLabel lblBuscarE = new JLabel("Buscar:");
		lblBuscarE.setBounds(305, 11, 51, 14);
		pnlEliminarNadador.add(lblBuscarE);
		
		txtBuscarE = new JTextField();
		txtBuscarE.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
			if (!txtBuscarE.getText().isEmpty())
			{
				if (isNumeric(txtBuscarE.getText()))
				{
				
					DefaultTableModel modeloTabla = new DefaultTableModel()
					{
						private static final long serialVersionUID = 1L;
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					};
					
					ArrayList<Nadador> listaNadadores = cc.buscarMuchosNadadoresPorDni(Integer.parseInt(txtBuscarE.getText()));
					Object[] identifiers = {"Nombre", "Apellido", "Dni"};
					modeloTabla.setColumnIdentifiers(identifiers);
					for(Nadador nad : listaNadadores)
					{
						Object[] o = new Object[3];
						o[0] = nad.getNombre();
						o[1] = nad.getApellido();
						o[2] = nad.getDni();
						modeloTabla.addRow(o);
					}
					tblEliminarNadador.setModel(modeloTabla);
				}
				else if (Pattern.matches("[a-zA-Z]+", txtBuscarE.getText()))
				{
					JOptionPane.showMessageDialog(getContentPane(), "La búsqueda es por DNI: Sólo se pueden ingresar números.");
					txtBuscarE.setText("");
				}
				else if (txtBuscarE.getText().charAt(0) == ' ')
				{
					JOptionPane.showMessageDialog(getContentPane(), "La búsqueda es por DNI: Sólo se pueden ingresar números.");
					txtBuscarE.setText("");
				}
			}
		}
			
		});
		txtBuscarE.setColumns(10);
		txtBuscarE.setBounds(366, 8, 125, 20);
		pnlEliminarNadador.add(txtBuscarE);
		
		tblEliminarNadador = new JTable();
		tblEliminarNadador.getTableHeader().setReorderingAllowed(false);
		tblEliminarNadador.getTableHeader().setResizingAllowed(false);
		tblEliminarNadador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		DefaultTableModel modeloTabla = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		ArrayList<Nadador> listaNadadores = cc.buscarTodosNadadores();
		Object[] identifiers = {"Nombre", "Apellido", "Dni"};
		modeloTabla.setColumnIdentifiers(identifiers);
		for(Nadador nad : listaNadadores)
		{
			Object[] o = new Object[3];
			o[0] = nad.getNombre();
			o[1] = nad.getApellido();
			o[2] = nad.getDni();
			modeloTabla.addRow(o);
		}
		tblEliminarNadador.setModel(modeloTabla);
		
		JScrollPane spEliminarNadador = new JScrollPane(tblEliminarNadador);
		spEliminarNadador.setBounds(10, 36, 481, 193);
		pnlEliminarNadador.add(spEliminarNadador);
		
	}
	

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) 
	{
		// TODO Auto-generated method stub
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
	
	 private boolean isNumeric(String str)
	  {
	    try
	    {
	    	Integer.parseInt(str);
	    }
	    catch(NumberFormatException nfe)
	    {
		     return false;
	    }
	    return true;
	  }
	 
}
