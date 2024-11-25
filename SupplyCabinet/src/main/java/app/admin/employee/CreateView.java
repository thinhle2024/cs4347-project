package app.admin.employee;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Employee;
import model.Office;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtSSN;
	private JTextField txtFirstName;
	private JTextField txtMiddleInitial;
	private JTextField txtLastName;

	/**
	 * Create the panel.
	 */
	private EmployeeController controller;
	private List<Office> dataSource;
	
	@SuppressWarnings("static-access")
	public CreateView() {
		
		controller = new EmployeeController();
		dataSource = controller.getOfficeList();
		
		final JComboBox<Office> cbxOfficeList = new JComboBox<>();
		cbxOfficeList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cbxOfficeList.setRenderer(new ListCellRenderer<Office>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Office> list, Office value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if (value == null)
					return null;
				return new JLabel(value.getOfficeName());
			}
		});
		
		if (dataSource != null)			
			cbxOfficeList.setModel(new DefaultComboBoxModel<Office>(dataSource.toArray(new Office[0])));		
		
		JLabel lblNewLabel = new JLabel("Office");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblMiddleName = new JLabel("Middle initial");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		txtSSN = new JTextField();
		txtSSN.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtSSN.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtFirstName.setColumns(10);
		
		txtMiddleInitial = new JTextField();
		txtMiddleInitial.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtMiddleInitial.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtLastName.setColumns(10);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.createRecord(new Employee(
						txtSSN.getText(),
						txtFirstName.getText(),
						txtMiddleInitial.getText(),
						txtLastName.getText(),
						((Office)cbxOfficeList.getSelectedItem()).getOfficeNum()
						));
				
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMiddleName, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtMiddleInitial, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel)
										.addComponent(lblSsn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(txtFirstName, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
											.addComponent(cbxOfficeList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(txtSSN, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))))
							.addContainerGap(16, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(59))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cbxOfficeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSsn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSSN, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMiddleName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMiddleInitial, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
