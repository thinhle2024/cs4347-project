package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the shipped database table.
 * 
 */
@Entity
@Table(name="shipped")
@NamedQuery(name="Shipped.findAll", query="SELECT s FROM Shipped s")
public class Shipped implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=true)
	private int id;

	@Column(nullable=true)
	private int quantityShipped;

	@Temporal(TemporalType.DATE)
	private Date shippedDate;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="officeNum", nullable=true)
	private Office office;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productNum", nullable=true)
	private Product product;

	public Shipped() {
	}

	public Shipped(Office office, Product product, int quantityShipped) {
		super();
		this.office = office;
		this.product = product;
		this.quantityShipped = quantityShipped;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantityShipped() {
		return this.quantityShipped;
	}

	public void setQuantityShipped(int quantityShipped) {
		this.quantityShipped = quantityShipped;
	}

	public Date getShippedDate() {
		return this.shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Office getOffice() {
		return this.office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}