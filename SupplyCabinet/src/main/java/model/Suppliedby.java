package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the suppliedby database table.
 * 
 */
@Entity
@Table(name="suppliedby")
@NamedQuery(name="Suppliedby.findAll", query="SELECT s FROM Suppliedby s")
public class Suppliedby implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SuppliedbyPK id;

	@Column(nullable=true)
	private int quantityAvailable;

	@Column(nullable=true)
	private double unitPriceAvailable;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productNum", nullable=true, insertable=true, updatable=true)
	private Product product;

	//bi-directional many-to-one association to Vendor
	@ManyToOne
	@JoinColumn(name="vendorNum", nullable=true, insertable=true, updatable=true)
	private Vendor vendor;

	public Suppliedby() {
	}

	public Suppliedby(Vendor vendor, Product product, int quantityAvailable) {
		super();
		this.vendor = vendor;
		this.product = product;
		this.quantityAvailable = quantityAvailable;
	}

	public Suppliedby(Vendor vendor, Product product, int quantityAvailable, double unitPriceAvailable) {
		super();
		this.vendor = vendor;
		this.product = product;
		this.quantityAvailable = quantityAvailable;
		this.unitPriceAvailable = unitPriceAvailable;
	}

	public Suppliedby(Vendor vendor, Product product) {
		super();
		this.vendor = vendor;
		this.product = product;
	}

	public SuppliedbyPK getId() {
		return this.id;
	}

	public void setId(SuppliedbyPK id) {
		this.id = id;
	}

	public int getQuantityAvailable() {
		return this.quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public double getUnitPriceAvailable() {
		return this.unitPriceAvailable;
	}

	public void setUnitPriceAvailable(double unitPriceAvailable) {
		this.unitPriceAvailable = unitPriceAvailable;
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