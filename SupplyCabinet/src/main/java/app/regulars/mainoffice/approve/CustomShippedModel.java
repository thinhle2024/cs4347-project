package app.regulars.mainoffice.approve;

public class CustomShippedModel {

	private String officeName;
	private String productName;
	private int quantityNeeded;
	private int officeNum;
	private int productNum;
	
	public CustomShippedModel() {
		
	}

	public CustomShippedModel(String officeName, String productName, int quantityNeeded, int officeNum,
			int productNum) {
		super();
		this.officeName = officeName;
		this.productName = productName;
		this.quantityNeeded = quantityNeeded;
		this.officeNum = officeNum;
		this.productNum = productNum;
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

	public int getQuantityNeeded() {
		return quantityNeeded;
	}

	public void setQuantityNeeded(int quantityNeeded) {
		this.quantityNeeded = quantityNeeded;
	}

	public int getOfficeNum() {
		return officeNum;
	}

	public void setOfficeNum(int officeNum) {
		this.officeNum = officeNum;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	
	public String toString() {
		return officeName + " / " + productName + " / " + quantityNeeded;
	}
}
