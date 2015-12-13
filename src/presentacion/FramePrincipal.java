package presentacion;

import java.awt.EventQueue;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane contentPane;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try 
				{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
			
		//ADMINISTRAR NADADORES
		JMenuItem mntmAgregarNadador = new JMenuItem("Administrar Nadadores");
		mntmAgregarNadador.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{		
				if (FrameAdministrarNadador.devolverInstancia() ==  null)
				{
					agregarAlPanel(FrameAdministrarNadador.obtenerInstancia());
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
					agregarAlPanel(FrameInscribirNadadorCarrera.obtenerInstancia());
					FrameInscribirNadadorCarrera.obtenerInstancia().setVisible(true);
				}
			}
		});
		mnCarreras.add(mntmInscribirACarrera);
		
		
		//CARGAR NUEVA CARRERA
		JMenuItem mntmCarrera = new JMenuItem("Administrar carreras");
		mntmCarrera.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (FrameAdministrarCarrera.devolverInstancia() ==  null)
				{
					agregarAlPanel(FrameAdministrarCarrera.obtenerInstancia());
					FrameAdministrarCarrera.obtenerInstancia().setVisible(true);
				}
				
			}
		});
		mnCarreras.add(mntmCarrera);
		
		JMenu mnTorneos = new JMenu("Torneos");
		menuBar.add(mnTorneos);
		
		//ADMINISTRAR TORNEOS
		JMenuItem mntmAdministrarTorneos = new JMenuItem("Administrar Torneos");
		mntmAdministrarTorneos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (FrameAdministrarTorneo.devolverInstancia() ==  null)
				{
					agregarAlPanel(FrameAdministrarTorneo.obtenerInstancia());
					FrameAdministrarTorneo.obtenerInstancia().setVisible(true);
				}
			}
		});
		mnTorneos.add(mntmAdministrarTorneos);
		
		JMenu mnProgramas = new JMenu("Programas");
		menuBar.add(mnProgramas);
		
		//ADMINISTRAR PROGRAMAS
		JMenuItem mntmAdministrarProgramas = new JMenuItem("Administrar Programas");
		mntmAdministrarProgramas.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (FrameAdministrarProgramas.devolverInstancia() ==  null)
				{
					agregarAlPanel(FrameAdministrarProgramas.obtenerInstancia());
					FrameAdministrarProgramas.obtenerInstancia().setVisible(true);
				}
			}
		});
		mnProgramas.add(mntmAdministrarProgramas);
		
		JMenu mnEstilos = new JMenu("Estilos");
		menuBar.add(mnEstilos);
		
		//ADMINISTRAR ESTILOS
		JMenuItem mntmAdministrarEstilos = new JMenuItem("Administrar Estilos");
		mntmAdministrarEstilos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (FrameAdministrarEstilos.devolverInstancia() ==  null)
				{
					agregarAlPanel(FrameAdministrarEstilos.obtenerInstancia());
					FrameAdministrarEstilos.obtenerInstancia().setVisible(true);
				}
			}
		});
		mnEstilos.add(mntmAdministrarEstilos);
		
	}
	
	//Esto pone un panel adelante del otro y con la forma tipo "en cascada"
	private void agregarAlPanel(JInternalFrame frame)
	{
		frame.setBounds(10 * (contentPane.getAllFrames().length + 1), 10 * (contentPane.getAllFrames().length + 1), (int)frame.getBounds().getWidth(), (int)frame.getBounds().getHeight());
		contentPane.add(frame);
		frame.moveToFront();
	}
}

