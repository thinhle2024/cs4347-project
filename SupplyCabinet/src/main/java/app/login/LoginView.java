package app.login;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.SupplyCabinet;

public class LoginView extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// =============================================
	// non-GUI-related variables
	private LoginInfoModel loginInfo;
	private LoginController loginController;
	
	// =============================================
	
	private JTextField txtSSN;

	/**
	 * Create the dialog.
	 */
	public LoginView(LoginInfoModel loginInfo, SupplyCabinet sc) {
		// make sure these objects have been initialized before they are passed
		LoginView.this.loginInfo = loginInfo;
		
		// =========================================
		// non-GUI-related initialization
		loginController = new LoginController();
		
		// =========================================
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Login");
		setBounds(100, 100, 386, 95);	
		
		JLabel lblNewLabel = new JLabel("SSN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		txtSSN = new JTextField();
		txtSSN.setText("200000000");
		txtSSN.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtSSN.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setText("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int officeNum = -1;
				String SSN = LoginView.this.txtSSN.getText();

				// checking SSN
				// the return value is the office # of the employee associated with the SSN
				if (validateSSN()) {
					if ( ( officeNum = loginController.checkSSN(SSN) ) != -1 ) {
						setVisible(false);
						
						LoginView.this.loginInfo.setSSN(SSN);
						LoginView.this.loginInfo.setOfficeNum(officeNum);
						LoginView.this.loginInfo.setMainOffice(
									officeNum == loginController.getMainOfficeNum()
								);
						LoginView.this.loginInfo.setAdministrator(
									SSN.equals(loginController.getAdminSSN())
								);
						
						sc.setVisible();
						
						dispose();
					}
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(txtSSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtSSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin))
					.addContainerGap(236, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		setLocationRelativeTo(null);
	}
	
	public boolean containsOnlyDigits() {
		String s = txtSSN.getText();
		for (int i=0, size=s.length() ; i<size ; ++i) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validateSSN() {
		return !txtSSN.getText().isEmpty() && containsOnlyDigits(); 
	}
	
}
