package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the lineitems database table.
 * 
 */
@Embeddable
public class LineitemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int orderNum;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int vendorNum;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int productNum;

	public LineitemPK() {
	}
	public int getOrderNum() {
		return this.orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getVendorNum() {
		return this.vendorNum;
	}
	public void setVendorNum(int vendorNum) {
		this.vendorNum = vendorNum;
	}
	public int getProductNum() {
		return this.productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LineitemPK)) {
			return false;
		}
		LineitemPK castOther = (LineitemPK)other;
		return 
			(this.orderNum == castOther.orderNum)
			&& (this.vendorNum == castOther.vendorNum)
			&& (this.productNum == castOther.productNum);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderNum;
		hash = hash * prime + this.vendorNum;
		hash = hash * prime + this.productNum;
		
		return hash;
	}
}