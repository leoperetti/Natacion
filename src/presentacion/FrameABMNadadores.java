package presentacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

import negocio.ControladorCompetencia;
import javax.swing.JFrame;

public class FrameABMNadadores extends JInternalFrame implements InternalFrameListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre, txtApellido, txtClub, txtEdad, txtDni;
	private ControladorCompetencia cc = new ControladorCompetencia();
	private static FrameABMNadadores instancia = null;

	
	
	public FrameABMNadadores() 
	{
        initComponents();
        addInternalFrameListener(this);
    }
	
	public static FrameABMNadadores obtenerInstancia()
	{
		if (instancia == null)
		{
			instancia = new FrameABMNadadores();
		}
		return instancia;
	}
	
	public static FrameABMNadadores devolverInstancia()
	{
		return instancia;
	}

	
	public void initComponents() 
	{
		setTitle("Registrar Nadador");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setBounds(27, 11, 445, 242);
		this.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 57, 26);
		this.getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(72, 17, 100, 20);
		this.getContentPane().add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(280, 17, 100, 20);
		this.getContentPane().add(txtApellido);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(225, 11, 62, 26);
		this.getContentPane().add(lblApellido);
				
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setCommitsOnValidEdit(true);
		
	    JFormattedTextField txtDni = new JFormattedTextField(formatter);
		txtDni.setColumns(10);
		txtDni.setBounds(280, 48, 100, 20);
		this.getContentPane().add(txtDni);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(225, 51, 46, 14);
		this.getContentPane().add(lblDni);
		
		JFormattedTextField txtEdad = new JFormattedTextField(formatter);
		txtEdad.setColumns(10);
		txtEdad.setBounds(72, 51, 100, 20);
		this.getContentPane().add(txtEdad);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(10, 44, 47, 29);
		this.getContentPane().add(lblEdad);
		
		JLabel lblClub = new JLabel("Club:");
		lblClub.setBounds(10, 84, 57, 26);
		this.getContentPane().add(lblClub);
		
		txtClub = new JTextField();
		txtClub.setColumns(10);
		txtClub.setBounds(72, 90, 100, 20);
		this.getContentPane().add(txtClub);
		
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
        
		
		JFormattedTextField txtTiempo1 = new JFormattedTextField(mask);
		txtTiempo1.setBounds(72, 132, 100, 20);
		this.getContentPane().add(txtTiempo1);
		
		JLabel lblTiempo = new JLabel("Tiempo 1:");
		lblTiempo.setBounds(10, 135, 57, 14);
		this.getContentPane().add(lblTiempo);
		
		JLabel lblNewLabel = new JLabel("Tiempo 2:");
		lblNewLabel.setBounds(10, 168, 57, 14);
		this.getContentPane().add(lblNewLabel);
		
		JFormattedTextField txtTiempo2 = new JFormattedTextField(mask);
		txtTiempo2.setBounds(72, 165, 100, 20);
		this.getContentPane().add(txtTiempo2);
		
		Object[] opciones = {"Masculino", "Femenino"};
		
		JComboBox<Object> cb2Genero = new JComboBox<Object>(opciones);
		cb2Genero.setBounds(280, 79, 100, 20);
		getContentPane().add(cb2Genero);
		setVisible(true);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(291, 164, 89, 23);

		btnAgregar.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				char sexo;
				
				if(!(txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty() && 
					txtEdad.getText().isEmpty() && txtClub.getText().isEmpty()
					&& txtDni.getText().isEmpty()))
				{
					
					if(cb2Genero.getSelectedIndex()==0)
					{
						sexo = 'm';
					}
					else
					{
						sexo = 'f';
					}
					int dni = (int)txtDni.getValue();
					String nombre = txtNombre.getText();
					String apellido = txtApellido.getText();
					String club = txtClub.getText();
					int edad = (int)txtEdad.getValue();
					
					cc.cargarNadador(dni, nombre, apellido, club, edad, txtTiempo1.getText(), txtTiempo2.getText(), sexo);
					JOptionPane.showMessageDialog(getContentPane(), "Nadador cargado correctamente!");
				}
				else
				{
					JOptionPane.showMessageDialog(getContentPane(), "Error, hay un campo en blanco");
				
				}
			}
		});
		this.getContentPane().add(btnAgregar);
		
		JLabel lblNewLabel_1 = new JLabel("Sexo:");
		lblNewLabel_1.setBounds(225, 76, 46, 23);
		getContentPane().add(lblNewLabel_1);


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
}

