package app.admin.suppliedby;

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

import model.Product;
import model.Suppliedby;
import model.Vendor;

public class DeleteView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	
	private List<Suppliedby> dataSource;
	private SuppliedbyController controller; 
	
	@SuppressWarnings("static-access")
	public DeleteView() {
		
		controller = new SuppliedbyController();	
		dataSource = controller.getSuppliedbyList();
		
		final JComboBox<Suppliedby> cbxAssociation = new JComboBox<>();
		cbxAssociation.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cbxAssociation.setRenderer(new ListCellRenderer<Suppliedby>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Suppliedby> list, Suppliedby value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if (value == null)
					return null;				
				return new JLabel(value.getVendor().getVendorName() + " - " + value.getProduct().getProductName());
			}
		});
		
		if (dataSource != null)			
			cbxAssociation.setModel(new DefaultComboBoxModel<Suppliedby>(dataSource.toArray(new Suppliedby[0])));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.unlinkVendorProduct(
						new Suppliedby(
								new Vendor(((Suppliedby)cbxAssociation.getSelectedItem()).getVendor().getVendorNum()),
								new Product(((Suppliedby)cbxAssociation.getSelectedItem()).getProduct().getProductNum())
								)
						);;
				
				dataSource = controller.getSuppliedbyList();
				if (dataSource != null)			
					cbxAssociation.setModel(new DefaultComboBoxModel<Suppliedby>(dataSource.toArray(new Suppliedby[0])));
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblOfficeList = new JLabel("Association list");
		lblOfficeList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		cbxAssociation.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOfficeList, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDelete)
						.addComponent(cbxAssociation, 0, 322, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOfficeList, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnDelete)
					.addContainerGap())
		);
		
		setLayout(groupLayout);

	}
}
