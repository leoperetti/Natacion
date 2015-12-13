package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class prueba{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba window = new prueba();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public prueba() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private JComboBox cb1, cb2, cb3, cb4;
	private DefaultComboBoxModel db1 = new DefaultComboBoxModel(), db2= new DefaultComboBoxModel(), db3= new DefaultComboBoxModel(), db4= new DefaultComboBoxModel();
	
	  private enum Actions {
		    cb1,
		    cb2
		  }
	  
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		db1.addElement("1");
		db1.addElement("2");
		
		cb1 = new JComboBox(db1);
		cb1.setBounds(10, 11, 242, 20);
		frame.getContentPane().add(cb1);
		
		cb2 = new JComboBox(db2);
		cb2.setBounds(10, 42, 242, 20);
		frame.getContentPane().add(cb2);

		
		cb3 = new JComboBox(db3);
		cb3.setBounds(10, 73, 242, 20);
		frame.getContentPane().add(cb3);
		
		cb4 = new JComboBox();
		cb4.setBounds(10, 104, 242, 20);
		frame.getContentPane().add(cb4);
		
		ActionListener al3 = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("3");
			}
		};
		
		ActionListener al2 = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("2");
				db3.removeAllElements();
				db3.addElement("3");
			}
		};

		ActionListener al1 = new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cb2.removeActionListener(al2);
				cb3.removeActionListener(al3);
				System.out.println("1");
				db2.removeAllElements();
				db2.addElement("4");
				db3.removeAllElements();
				cb2.addActionListener(al2);
				cb3.addActionListener(al3);
			}
		};
		

		
		cb1.addActionListener(al1);
		cb2.addActionListener(al2);
		cb3.addActionListener(al3);
		
	}

}


