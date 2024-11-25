package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vendor database table.
 * 
 */
@Entity
@Table(name="vendor")
@NamedQuery(name="Vendor.findAll", query="SELECT v FROM Vendor v")
public class Vendor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int vendorNum;

	@Column(length=30)
	private String address;

	@Column(length=10)
	private String phoneNum;

	@Column(nullable=false, length=15)
	private String vendorName;

	//bi-directional many-to-one association to Lineitem
	@OneToMany(mappedBy="vendor")
	private List<Lineitem> lineitems;

	//bi-directional many-to-one association to Suppliedby
	@OneToMany(mappedBy="vendor")
	private List<Suppliedby> suppliedbies;

	public Vendor() {
	}

	public Vendor(int vendorNum) {
		super();
		this.vendorNum = vendorNum;
	}

	public Vendor(String vendorName, String phoneNum, String address) {
		super();
		this.vendorName = vendorName;
		this.phoneNum = phoneNum;
		this.address = address;
	}

	public int getVendorNum() {
		return this.vendorNum;
	}

	public void setVendorNum(int vendorNum) {
		this.vendorNum = vendorNum;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public List<Lineitem> getLineitems() {
		return this.lineitems;
	}

	public void setLineitems(List<Lineitem> lineitems) {
		this.lineitems = lineitems;
	}

	public Lineitem addLineitem(Lineitem lineitem) {
		getLineitems().add(lineitem);
		lineitem.setVendor(this);

		return lineitem;
	}

	public Lineitem removeLineitem(Lineitem lineitem) {
		getLineitems().remove(lineitem);
		lineitem.setVendor(null);

		return lineitem;
	}

	public List<Suppliedby> getSuppliedbies() {
		return this.suppliedbies;
	}

	public void setSuppliedbies(List<Suppliedby> suppliedbies) {
		this.suppliedbies = suppliedbies;
	}

	public Suppliedby addSuppliedby(Suppliedby suppliedby) {
		getSuppliedbies().add(suppliedby);
		suppliedby.setVendor(this);

		return suppliedby;
	}

	public Suppliedby removeSuppliedby(Suppliedby suppliedby) {
		getSuppliedbies().remove(suppliedby);
		suppliedby.setVendor(null);

		return suppliedby;
	}

}