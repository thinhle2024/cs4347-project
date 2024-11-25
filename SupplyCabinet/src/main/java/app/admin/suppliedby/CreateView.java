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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;

import app.admin.product.ProductController;
import app.admin.vendor.VendorController;
import model.Product;
import model.Suppliedby;
import model.Vendor;

public class CreateView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	private SuppliedbyController controller;
	
	private List<Suppliedby> suppliedbys;
	private Suppliedby selectedSuppliedby;
	
	private List<Vendor> vendors;
	private Vendor selectedVendor;
	
	private List<Product> products;
	private Product selectedProduct;
	
	private JTextField txtQuantity;
	private JTextField txtUnitPrice;
	
	JComboBox<Vendor> cbxVendor;
	JComboBox<Product> cbxProduct;
	
	@SuppressWarnings("static-access")
	public CreateView() {
		
		controller = new SuppliedbyController();
		suppliedbys = controller.getSuppliedbyList();
		vendors = VendorController.getVendorList();
		products = ProductController.getOfficeList();
		
		cbxVendor = new JComboBox<>();
		cbxProduct = new JComboBox<>();
		
		cbxVendor.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cbxVendor.setRenderer(new ListCellRenderer<Vendor>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Vendor> list, Vendor value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if (value == null)
					return null;
				return new JLabel(value.getVendorName());
			}
		});
		cbxVendor.setModel(new DefaultComboBoxModel<Vendor>(vendors.toArray(new Vendor[0])));		
				
		cbxProduct.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cbxProduct.setRenderer(new ListCellRenderer<Product>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Product> list, Product value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if (value == null)
					return null;
				return new JLabel(value.getProductName());
			}
		});
		
		cbxProduct.setModel(new DefaultComboBoxModel<Product>(products.toArray(new Product[0])));
		
		JLabel lblVendorname = new JLabel("VendorName");
		lblVendorname.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblPhoneNumber = new JLabel("Product name");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JButton btnCreate = new JButton("Create / Update");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( isAllDigits(txtQuantity.getText()) && isAllDigits(txtUnitPrice.getText()) ) {
					controller.associateVendorProduct(new Suppliedby(
							selectedVendor,
							selectedProduct,
							Integer.parseInt(txtQuantity.getText()),
							Double.parseDouble(txtUnitPrice.getText())
							));
				} else {
					txtQuantity.setText("0");
					txtUnitPrice.setText("0");
				}
				
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblUnitPrice = new JLabel("Unit price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtQuantity.setColumns(10);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtUnitPrice.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtQuantity, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblVendorname, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxVendor, 0, 222, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxProduct, 0, 222, Short.MAX_VALUE)))
					.addContainerGap(16, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUnitPrice, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 105, Short.MAX_VALUE)
						.addComponent(txtUnitPrice, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVendorname, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxVendor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxProduct, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUnitPrice, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUnitPrice, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		cbxVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAllInfomation();
			}
		});
		
		cbxProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAllInfomation();
			}
		});
		
		updateAllInfomation();
		
		setLayout(groupLayout);
	}
	
	@SuppressWarnings("static-access")
	protected void updateAllInfomation() {
		
		if (suppliedbys.size() == 0 || vendors.size() == 0 || products.size() == 0)
			return;

		selectedVendor = (Vendor) cbxVendor.getSelectedItem();
		selectedProduct = (Product) cbxProduct.getSelectedItem();
		
		suppliedbys = controller.getSuppliedbyList();

		// O(n^2), should optimize this
		boolean found = false;
		Suppliedby cursor;
		for (int i=0, size=suppliedbys.size() ; i<size ; ++i) {
			cursor = suppliedbys.get(i);
			if (cursor.getVendor().getVendorNum() == selectedVendor.getVendorNum()) {
				if (cursor.getProduct().getProductNum() == selectedProduct.getProductNum()) {
					selectedSuppliedby = suppliedbys.get(i);
					found = true;
				}
			}
		}
		
		if (found) {
			txtQuantity.setText(selectedSuppliedby.getQuantityAvailable() + "");
			txtUnitPrice.setText(selectedSuppliedby.getUnitPriceAvailable() + "");
		} else {
			txtQuantity.setText("0");
			txtUnitPrice.setText("0");
		}
		
	}
	
	public boolean isAllDigits(String s) {
		if (s.isEmpty())
			return false;
		
		for (int i=0,size=s.length() ; i<size ; ++i) {
			if ( !Character.isDigit(s.charAt(i)) && s.charAt(i) != '.'  ) {
				return false;
			}
		}
		
		return true;
	}
	
}
