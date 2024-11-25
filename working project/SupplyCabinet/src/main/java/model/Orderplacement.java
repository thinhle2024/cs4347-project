package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orderplacement database table.
 * 
 */
@Entity
@Table(name="orderplacement")
@NamedQuery(name="Orderplacement.findAll", query="SELECT o FROM Orderplacement o")
public class Orderplacement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int orderNum;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date requestDate;

	//bi-directional many-to-one association to Lineitem
	@OneToMany(mappedBy="orderplacement")
	private List<Lineitem> lineitems;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="SSN", nullable=false)
	private Employee employee;

	public Orderplacement() {
	}

	public Orderplacement(int orderNum) {
		super();
		this.orderNum = orderNum;
	}

	public int getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public List<Lineitem> getLineitems() {
		return this.lineitems;
	}

	public void setLineitems(List<Lineitem> lineitems) {
		this.lineitems = lineitems;
	}

	public Lineitem addLineitem(Lineitem lineitem) {
		getLineitems().add(lineitem);
		lineitem.setOrderplacement(this);

		return lineitem;
	}

	public Lineitem removeLineitem(Lineitem lineitem) {
		getLineitems().remove(lineitem);
		lineitem.setOrderplacement(null);

		return lineitem;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}