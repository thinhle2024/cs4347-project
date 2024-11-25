package app.admin.employee;

import java.awt.EventQueue;

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
	 */
	public GUITest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		setContentPane(new CreateView());
		setContentPane(new DeleteView());
		
		pack();
		setLocationRelativeTo(null);
	}

}

