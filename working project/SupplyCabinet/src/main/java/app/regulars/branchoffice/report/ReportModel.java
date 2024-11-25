package app.regulars.branchoffice.report;

public class ReportModel {
	private String date;
	private String productName;
	private int quantity;

	public ReportModel(String date, String productName, int quantity) {
		super();
		this.date = date;
		this.productName = productName;
		this.quantity = quantity;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
