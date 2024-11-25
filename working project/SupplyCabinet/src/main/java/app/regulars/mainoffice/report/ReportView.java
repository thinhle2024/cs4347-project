package app.regulars.mainoffice.report;

import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;

public class ReportView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tr1;
	private JTable tr2;
	private JTable tr3;

	/**
	 * Create the panel.
	 */
	public ReportView() {

		JScrollPane sp1 = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Weekly orders");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblNewLabel_1 = new JLabel("Products shipped to branches");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JScrollPane sp2 = new JScrollPane();
		
		JLabel lblNewLabel_1_1 = new JLabel("Products still in stock");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JScrollPane sp3 = new JScrollPane();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(sp1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(sp2, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE)
						.addComponent(sp3, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sp1, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sp2, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sp3, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		tr3 = new JTable();
		sp3.setViewportView(tr3);
		
		tr2 = new JTable();
		sp2.setViewportView(tr2);

		List<ReportModel> l = ReportController.generateMainOfficeWeeklyOrders();
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Request date");
		tableModel.addColumn("Arrival date");
		tableModel.addColumn("Full name");
		tableModel.addColumn("Office name");
		tableModel.addColumn("Vendor name");
		tableModel.addColumn("Product name");		
		tableModel.addColumn("Quantity");
		tableModel.addColumn("Unit price");
		tableModel.addColumn("Total");

		for (ReportModel x : l) {
			tableModel.insertRow(tableModel.getRowCount(),
					new Object[] { 
							x.getRequestDate(), 
							x.getArrivalDate(),
							x.getFullName(), 
							x.getOfficeName(), 
							x.getProductName(),
							x.getVendorName(), 
							x.getQuantity(), 
							x.getUnitPrice(),
							x.getQuantity() * x.getUnitPrice()
					});
		}

		tr1 = new JTable(tableModel);
		sp1.setViewportView(tr1);
		
		List<app.regulars.branchoffice.report.ReportModel> ll = ReportController.generateMainOfficeShippedProducts();

		DefaultTableModel tm2 = new DefaultTableModel();
		tm2.addColumn("Date");
		tm2.addColumn("Product name");
		tm2.addColumn("Quantity");

		for (app.regulars.branchoffice.report.ReportModel x : ll) {
			tm2.insertRow(tm2.getRowCount(),
					new Object[] { 
							x.getDate(),
							x.getProductName(),
							x.getQuantity()
					});
		}

		tr2 = new JTable(tm2);
		sp2.setViewportView(tr2);	
		
		List<app.regulars.branchoffice.report.ReportModel> lll = ReportController.generateMainOfficeInstockProducts();

		DefaultTableModel tm3 = new DefaultTableModel();
		tm3.addColumn("Product name");
		tm3.addColumn("Quantity");
		
		for (app.regulars.branchoffice.report.ReportModel x : lll) {
			tm3.insertRow(tm3.getRowCount(),
					new Object[] {
							x.getProductName(),
							x.getQuantity()
					});
		}

		tr3 = new JTable(tm3);
		sp3.setViewportView(tr3);
		
		setLayout(groupLayout);

	}
}
