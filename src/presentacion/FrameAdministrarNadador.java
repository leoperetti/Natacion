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

import entidades.Club;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.CardLayout;

public class FrameAdministrarNadador extends JInternalFrame implements InternalFrameListener
{

	private static final long serialVersionUID = 1L;
	private static FrameAdministrarNadador instancia = null;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private JTextField txtNuevoNombre;
	private JTextField txtNuevoApellido;
	private JTextField txtModificaNombre;
	private JTextField txtModificaApellido;
	private JTable tblEliminarNadador;
	private JTextField txtBuscarE;
	private JTextField txtNuevoDni;
	private JTextField txtNuevoEdad;
	private JTextField txtModificaDni;
	private JTextField txtModificaEdad;
	
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
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tbPnlAdministrarNadadores = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tbPnlAdministrarNadadores, "tabbedPane");
		
		JPanel pnlAgregarNadador = new JPanel();
		tbPnlAdministrarNadadores.addTab("Nuevo Nadador", null, pnlAgregarNadador, null);
		pnlAgregarNadador.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 36, 79, 14);
		pnlAgregarNadador.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 61, 79, 14);
		pnlAgregarNadador.add(lblApellido);
		
		JLabel lblFechaNacimiento = new JLabel("Nacimiento:");
		lblFechaNacimiento.setBounds(10, 86, 79, 14);
		pnlAgregarNadador.add(lblFechaNacimiento);
		
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
		
		JLabel lblErrorDniNuevoNadador = new JLabel("");
		lblErrorDniNuevoNadador.setForeground(Color.RED);
		lblErrorDniNuevoNadador.setBounds(305, 11, 186, 14);
		pnlAgregarNadador.add(lblErrorDniNuevoNadador);
		
		JLabel lblErrorEdadNuevoNadador = new JLabel("");
		lblErrorEdadNuevoNadador.setForeground(Color.RED);
		lblErrorEdadNuevoNadador.setBounds(305, 86, 186, 14);
		pnlAgregarNadador.add(lblErrorEdadNuevoNadador);
		
		txtNuevoDni = new JTextField();
		txtNuevoDni.setBounds(95, 8, 200, 20);
		pnlAgregarNadador.add(txtNuevoDni);
		txtNuevoDni.setColumns(10);
		validarValoresNumericos(txtNuevoDni, lblErrorDniNuevoNadador);
		
		
		txtNuevoNombre = new JTextField();
		txtNuevoNombre.setColumns(10);
		txtNuevoNombre.setBounds(95, 33, 200, 20);
		pnlAgregarNadador.add(txtNuevoNombre);
		
		txtNuevoApellido = new JTextField();
		txtNuevoApellido.setColumns(10);
		txtNuevoApellido.setBounds(95, 58, 200, 20);
		pnlAgregarNadador.add(txtNuevoApellido);
		
		txtNuevoEdad = new JFormattedTextField(generarMascara());
		txtNuevoEdad.setColumns(10);
		txtNuevoEdad.setBounds(95, 83, 200, 20);
		pnlAgregarNadador.add(txtNuevoEdad);
		
		
		Object[] opciones = {"Masculino", "Femenino"};
		JComboBox cbNuevoSexo = new JComboBox(opciones);
		cbNuevoSexo.setBounds(95, 133, 200, 20);
		pnlAgregarNadador.add(cbNuevoSexo);
		
		JFormattedTextField txtNuevoTiempo1 = new JFormattedTextField(crearMascaraTiempo());
		txtNuevoTiempo1.setBounds(95, 158, 200, 20);
		pnlAgregarNadador.add(txtNuevoTiempo1);
		
		JFormattedTextField txtNuevoTiempo2 = new JFormattedTextField(crearMascaraTiempo());
		txtNuevoTiempo2.setBounds(95, 183, 200, 20);
		pnlAgregarNadador.add(txtNuevoTiempo2);
		
		JButton btnCargarJugador = new JButton("Cargar Nadador");
		btnCargarJugador.setBounds(289, 262, 222, 31);
		pnlAgregarNadador.add(btnCargarJugador);

		JComboBox<Club> cbClubes = new JComboBox<Club>(generarModeloClub(cc.buscarClubes()));
		cbClubes.setBounds(95, 108, 200, 20);
		pnlAgregarNadador.add(cbClubes);

		btnCargarJugador.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				cargarNadador(cbNuevoSexo, txtNuevoTiempo1, txtNuevoTiempo2, cbClubes);
			}
		});
		

		
		JPanel pnlEliminarNadador = new JPanel();
		tbPnlAdministrarNadadores.addTab("Modificar/Eliminar Nadador", null, pnlEliminarNadador, null);
		pnlEliminarNadador.setLayout(null);
		
		JButton btnEliminarNadador = new JButton("Eliminar Nadador");
		btnEliminarNadador.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				eliminarNadador();
			}
		});
		btnEliminarNadador.setBounds(289, 262, 222, 31);
		pnlEliminarNadador.add(btnEliminarNadador);
		
		JLabel lblBuscarE = new JLabel("Buscar:");
		lblBuscarE.setBounds(325, 11, 51, 14);
		pnlEliminarNadador.add(lblBuscarE);
		
		txtBuscarE = new JTextField();
		txtBuscarE.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				buscarNadadoresParaEliminar();
			}	
		});
		
		txtBuscarE.setColumns(10);
		txtBuscarE.setBounds(386, 8, 125, 20);
		pnlEliminarNadador.add(txtBuscarE);
		
		tblEliminarNadador = new JTable();
		tblEliminarNadador.getTableHeader().setReorderingAllowed(false);
		tblEliminarNadador.getTableHeader().setResizingAllowed(false);
		tblEliminarNadador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEliminarNadador.setModel(generarModeloTabla(cc.buscarTodosNadadores()));
		
		JScrollPane spEliminarNadador = new JScrollPane(tblEliminarNadador);
		spEliminarNadador.setBounds(10, 36, 501, 215);
		pnlEliminarNadador.add(spEliminarNadador);
		
		
		JPanel pnlModificarNadador = new JPanel();
		getContentPane().add(pnlModificarNadador, "pnlModificarNadador");
		pnlModificarNadador.setLayout(null);
		
		JFormattedTextField txtModificaTiempo1 = new JFormattedTextField(crearMascaraTiempo());
		txtModificaTiempo1.setBounds(119, 177, 200, 20);
		pnlModificarNadador.add(txtModificaTiempo1);
		
		JFormattedTextField txtModificaTiempo2 = new JFormattedTextField(crearMascaraTiempo());
		txtModificaTiempo2.setBounds(119, 202, 200, 20);
		pnlModificarNadador.add(txtModificaTiempo2);
		
		JComboBox cbModificaSexo = new JComboBox(opciones);
		cbModificaSexo.setBounds(119, 152, 200, 20);
		pnlModificarNadador.add(cbModificaSexo);
		
		JComboBox<Club> cbModificarClubAnfitrion = new JComboBox<Club>(generarModeloClub(cc.buscarClubes()));
		cbModificarClubAnfitrion.setBounds(119, 126, 200, 23);
		pnlModificarNadador.add(cbModificarClubAnfitrion);
		
		JButton btnModificarNadador = new JButton("Modificar Nadador");
		btnModificarNadador.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				cargarPanelModificar(txtModificaTiempo1, txtModificaTiempo2, cbModificaSexo, cbModificarClubAnfitrion);
			}
		});
		btnModificarNadador.setBounds(10, 262, 222, 31);
		pnlEliminarNadador.add(btnModificarNadador);
		
		JLabel label = new JLabel("DNI:");
		label.setBounds(34, 30, 79, 14);
		pnlModificarNadador.add(label);
		
		txtModificaDni = new JTextField();
		txtModificaDni.setEditable(false);
		txtModificaDni.setColumns(10);
		txtModificaDni.setBounds(119, 27, 200, 20);
		pnlModificarNadador.add(txtModificaDni);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setBounds(34, 55, 79, 14);
		pnlModificarNadador.add(label_1);
		
		txtModificaNombre = new JTextField();
		txtModificaNombre.setColumns(10);
		txtModificaNombre.setBounds(119, 52, 200, 20);
		pnlModificarNadador.add(txtModificaNombre);
		
		JLabel label_2 = new JLabel("Apellido:");
		label_2.setBounds(34, 80, 79, 14);
		pnlModificarNadador.add(label_2);
		
		txtModificaApellido = new JTextField();
		txtModificaApellido.setColumns(10);
		txtModificaApellido.setBounds(119, 77, 200, 20);
		pnlModificarNadador.add(txtModificaApellido);
		
		JLabel lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setBounds(34, 105, 79, 14);
		pnlModificarNadador.add(lblNacimiento);
		
		JLabel lblErrorEdadModificarNadador = new JLabel("");
		lblErrorEdadModificarNadador.setForeground(Color.RED);
		lblErrorEdadModificarNadador.setBounds(305, 86, 186, 14);
		pnlModificarNadador.add(lblErrorEdadModificarNadador);
		
		txtModificaEdad = new JFormattedTextField(generarMascara());
		txtModificaEdad.setColumns(10);
		txtModificaEdad.setBounds(119, 102, 200, 20);
		pnlModificarNadador.add(txtModificaEdad);
		validarValoresNumericos(txtModificaEdad, lblErrorEdadModificarNadador);
		
		JLabel label_4 = new JLabel("Club:");
		label_4.setBounds(34, 130, 79, 14);
		pnlModificarNadador.add(label_4);
		
		JLabel label_5 = new JLabel("Sexo:");
		label_5.setBounds(34, 155, 79, 14);
		pnlModificarNadador.add(label_5);
	
		
		JLabel label_6 = new JLabel("Tiempo 1:");
		label_6.setBounds(34, 180, 79, 14);
		pnlModificarNadador.add(label_6);
		
		JLabel label_7 = new JLabel("Tiempo 2:");
		label_7.setBounds(34, 205, 79, 14);
		pnlModificarNadador.add(label_7);
		

		JButton btnGuardarDatos = new JButton("Guardar Datos");
		btnGuardarDatos.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				modificarNadador(txtModificaTiempo1, txtModificaTiempo2, cbModificaSexo, cbModificarClubAnfitrion);
			}
		});
		btnGuardarDatos.setBounds(294, 290, 222, 31);
		pnlModificarNadador.add(btnGuardarDatos);
		

	}
	
	//Todo lo de los eventos separado a métodos privados:
	
	private void eliminarNadador()
	{
		int i = tblEliminarNadador.getSelectedRow();
		int dni = (int) tblEliminarNadador.getValueAt(i, 2);
		cc.eliminarNadador(dni);
		JOptionPane.showMessageDialog(getContentPane(), "Nadador eliminado Correctamente");
		tblEliminarNadador.setModel(generarModeloTabla(cc.buscarTodosNadadores()));
	}
	
	private void cargarPanelModificar(JFormattedTextField txtModificaTiempo1, JFormattedTextField txtModificaTiempo2, JComboBox cbModificaSexo, JComboBox<Club> cbClub)
	{
		int dniNadadorActual = (int)tblEliminarNadador.getValueAt(tblEliminarNadador.getSelectedRow(), 2);
		Nadador nadadorActual = cc.buscarNadadorPorDni(dniNadadorActual);
		txtModificaDni.setText(Integer.toString(dniNadadorActual));
		txtModificaApellido.setText(nadadorActual.getApellido());
		txtModificaNombre.setText(nadadorActual.getNombre());
		txtModificaEdad.setText(nadadorActual.getFechaNacimiento());
		
		for(Club c : cc.buscarClubes())
		{
			if (c.getNroClub() == nadadorActual.getNroClub())
				cbClub.getModel().setSelectedItem(c);
		}
		txtModificaTiempo1.setText(nadadorActual.getTiempoPreCompetencia1());
		txtModificaTiempo2.setText(nadadorActual.getTiempoPreCompetencia2());

		if (nadadorActual.getSexo() == 'm')
		{
			cbModificaSexo.setSelectedIndex(0);
		}
		else
		{
			cbModificaSexo.setSelectedIndex(1);
		}
		
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "pnlModificarNadador");
	}
	
	private void cargarNadador(JComboBox cbNuevoSexo, JFormattedTextField txtNuevoTiempo1, JFormattedTextField txtNuevoTiempo2, JComboBox<Club> cbClubes)
	{
		char sexo;
		if(!(txtNuevoNombre.getText().isEmpty() && txtNuevoApellido.getText().isEmpty() && txtNuevoEdad.getText().isEmpty() && txtNuevoDni.getText().isEmpty()))
		{
			if(cbNuevoSexo.getSelectedIndex()==0)
			{
				sexo = 'm';
			}
			else
			{
				sexo = 'f';
			}
			int dni = Integer.parseInt(txtNuevoDni.getText());
			String nombre = txtNuevoNombre.getText();
			String apellido = txtNuevoApellido.getText();
			Club clubSeleccionado = (Club)cbClubes.getSelectedItem();
			String fechaNacimiento = txtNuevoEdad.getText();
			
			cc.cargarNadador(dni, nombre, apellido, clubSeleccionado.getNroClub(), fechaNacimiento, txtNuevoTiempo1.getText(), txtNuevoTiempo2.getText(), sexo);
			JOptionPane.showMessageDialog(getContentPane(), "Nadador cargado correctamente!");
			limpiarCampos();
			tblEliminarNadador.setModel(generarModeloTabla(cc.buscarTodosNadadores()));
		}
		else
		{
			JOptionPane.showMessageDialog(getContentPane(), "Error, hay un campo en blanco");
		
		}
	}
	
	private void modificarNadador(JFormattedTextField txtModificaTiempo1, JFormattedTextField txtModificaTiempo2, JComboBox cbModificaSexo, JComboBox<Club> cbClub)
	{
		char sexo;
		if(cbModificaSexo.getSelectedIndex()==0)
		{
			sexo = 'm';
		}
		else
		{
			sexo = 'f';
		}
		Club clubSeleccionado = (Club) cbClub.getSelectedItem();
		cc.modificarNadador(txtModificaApellido.getText(), txtModificaNombre.getText(), clubSeleccionado.getNroClub(), txtModificaEdad.getText(), txtModificaTiempo1.getText(), txtModificaTiempo2.getText(), sexo, Integer.parseInt(txtModificaDni.getText()));
		tblEliminarNadador.setModel(generarModeloTabla(cc.buscarTodosNadadores()));
		JOptionPane.showMessageDialog(getContentPane(), "Nadador modificado.");
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "tabbedPane");
	}
	
	//Este recibe el jtext y el jlabel y si no es numérico lo del jtext te setea el jlabel con un mensaje avisando eso.
	//podríamos hacer una clase Util en otra capa aparte y estos métodos genéricos ponerlos ahi (lo aprendí de net a eso)
	//-Juan <3
	private void validarValoresNumericos(JTextField texto, JLabel lblError)
	{
		texto.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				if (!texto.getText().isEmpty())
				{
					if (!isNumeric(texto.getText()))
					{
						texto.setText("");
						lblError.setText("Este campo sólo acepta números");
					}
					else
					{
						lblError.setText("");
					}
				}
			}
		});
	}

	private DefaultComboBoxModel<Club> generarModeloClub(ArrayList<Club> listaClub)
	{
		DefaultComboBoxModel<Club> modeloCombo = new DefaultComboBoxModel<Club>();
		
		for(Club clu : listaClub)
		{
			modeloCombo.addElement(clu);
		}
		return modeloCombo;
		
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
	
	private MaskFormatter crearMascaraTiempo()
	{
		MaskFormatter mask = null;
        try 
        {
            mask = new MaskFormatter("##:##:##");
            mask.setPlaceholderCharacter('0');
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        return mask;
	}
	
	private void buscarNadadoresParaEliminar()
	{
		if (!txtBuscarE.getText().isEmpty())
		{
			if (isNumeric(txtBuscarE.getText()))
			{
				tblEliminarNadador.setModel(generarModeloTabla(cc.buscarMuchosNadadoresPorDni(Integer.parseInt(txtBuscarE.getText()))));
			}
			else if (Pattern.matches("[a-zA-Z]+", txtBuscarE.getText()))
			{
				tblEliminarNadador.setModel(generarModeloTabla(cc.buscarMuchosNadadoresPorNombreYApellido(txtBuscarE.getText())));
			}
			else if (txtBuscarE.getText().charAt(0) == ' ')
			{
				txtBuscarE.setText("");
			}
		}
		else
		{
			tblEliminarNadador.setModel(generarModeloTabla(cc.buscarTodosNadadores()));
		}
	}
	
	//Se le pasa como parámetro una lista de nadadores, y te devuelve el modelo cargado con los elementos de la lista directo para
	//aplicarlo en una tabla.
	private DefaultTableModel generarModeloTabla(ArrayList<Nadador> listaNadadores)
	{
		DefaultTableModel modeloTabla = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
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
		return modeloTabla;
		
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
	
	//Otro que podría ir en la clase Util
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
	 private void limpiarCampos(){
		 txtNuevoApellido.setText("");
		 txtNuevoNombre.setText("");
		 txtNuevoDni.setText("");
		 txtNuevoEdad.setText("");
	 }
}
