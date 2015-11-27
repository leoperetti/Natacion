package presentacion;

import java.awt.EventQueue;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Presentacion2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion2 frame = new Presentacion2();
					ImageIcon img = new ImageIcon("Icono/iconoNadador.png");
					frame.setIconImage(img.getImage());
					frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Presentacion2() 
	{
		setTitle("Gestión de Competencias de Natación");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 410);
		
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNadadores = new JMenu("Nadadores");
		menuBar.add(mnNadadores);
		
		JMenu mnCarreras = new JMenu("Carreras");
		menuBar.add(mnCarreras);
	
		
		//AGREGAR NADADOR
		JMenuItem mntmAgregarNadador = new JMenuItem("Agregar Nadador");
		mntmAgregarNadador.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{		
				if (FrameABMNadadores.devolverInstancia() ==  null)
				{
					contentPane.add(FrameABMNadadores.obtenerInstancia());
					FrameABMNadadores.obtenerInstancia().setVisible(true);
				}
			}
		});
		mnNadadores.add(mntmAgregarNadador);
		
		
		//INSCRIBIR A CARRERA
		JMenuItem mntmInscribirACarrera = new JMenuItem("Inscribir a Carrera/Generar Serie");
		mntmInscribirACarrera.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (FrameInscribirNadadorCarrera.devolverInstancia() ==  null)
				{
					contentPane.add(FrameInscribirNadadorCarrera.obtenerInstancia());
					FrameInscribirNadadorCarrera.obtenerInstancia().setVisible(true);
				}
			}
		});
		mnCarreras.add(mntmInscribirACarrera);
		
		
		//CARGAR NUEVA CARRERA
		JMenuItem mntmCarrera = new JMenuItem("Cargar nueva carrera");
		mntmCarrera.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (FrameRegistrarCarrera.devolverInstancia() ==  null)
				{
					contentPane.add(FrameRegistrarCarrera.obtenerInstancia());
					FrameRegistrarCarrera.obtenerInstancia().setVisible(true);
				}
			}
		});
		mnCarreras.add(mntmCarrera);
		
	}
}

