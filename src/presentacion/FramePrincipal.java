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


public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
					java.net.URL url = FramePrincipal.class.getResource("/resources/iconoNadador.png");
	                ImageIcon icon = new ImageIcon(url);
					frame.setIconImage(icon.getImage());
					frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FramePrincipal() 
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
		JMenuItem mntmAgregarNadador = new JMenuItem("Administrar Nadadores");
		mntmAgregarNadador.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{		
				if (FrameAdministrarNadador.devolverInstancia() ==  null)
				{
					contentPane.add(FrameAdministrarNadador.obtenerInstancia());
					FrameAdministrarNadador.obtenerInstancia().setVisible(true);
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
		
		JMenu mnTorneos = new JMenu("Torneos");
		menuBar.add(mnTorneos);
		
		JMenuItem mntmAdministrarTorneos = new JMenuItem("Administrar Torneos");
		mntmAdministrarTorneos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (FrameAdministrarTorneo.devolverInstancia() ==  null)
				{
					contentPane.add(FrameAdministrarTorneo.obtenerInstancia());
					FrameAdministrarTorneo.obtenerInstancia().setVisible(true);
				}
			}
		});
		mnTorneos.add(mntmAdministrarTorneos);
		
	}
}

