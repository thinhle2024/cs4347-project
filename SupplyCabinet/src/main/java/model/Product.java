package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int productNum;

	@Column(nullable=false, length=15)
	private String productName;

	//bi-directional many-to-one association to Lineitem
	@OneToMany(mappedBy="product")
	private List<Lineitem> lineitems;

	//bi-directional many-to-one association to Shipped
	@OneToMany(mappedBy="product")
	private List<Shipped> shippeds;

	//bi-directional many-to-one association to Sold
	@OneToMany(mappedBy="product")
	private List<Sold> solds;

	//bi-directional many-to-one association to Suppliedby
	@OneToMany(mappedBy="product")
	private List<Suppliedby> suppliedbies;

	public Product() {
	}

	public Product(int productNum) {
		super();
		this.productNum = productNum;
	}

	public Product(int productNum, String productName) {
		super();
		this.productNum = productNum;
		this.productName = productName;
	}

	public Product(String productName) {
		super();
		this.productName = productName;
	}

	public int getProductNum() {
		return this.productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Lineitem> getLineitems() {
		return this.lineitems;
	}

	public void setLineitems(List<Lineitem> lineitems) {
		this.lineitems = lineitems;
	}

	public Lineitem addLineitem(Lineitem lineitem) {
		getLineitems().add(lineitem);
		lineitem.setProduct(this);

		return lineitem;
	}

	public Lineitem removeLineitem(Lineitem lineitem) {
		getLineitems().remove(lineitem);
		lineitem.setProduct(null);

		return lineitem;
	}

	public List<Shipped> getShippeds() {
		return this.shippeds;
	}

	public void setShippeds(List<Shipped> shippeds) {
		this.shippeds = shippeds;
	}

	public Shipped addShipped(Shipped shipped) {
		getShippeds().add(shipped);
		shipped.setProduct(this);

		return shipped;
	}

	public Shipped removeShipped(Shipped shipped) {
		getShippeds().remove(shipped);
		shipped.setProduct(null);

		return shipped;
	}

	public List<Sold> getSolds() {
		return this.solds;
	}

	public void setSolds(List<Sold> solds) {
		this.solds = solds;
	}

	public Sold addSold(Sold sold) {
		getSolds().add(sold);
		sold.setProduct(this);

		return sold;
	}

	public Sold removeSold(Sold sold) {
		getSolds().remove(sold);
		sold.setProduct(null);

		return sold;
	}

	public List<Suppliedby> getSuppliedbies() {
		return this.suppliedbies;
	}

	public void setSuppliedbies(List<Suppliedby> suppliedbies) {
		this.suppliedbies = suppliedbies;
	}

	public Suppliedby addSuppliedby(Suppliedby suppliedby) {
		getSuppliedbies().add(suppliedby);
		suppliedby.setProduct(this);

		return suppliedby;
	}

	public Suppliedby removeSuppliedby(Suppliedby suppliedby) {
		getSuppliedbies().remove(suppliedby);
		suppliedby.setProduct(null);

		return suppliedby;
	}

	public String toString() {
		return productName;
	}
}