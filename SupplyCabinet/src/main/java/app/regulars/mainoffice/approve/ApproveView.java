package app.regulars.mainoffice.approve;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApproveView extends JPanel {

	private JList<CustomShippedModel> lstUnApprovedOrder;
	/**
	 * Create the panel.
	 */
	public ApproveView() {
		
		JLabel lblNewLabel = new JLabel("Order list");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("Approve");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApproveController.approve(lstUnApprovedOrder.getSelectedValue());
				refresh();
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
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGap(30))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		lstUnApprovedOrder = new JList<>();
		lstUnApprovedOrder.setFont(new Font("Tahoma", Font.PLAIN, 22));
		scrollPane.setViewportView(lstUnApprovedOrder);
		setLayout(groupLayout);


		refresh();
	}
	
	public void refresh() {
		List<CustomShippedModel> l = ApproveController.getUnapprovedOrderList();
		lstUnApprovedOrder.setModel(new DefaultComboBoxModel<CustomShippedModel>(l.toArray(new CustomShippedModel[0])));
	}

}
