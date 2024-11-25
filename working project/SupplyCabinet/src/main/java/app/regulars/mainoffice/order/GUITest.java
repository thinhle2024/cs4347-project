package app.regulars.mainoffice.order;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;

public class GUITest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITest frame = new GUITest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public GUITest() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(new OrderView("200000000"));
		
		pack();
		setLocationRelativeTo(null);
	}

}
