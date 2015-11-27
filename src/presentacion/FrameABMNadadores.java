package presentacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.text.MaskFormatter;

import negocio.ControladorCompetencia;
import javax.swing.JFrame;

public class FrameABMNadadores extends JInternalFrame implements InternalFrameListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre, txtApellido, txtDni, txtEdad, txtClub;
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setMaximizable(true);
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
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(280, 48, 100, 20);
		this.getContentPane().add(txtDni);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(225, 51, 46, 14);
		this.getContentPane().add(lblDni);
		
		txtEdad = new JTextField();
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
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(236, 164, 89, 23);

		btnAgregar.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
				if(!(txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty() && 
					txtEdad.getText().isEmpty() && txtClub.getText().isEmpty()
					&& txtDni.getText().isEmpty()))
				{
					int dni = Integer.parseInt(txtDni.getText());
					String nombre = txtNombre.getText();
					String apellido = txtApellido.getText();
					String club = txtClub.getText();
					int edad = Integer.parseInt(txtEdad.getText());
					String[] tiempoString1 = txtTiempo1.getText().split(":");
					//ARREGLAR ESTO: EL timUnit.Hours.toMillis no anda bien
					Time t1 =  new Time(TimeUnit.HOURS.toMillis(Integer.parseInt(tiempoString1[0])) + TimeUnit.MINUTES.toMillis(Integer.parseInt(tiempoString1[1])) + TimeUnit.SECONDS.toMillis(Integer.parseInt(tiempoString1[2])));
					
					String[] tiempoString2 = txtTiempo2.getText().split(":");
					Time t2 =  new Time(TimeUnit.HOURS.toMillis(Integer.parseInt(tiempoString2[0])) + TimeUnit.MINUTES.toMillis(Integer.parseInt(tiempoString2[1])) + TimeUnit.SECONDS.toMillis(Integer.parseInt(tiempoString2[2])));
					if((Integer.parseInt(tiempoString1[0]) == 00) && (Integer.parseInt(tiempoString1[1]) == 0) && (Integer.parseInt(tiempoString1[2]) == 0)
							&& (Integer.parseInt(tiempoString2[0]) == 00) && (Integer.parseInt(tiempoString2[1]) == 0) && (Integer.parseInt(tiempoString2[2]) == 0))
					{
						JOptionPane.showMessageDialog(getContentPane(), "No ingresó el tiempo");
					}
					else
					{
						cc.cargarNadador(dni, nombre, apellido, club, edad, t1, t2);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(getContentPane(), "Error, hay un campo en blanco");
				
				}
			}
		});
		this.getContentPane().add(btnAgregar);


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
