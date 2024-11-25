package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sold database table.
 * 
 */
@Entity
@Table(name="sold")
@NamedQuery(name="Sold.findAll", query="SELECT s FROM Sold s")
public class Sold implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=true)
	private int id;

	@Column(nullable=true)
	private int quantitySold;

	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date soldDate;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="officeNum", nullable=true)
	private Office office;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productNum", nullable=true)
	private Product product;

	public Sold() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantitySold() {
		return this.quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	public Date getSoldDate() {
		return this.soldDate;
	}

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
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