package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lineitems database table.
 * 
 */
@Entity
@Table(name="lineitems")
@NamedQuery(name="Lineitem.findAll", query="SELECT l FROM Lineitem l")
public class Lineitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LineitemPK id;

	@Temporal(TemporalType.DATE)
	private Date arrivalDate;

	@Column(nullable=true)
	private int quantity;

	@Column(nullable=true)
	private double unitPrice;

	//bi-directional many-to-one association to Orderplacement
	@ManyToOne
	@JoinColumn(name="orderNum", nullable=true, insertable=true, updatable=true)
	private Orderplacement orderplacement;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productNum", nullable=true, insertable=true, updatable=true)
	private Product product;

	//bi-directional many-to-one association to Vendor
	@ManyToOne
	@JoinColumn(name="vendorNum", nullable=true, insertable=true, updatable=true)
	private Vendor vendor;

	public Lineitem() {
	}

	public Lineitem(Orderplacement orderplacement, Vendor vendor, Product product) {
		super();
		this.orderplacement = orderplacement;
		this.vendor = vendor;
		this.product = product;
	}

	public Lineitem(Orderplacement orderplacement, Vendor vendor, Product product, int quantity, double unitPrice) {
		super();
		this.orderplacement = orderplacement;
		this.vendor = vendor;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public LineitemPK getId() {
		return this.id;
	}

	public void setId(LineitemPK id) {
		this.id = id;
	}

	public Date getArrivalDate() {
		return this.arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Orderplacement getOrderplacement() {
		return this.orderplacement;
	}

	public void setOrderplacement(Orderplacement orderplacement) {
		this.orderplacement = orderplacement;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}