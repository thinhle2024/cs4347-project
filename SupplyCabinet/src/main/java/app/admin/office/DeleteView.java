package app.admin.office;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;

import model.Office;

public class DeleteView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtOfficeName;
	private JTextField txtOfficeAddress;

	/**
	 * Create the panel.
	 */
	
	private List<Office> dataSource;
	private OfficeController controller; 
	
	@SuppressWarnings("static-access")
	public DeleteView() {
		
		controller = new OfficeController();
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
		
		JLabel lblNewLabel = new JLabel("Office name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		txtOfficeName = new JTextField();
		txtOfficeName.setEditable(false);
		txtOfficeName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtOfficeName.setColumns(10);
		
		txtOfficeAddress = new JTextField();
		txtOfficeAddress.setEditable(false);
		txtOfficeAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtOfficeAddress.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.deleteRecord(new Office(
						((Office)cbxOfficeList.getSelectedItem()).getOfficeNum(),
						txtOfficeName.getText(),
						txtOfficeAddress.getText()
						));
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblOfficeList = new JLabel("Office list");
		lblOfficeList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		cbxOfficeList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOfficeList, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cbxOfficeList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(txtOfficeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDelete)
								.addComponent(txtOfficeAddress, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOfficeList, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxOfficeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtOfficeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtOfficeAddress, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDelete)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		setLayout(groupLayout);

	}
}
