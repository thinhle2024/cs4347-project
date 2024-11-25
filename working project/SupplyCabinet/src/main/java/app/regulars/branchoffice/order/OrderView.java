package app.regulars.branchoffice.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

public class OrderView extends OrderViewPrototype {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int officeNum;
	
	private List<CustomProductModel> products;
	private List<CustomProductModel2> orders;
	
	public OrderView(int officeNum) {
		super();
		
		this.officeNum = officeNum;
		
		btnPlaceAnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		lstOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		refresh();
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateQuantity();
				updateTextfields();
				updateOrders();
			}
		});
		
		cbxProductList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (products == null)
					return;
				
				updateTextfields();
			}
		});
		
		btnPlaceAnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<CustomProductModel2> l = new ArrayList<>();
				
				for (int i=0, size=lstOrders.getModel().getSize() ; i<size ; ++i)
					l.add(new CustomProductModel2(
								lstOrders.getModel().getElementAt(i).getProductNum(),
								lstOrders.getModel().getElementAt(i).getProductName(),
								lstOrders.getModel().getElementAt(i).getQuantityAvailable(),
								lstOrders.getModel().getElementAt(i).getQuantityNeeded()
							));
				
				OrderController.placeOrder(officeNum, l);
				
				refresh();
			}
		});

	}
	
	public void updateOrders() {
		boolean exist = false;
		CustomProductModel c = ((CustomProductModel)cbxProductList.getSelectedItem());
		for (int i=0, size=lstOrders.getModel().getSize() ; i<size ; ++i) {
			if (	lstOrders.getModel().getElementAt(i).getProductNum() == c.getProductNum()	) {
				
				lstOrders.getModel().getElementAt(i).setQuantityNeeded(c.getQuantityNeeded());
				lstOrders.getModel().getElementAt(i).setQuantityAvailable(c.getQuantityAvailable());
				
				exist = true;
				break;
			}
		}
		
		if (!exist)
			orders.add(new CustomProductModel2(
					c.getProductNum(),
					c.getProductName(),
					c.getQuantityAvailable(),
					c.getQuantityNeeded()
					));
		
		lstOrders.setModel(new DefaultComboBoxModel<CustomProductModel2>(orders.toArray(new CustomProductModel2[0])));
	}
	
	public void updateQuantity() {
		CustomProductModel c = (CustomProductModel) cbxProductList.getSelectedItem();
		int need = Integer.parseInt(txtQtyNeeded.getText());
		c.setQuantityNeeded(need);
	}
	
	public void updateTextfields() {
		txtQtyAvailable.setText(((CustomProductModel)cbxProductList.getSelectedItem()).getQuantityAvailable()+"");
	}
	
	public void refresh() {
		products = OrderController.getCustomProductList();
		cbxProductList.setModel(new DefaultComboBoxModel<CustomProductModel>(products.toArray(new CustomProductModel[0])));
		orders = new ArrayList<>();
	}
}
