package app.regulars.branchoffice.order;

import model.Product;

public class CustomProductModel extends Product {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int quantityAvailable;
	private int quantityNeeded;
	
	
	
	public CustomProductModel() {
		super();
	}

	public CustomProductModel(int productNum, String productName, int quantityAvailable) {
		super(productNum, productName);
		this.quantityAvailable = quantityAvailable;
	}

	public CustomProductModel(int productNum, String productName, int quantityAvailable, int quantityNeeded) {
		super(productNum, productName);
		this.quantityAvailable = quantityAvailable;
		this.quantityNeeded = quantityNeeded;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getQuantityNeeded() {
		return quantityNeeded;
	}

	public void setQuantityNeeded(int quantityNeeded) {
		this.quantityNeeded = quantityNeeded;
	}

	public String toString() {
		return getProductName();
	}
	
	public Object[] getObject() {
		return new Object[] {
				getProductNum(),
				getProductName(),
				quantityAvailable
		};
	}
}
