package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=9)
	private String ssn;

	@Column(nullable=false, length=15)
	private String firstName;

	@Column(nullable=false, length=15)
	private String lastName;

	@Column(nullable=false, length=1)
	private String middleInitial;

	//bi-directional one-to-one association to Administrator
	@OneToOne(mappedBy="employee")
	private Administrator administrator;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="officeNum", nullable=false)
	private Office office;

	//bi-directional many-to-one association to Orderplacement
	@OneToMany(mappedBy="employee")
	private List<Orderplacement> orderplacements;

	public Employee() {
	}

	public Employee(String ssn) {
		super();
		this.ssn = ssn;
	}

	public Employee(String ssn, String firstName, String middleInitial, String lastName, int officeNum) {
		super();
		this.ssn = ssn;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.office = new Office(officeNum);
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return this.middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Office getOffice() {
		return this.office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public List<Orderplacement> getOrderplacements() {
		return this.orderplacements;
	}

	public void setOrderplacements(List<Orderplacement> orderplacements) {
		this.orderplacements = orderplacements;
	}

	public Orderplacement addOrderplacement(Orderplacement orderplacement) {
		getOrderplacements().add(orderplacement);
		orderplacement.setEmployee(this);

		return orderplacement;
	}

	public Orderplacement removeOrderplacement(Orderplacement orderplacement) {
		getOrderplacements().remove(orderplacement);
		orderplacement.setEmployee(null);

		return orderplacement;
	}

}