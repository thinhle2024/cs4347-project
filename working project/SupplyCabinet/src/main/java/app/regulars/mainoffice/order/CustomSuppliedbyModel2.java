package app.regulars.mainoffice.order;

public class CustomSuppliedbyModel2 extends CustomSuppliedbyModel {

	public CustomSuppliedbyModel2(
			int vendorNum, 
			int productNum, 
			int quantityAvailable, 
			int quantityNeeded,
			String vendorName, 
			String productName) {
		super(vendorNum, productNum, quantityAvailable, quantityNeeded, vendorName, productName);
	}
	
	public String toString() {
		return getVendorName() + " / " + getProductName() + " / " + getQuantityNeeded();
	}
}
