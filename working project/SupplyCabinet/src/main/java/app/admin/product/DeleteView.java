package app.admin.product;

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

public class DeleteView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	
	private List<Product> dataSource;
	private ProductController controller; 
	
	@SuppressWarnings("static-access")
	public DeleteView() {
		
		controller = new ProductController();
		dataSource = controller.getOfficeList();
		
		final JComboBox<Product> cbxProductList = new JComboBox<>();
		cbxProductList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cbxProductList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cbxProductList.setRenderer(new ListCellRenderer<Product>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Product> list, Product value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if (value == null)
					return null;
				return new JLabel(value.getProductName());
			}
		});
		
		if (dataSource != null)			
			cbxProductList.setModel(new DefaultComboBoxModel<Product>(dataSource.toArray(new Product[0])));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.deleteRecord(new Product(
						((Product)cbxProductList.getSelectedItem()).getProductNum()
						));
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblOfficeList = new JLabel("Product list");
		lblOfficeList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		cbxProductList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOfficeList, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(cbxProductList, 0, 202, Short.MAX_VALUE)
							.addContainerGap(18, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnDelete)
							.addGap(69))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOfficeList, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxProductList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		
		setLayout(groupLayout);

	}
}
