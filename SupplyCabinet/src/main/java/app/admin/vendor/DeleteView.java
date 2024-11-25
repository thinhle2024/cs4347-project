package app.admin.vendor;

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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;

import model.Vendor;

public class DeleteView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	
	private List<Vendor> dataSource;
	private VendorController controller; 
	
	@SuppressWarnings("static-access")
	public DeleteView() {
		
		controller = new VendorController();
		dataSource = controller.getVendorList();
		
		final JComboBox<Vendor> cbxOfficeList = new JComboBox<>();
		cbxOfficeList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cbxOfficeList.setRenderer(new ListCellRenderer<Vendor>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Vendor> list, Vendor value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if (value == null)
					return null;				
				return new JLabel(value.getVendorName());
			}
		});
		
		if (dataSource != null)			
			cbxOfficeList.setModel(new DefaultComboBoxModel<Vendor>(dataSource.toArray(new Vendor[0])));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.deleteRecord(
						new Vendor( ((Vendor)cbxOfficeList.getSelectedItem()).getVendorNum() ) );
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblOfficeList = new JLabel("Vendor list");
		lblOfficeList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		cbxOfficeList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOfficeList, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cbxOfficeList, 0, 250, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnDelete)
							.addGap(76))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOfficeList, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxOfficeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		setLayout(groupLayout);

	}
}
