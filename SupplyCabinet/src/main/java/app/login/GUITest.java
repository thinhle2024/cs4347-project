package app.login;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUITest extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginInfoModel loginInfo;

	private JPanel contentPane;	
	private LoginView customDialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					GUITest frame = new GUITest();
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
		loginInfo = new LoginInfoModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
				
		pack();
		setLocationRelativeTo(null);
		
		customDialog = new LoginView(loginInfo, null);
		customDialog.setVisible(true);
	}

	// ===============================================

}
