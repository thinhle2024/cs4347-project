package app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import app.login.LoginInfoModel;
import app.login.LoginView;

public class SupplyCabinet {

	private LoginInfoModel loginInfo;
	private JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SupplyCabinet().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void start() {
		window = new SupplyCabinet();
		login = new LoginView(window.loginInfo, window);
		login.setVisible(true);
	}

	private SupplyCabinet window;
	private LoginView login;
	private JMenuBar menuBar;
	private JMenu mnAdminFunctions;
	private JMenu mnMainOfficeFunctions;
	private JMenu mnBranchOfficeFunctions;
	private JMenuItem mntmNewMenuItem_10;

	/**
	 * Create the application.
	 */
	public SupplyCabinet() {
		loginInfo = new LoginInfoModel();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Supply Cabinet");

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("file");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem_10 = new JMenuItem("log out");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				start();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_10);
		frame.setLocationRelativeTo(null);
	}

	public void setVisible() {
		
		if (loginInfo.isAdministrator()) {
			addAdminFunction();
			addMainOfficeFunctions();
			addBranchOfficeFunctions();
		} else {
			if (loginInfo.isMainOffice()) {
	
				addMainOfficeFunctions();
	
			} else {
				addBranchOfficeFunctions();
			}
		}

		frame.setVisible(true);
	}

	public void addAdminFunction() {
		mnAdminFunctions = new JMenu("Admin functions");
		menuBar.add(mnAdminFunctions);

		JMenu mnNewMenu_1 = new JMenu("Create");
		mnAdminFunctions.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Office");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.office.CreateView)) {
					panel = null;
					panel = new app.admin.office.CreateView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Office");
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Employee");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.employee.CreateView)) {
					panel = null;
					panel = new app.admin.employee.CreateView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Employee");
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Vendor");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.vendor.CreateView)) {
					panel = null;
					panel = new app.admin.vendor.CreateView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Vendor");
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Product");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.product.CreateView)) {
					panel = null;
					panel = new app.admin.product.CreateView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Product");
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("SuppliedBy");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.suppliedby.CreateView)) {
					panel = null;
					panel = new app.admin.suppliedby.CreateView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("SuppliedBy");
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_2 = new JMenu("Delete");
		mnAdminFunctions.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Office");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.office.DeleteView)) {
					panel = null;
					panel = new app.admin.office.DeleteView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Office");
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Employee");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.employee.DeleteView)) {
					panel = null;
					panel = new app.admin.employee.DeleteView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Employee");
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("SuppliedBy");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.suppliedby.DeleteView)) {
					panel = null;
					panel = new app.admin.suppliedby.DeleteView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("SuppliedBy");
				}
			}
		});

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Product");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.product.DeleteView)) {
					panel = null;
					panel = new app.admin.product.DeleteView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Product");
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Vendor");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.admin.vendor.DeleteView)) {
					panel = null;
					panel = new app.admin.vendor.DeleteView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Vendor");
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);
		mnNewMenu_2.add(mntmNewMenuItem_9);
	}

	public void addMainOfficeFunctions() {
		mnMainOfficeFunctions = new JMenu("Main office functions");
		menuBar.add(mnMainOfficeFunctions);

		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Order products");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.regulars.mainoffice.order.OrderView)) {
					panel = null;
					panel = new app.regulars.mainoffice.order.OrderView(loginInfo.getSSN());
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Vendor");
				}
			}
		});
		mnMainOfficeFunctions.add(mntmNewMenuItem_12);

		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Approve orders");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.regulars.mainoffice.approve.ApproveView)) {
					panel = null;
					panel = new app.regulars.mainoffice.approve.ApproveView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Vendor");
				}
			}
		});
		mnMainOfficeFunctions.add(mntmNewMenuItem_13);

		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Generate reports");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.regulars.mainoffice.report.ReportView)) {
					panel = null;
					panel = new app.regulars.mainoffice.report.ReportView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Vendor");
				}
			}
		});
		mnMainOfficeFunctions.add(mntmNewMenuItem_14);
	}

	public void addBranchOfficeFunctions() {
		mnBranchOfficeFunctions = new JMenu("Branch Office function");
		menuBar.add(mnBranchOfficeFunctions);

		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Order products");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.regulars.branchoffice.order.OrderView)) {
					panel = null;
					panel = new app.regulars.branchoffice.order.OrderView(loginInfo.getOfficeNum());
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Vendor");
				}
			}
		});
		mnBranchOfficeFunctions.add(mntmNewMenuItem_15);

		JMenuItem mntmNewMenuItem_16 = new JMenuItem("Generate report");
		mntmNewMenuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(panel instanceof app.regulars.branchoffice.report.ReportView)) {
					panel = null;
					panel = new app.regulars.branchoffice.report.ReportView();
					frame.setContentPane(panel);
					frame.pack();
					frame.setLocationRelativeTo(null);

					frame.setTitle("Vendor");
				}
			}
		});
		mnBranchOfficeFunctions.add(mntmNewMenuItem_16);
	}
}
