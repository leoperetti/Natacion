package reportes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reporte extends JFrame {

	public Reporte( Connection con){
		
		AbstractReportes.crearReporte(con, "C:\\Users\\Leo\\JaspersoftWorkspace\\Prueba\\nadadores.jasper");
		getContentPane();
		initComponents();
		AbstractReportes.showViewer();
	}

	/**
	 * Launch the application.
	 */
	private void initComponents() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte frame = new Reporte();
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
	public Reporte() {
		getContentPane().setLayout(null);
	}
}
