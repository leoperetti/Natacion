package reportes;

import com.mysql.jdbc.Connection;

import conexion.DataConnection;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JRException;

public abstract class AbstractReportes {

	private static JasperReport report;
	private static JasperPrint reportFilled;
	private static JasperViewer viewer;
	
	public static void crearReporte( Connection con, String path){
		
		try{
			
			report = (JasperReport) JRLoader.loadObjectFromFile(path);
			reportFilled = JasperFillManager.fillReport(report, null, con);
			
		}
		catch( JRException e){
			System.out.println("HOLA");
			e.printStackTrace();
		}
		
		
		
		
		
	}

	
	public static void showViewer(){

		viewer = new JasperViewer(reportFilled );
		viewer.setVisible(true);
	}

	/*public static void exportToPDF( String destino){
		
		try
		{
			JasperExportManager.exportReportToPdfFile(reportFilled	, destino);
		}
		catch(JRException e)
		{
			e.printStackTrace();
		}
		
	}*/
}

