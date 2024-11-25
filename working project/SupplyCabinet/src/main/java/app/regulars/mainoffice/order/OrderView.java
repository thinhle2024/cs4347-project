package app.regulars.mainoffice.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.MessageFactory;
import app.admin.product.ProductController;
import model.Product;

public class OrderView extends OrderViewPrototype {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String SSN;
	
	private List<CustomSuppliedbyModel> vendors;
	private List<CustomSuppliedbyModel2> orders;
	
	private static boolean canUpdateTotal;
	
	public OrderView(String SSN) {
		super();
		
		this.SSN = SSN;
		updateProductList();
		updateVendorList();
		
		orders = new ArrayList<>();	
		
		cbxProducts.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updateVendorList();
			}
		});
		
		lstVendors.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (txtQtyNeded.getText().isEmpty()) {
					MessageFactory.inform("please enter a quantity!");
					return;
				}
				
				updateTotal();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lstVendors.getSelectedIndex() == -1) {
					MessageFactory.inform("please select a vendor!");
					return;
				}
				
				if (!isQtyNeededValid()) {
					return;
				}
				
				CustomSuppliedbyModel c = lstVendors.getSelectedValue();
				if ( Integer.parseInt(txtQtyNeded.getText()) > c.getQuantityAvailable()) {
					txtQtyNeded.setText(c.getQuantityAvailable()+"");
					updateTotal();
				}
				
				add2OrderList();
				updateOrderList();
				updateVendorList();
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lstOrders.getSelectedIndex() == -1) {
					MessageFactory.inform("please select an order!");
					return;
				}
				
				removeFromOrderList();
				updateOrderList();
				updateVendorList();
			}
		});
		
		btnPlaceAnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (orders == null) {
					MessageFactory.inform("please add an order to the order list!");
					return;
				}
				
				OrderController.placeOrder(SSN, orders);
			}
		});
	}
	
	public boolean isQtyNeededValid() {
		// validate data in txtQuantityNeeded 
		String s = txtQtyNeded.getText();
		
		if (s.isEmpty()) {
			MessageFactory.inform("Input quantity needed please!");
			return false;
		}
		
		if (!s.matches("[0-9]+")) {
			MessageFactory.inform("Quantity must contain only number!");
			return false;
		}
		
		return true;
	}
	
	public void updateProductList() {
		List<Product> l = ProductController.getOfficeList();
		
		if (l != null)
			cbxProducts.setModel(new DefaultComboBoxModel<Product>(l.toArray(new Product[0])));
	}
	
	public void updateVendorList() {
		if (cbxProducts.getItemCount() == 0)
			return;
		
		vendors = OrderController.findAvailableVendors( 
					( (Product)cbxProducts.getSelectedItem() ).getProductNum() 
				);
		
		// update the quantity in vendor list
		if (orders != null && orders.size() != 0) {
			for (int i=0, i_size=orders.size() ; i<i_size ; ++i) {
				for (int j=0, j_size=vendors.size() ; j<j_size ; ++j) {
					if (	orders.get(i).getVendorNum() == vendors.get(j).getVendorNum() &&
							orders.get(i).getProductNum() == vendors.get(j).getProductNum()	) {
						
						// found a record, update the quantity needed
						vendors.get(j).setQuantityNeeded(orders.get(i).getQuantityNeeded());
						
					}
				}
			}
		}
		
		if (vendors != null) {
			canUpdateTotal = false;
			lstVendors.setModel(new DefaultComboBoxModel<CustomSuppliedbyModel>(vendors.toArray(new CustomSuppliedbyModel[0])));
			canUpdateTotal = true;
		}
	}
	
	public void updateTotal() {
		if (canUpdateTotal == false)
			return;
		
		CustomSuppliedbyModel c = lstVendors.getSelectedValue();
		txtTotal.setText(c.getUnitPriceAvailable() * Integer.parseInt(txtQtyNeded.getText()) + "");
	}
	
	public void add2OrderList() {
		CustomSuppliedbyModel c = lstVendors.getSelectedValue();
		// update the quantity needed if a row with this vendor and this product exists
		
		// update the quantity in order list
		boolean found = false;
		if (orders.size() != 0) {
			for (int i=0, i_size=orders.size() ; i<i_size ; ++i) {
				if (	orders.get(i).getVendorNum() == c.getVendorNum() &&
						orders.get(i).getProductNum() == c.getProductNum()	) {
					
					// found a record, update the quantity needed
					int newNeed = Integer.parseInt(txtQtyNeded.getText());
					int avai = orders.get(i).getQuantityAvailable();
					int need = orders.get(i).getQuantityNeeded();
					
					if (need+newNeed > avai) {
						orders.get(i).setQuantityNeeded(avai);
					} else {
						orders.get(i).setQuantityNeeded(need+newNeed);
					}					
					
					found = true;
					break;
				}
			}
		}
		
		if (!found) {
			// otherwise, create a new row
			CustomSuppliedbyModel2 nc = new CustomSuppliedbyModel2(
						c.getVendorNum(),
						c.getProductNum(),
						c.getQuantityAvailable(),
						c.getQuantityNeeded(),
						c.getVendorName(),
						c.getProductName()
					);
			
			nc.setQuantityNeeded( Integer.parseInt(txtQtyNeded.getText()) );		
			orders.add(nc);		
		}
	}
	
	public void updateOrderList() {
		if (orders != null)
			lstOrders.setModel(new DefaultComboBoxModel<CustomSuppliedbyModel>(orders.toArray(new CustomSuppliedbyModel[0])));	
	}
	
	public void removeFromOrderList() {
		if (orders != null) {
			for (int i=0, size=orders.size() ; i<size ; ++i) {
				if (	orders.get(i).getVendorNum() == lstOrders.getSelectedValue().getVendorNum() &&
						orders.get(i).getProductNum() == lstOrders.getSelectedValue().getProductNum()	) {
					
					orders.remove(i);
					break;
					
				}
			}
		}
	}
}
