package app.regulars.branchoffice.report;

import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

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

		ReportController bc = new ReportController();		

		JScrollPane sp1 = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Weekly orders");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblProductsNotShipped = new JLabel("Products in stock");
		lblProductsNotShipped.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JScrollPane sp2 = new JScrollPane();
		
		JLabel lblNewLabel_1_1 = new JLabel("Products not yet received from main office");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JScrollPane sp3 = new JScrollPane();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(sp3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
						.addComponent(sp2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
						.addComponent(sp1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING)
						.addComponent(lblProductsNotShipped, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sp1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblProductsNotShipped, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sp2, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sp3, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);

		List<ReportModel> l = bc.generateBranchOfficeWeeklyOrders( 2 );
		
		DefaultTableModel tm1 = new DefaultTableModel();
		tr1 = new JTable(tm1);
		sp1.setViewportView(tr1);
		
		tm1.addColumn("Date");		
		tm1.addColumn("Product name");
		tm1.addColumn("Quantity");
		for (ReportModel x : l) {
			tm1.insertRow(tm1.getRowCount(),
					new Object[] { 
							x.getDate(),							
							x.getProductName(),
							x.getQuantity()
			});
		}
		
		
		List<ReportModel> ll = bc.generateBranchOfficeNotYetReceived(2);
		DefaultTableModel tm2 = new DefaultTableModel();	
		tr2 = new JTable(tm2);
		sp2.setViewportView(tr2);
		
		tm2.addColumn("Product name");
		tm2.addColumn("Quantity");
		for (ReportModel x : ll) {
			tm2.insertRow( tm2.getRowCount(), new Object[] { x.getProductName(), x.getQuantity() } );
		}		
		
		List<ReportModel> lll = bc.generateBranchOfficeNotYetReceived(2);	
		DefaultTableModel tm3 = new DefaultTableModel();		
		tr3 = new JTable(tm3);
		sp3.setViewportView(tr3);	
		
		tm3.addColumn("Product name");
		tm3.addColumn("Quantity");
		
		for (ReportModel x : lll) {
			tm3.insertRow(tm3.getRowCount(),
					new Object[] { 						
							x.getProductName(),
							x.getQuantity()
			});
		}

		setLayout(groupLayout);

	}
}
