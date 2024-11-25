package app.regulars.mainoffice.order;

public class CustomSuppliedbyModel {

	private int orderNumber; // for the order placement table

	private int vendorNum; // from the suppliedby table
	private int productNum;
	private int quantityAvailable;
	private double unitPriceAvailable;

	private int quantityNeeded; // user input

	private String vendorName; // from the vendor table
	private String productName; // from the product table

	public CustomSuppliedbyModel() {
		super();
	}

	public CustomSuppliedbyModel(
			int orderNumber, 
			int vendorNum, 
			int productNum, 
			int quantityAvailable,
			double unitPriceAvailable, 
			int quantityNeeded, 
			String vendorName, 
			String productName) {
		super();
		this.orderNumber = orderNumber;
		this.vendorNum = vendorNum;
		this.productNum = productNum;
		this.quantityAvailable = quantityAvailable;
		this.unitPriceAvailable = unitPriceAvailable;
		this.quantityNeeded = quantityNeeded;
		this.vendorName = vendorName;
		this.productName = productName;
	}
	
	public CustomSuppliedbyModel(
			int vendorNum, 
			int productNum, 
			int quantityAvailable, 
			double unitPriceAvailable,
			String vendorName, 
			String productName) {
		super();
		this.vendorNum = vendorNum;
		this.productNum = productNum;
		this.quantityAvailable = quantityAvailable;
		this.unitPriceAvailable = unitPriceAvailable;
		this.vendorName = vendorName;
		this.productName = productName;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getVendorNum() {
		return vendorNum;
	}

	public void setVendorNum(int vendorNum) {
		this.vendorNum = vendorNum;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public double getUnitPriceAvailable() {
		return unitPriceAvailable;
	}

	public void setUnitPriceAvailable(double unitPriceAvailable) {
		this.unitPriceAvailable = unitPriceAvailable;
	}

	public int getQuantityNeeded() {
		return quantityNeeded;
	}

	public void setQuantityNeeded(int quantityNeeded) {
		this.quantityNeeded = quantityNeeded;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String toString() {
		return vendorName + " / " + quantityAvailable + " / " + quantityNeeded + " / $" + unitPriceAvailable;
	}
}
