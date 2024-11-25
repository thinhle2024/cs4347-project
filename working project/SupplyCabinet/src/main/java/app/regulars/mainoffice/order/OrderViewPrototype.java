package app.regulars.mainoffice.order;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Product;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderViewPrototype extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JTextField txtQtyNeded;
	protected JTextField txtTotal;
	
	protected JLabel lblExtraInfo;
	protected JComboBox<Product> cbxProducts;
	protected JList<CustomSuppliedbyModel> lstVendors;
	protected JList<CustomSuppliedbyModel> lstOrders;
	
	protected JButton btnAdd;
	protected JButton btnRemove;
	protected JButton btnPlaceAnOrder;
	
	/**
	 * Create the panel.
	 */
	public OrderViewPrototype() {
		
		JLabel lblNewLabel = new JLabel("Product list");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));

		cbxProducts = new JComboBox<>();
		cbxProducts.setFont(new Font("Tahoma", Font.PLAIN, 22));
		

		JLabel lblNewLabel_1 = new JLabel("Vendor list");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JScrollPane scrollPane = new JScrollPane();

		txtQtyNeded = new JTextField();
		txtQtyNeded.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtQtyNeded.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Qty needed");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblNewLabel_2_1 = new JLabel("Total");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 22));

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtTotal.setColumns(10);

		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));

		btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblNewLabel_1_1 = new JLabel("Order list");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JScrollPane scrollPane_1 = new JScrollPane();

		btnPlaceAnOrder = new JButton("Place an order");
		btnPlaceAnOrder.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		// 
		lblExtraInfo = new JLabel(" ");
		lblExtraInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblExtraInfo.setForeground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxProducts, 0, 337, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAdd)
									.addGap(18)
									.addComponent(btnRemove))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtTotal, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(txtQtyNeded, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblExtraInfo, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPlaceAnOrder)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cbxProducts, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtQtyNeded, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblExtraInfo))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd)
						.addComponent(btnRemove))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnPlaceAnOrder)
					.addContainerGap(22, Short.MAX_VALUE))
		);

		lstVendors = new JList<>();
		lstVendors.setFont(new Font("Tahoma", Font.PLAIN, 22));
		scrollPane.setViewportView(lstVendors);

		lstOrders = new JList<>();
		lstOrders.setFont(new Font("Tahoma", Font.PLAIN, 22));
		scrollPane_1.setViewportView(lstOrders);
		
		setLayout(groupLayout);
	}
}
