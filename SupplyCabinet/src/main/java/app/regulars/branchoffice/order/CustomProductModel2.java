package app.regulars.branchoffice.order;

public class CustomProductModel2 extends CustomProductModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomProductModel2(int productNum, String productName, int quantityAvailable, int quantityNeeded) {
		super(productNum, productName, quantityAvailable, quantityNeeded);
	}

	public String toString() {
		return getProductName() + " / " + getQuantityNeeded() + " / " + getQuantityAvailable();
	}
}
