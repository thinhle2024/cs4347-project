package app.regulars.branchoffice.order;

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

import model.Shipped;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class OrderViewPrototype extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JTextField txtQtyNeeded;
	protected JTextField txtQtyAvailable;
	
	protected JButton btnAdd;
	protected JButton btnPlaceAnOrder;	
	
	protected JComboBox<CustomProductModel> cbxProductList;
	protected JScrollPane scrollPane;
	protected JList<CustomProductModel2> lstOrders;
	
	/**
	 * Create the panel.
	 */
	public OrderViewPrototype() {
		
		JLabel lblNewLabel = new JLabel("Product list");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblNewLabel_1 = new JLabel("Qty needed");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		txtQtyNeeded = new JTextField();
		txtQtyNeeded.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtQtyNeeded.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Qty Available");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		txtQtyAvailable = new JTextField();
		txtQtyAvailable.setEditable(false);
		txtQtyAvailable.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtQtyAvailable.setColumns(10);
		
		btnAdd = new JButton("add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblNewLabel_2_1 = new JLabel("Order list");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		btnPlaceAnOrder = new JButton("Place an order");
		btnPlaceAnOrder.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		cbxProductList = new JComboBox<>();
		cbxProductList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxProductList, 0, 277, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd)
								.addComponent(txtQtyAvailable, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
							.addGap(113))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtQtyNeeded, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnPlaceAnOrder, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
									.addGap(5))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cbxProductList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQtyNeeded, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtQtyAvailable, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAdd)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPlaceAnOrder, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		lstOrders = new JList<>();
		lstOrders.setFont(new Font("Tahoma", Font.PLAIN, 22));
		scrollPane.setViewportView(lstOrders);

		
		setLayout(groupLayout);
		
	}

	
}
