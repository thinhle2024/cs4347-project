package app.regulars.mainoffice.report;

public class ReportModel {
	private String requestDate;
	private String arrivalDate;
	private String fullName;
	private String officeName;
	private String vendorName;
	private String productName;	
	private int quantity;
	private double unitPrice;

	public ReportModel() {

	}

	public ReportModel(
			String requestDate, 
			String arrivalDate, 
			String fullName, 
			String officeName,
			String vendorName,
			String productName, 
			int quantity, 
			double unitPrice) {
		super();
		this.requestDate = requestDate;
		this.arrivalDate = arrivalDate;
		this.fullName = fullName;
		this.officeName = officeName;
		this.vendorName = vendorName;
		this.productName = productName;		
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
