package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the office database table.
 * 
 */
@Entity
@Table(name="office")
@NamedQuery(name="Office.findAll", query="SELECT o FROM Office o")
public class Office implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int officeNum;

	@Column(length=30)
	private String address;

	@Column(nullable=false, length=15)
	private String officeName;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="office")
	private List<Employee> employees;

	//bi-directional one-to-one association to Mainoffice
	@OneToOne(mappedBy="office")
	private Mainoffice mainoffice;

	//bi-directional many-to-one association to Shipped
	@OneToMany(mappedBy="office")
	private List<Shipped> shippeds;

	//bi-directional many-to-one association to Sold
	@OneToMany(mappedBy="office")
	private List<Sold> solds;

	public Office() {
	}

	public Office(int officeNum) {
		super();
		this.officeNum = officeNum;
	}

	public Office(String officeName, String address) {
		super();
		this.officeName = officeName;
		this.address = address;
	}

	public Office(int officeNum, String officeName, String address) {
		super();
		this.officeNum = officeNum;
		this.officeName = officeName;
		this.address = address;
	}

	public int getOfficeNum() {
		return this.officeNum;
	}

	public void setOfficeNum(int officeNum) {
		this.officeNum = officeNum;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOfficeName() {
		return this.officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setOffice(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setOffice(null);

		return employee;
	}

	public Mainoffice getMainoffice() {
		return this.mainoffice;
	}

	public void setMainoffice(Mainoffice mainoffice) {
		this.mainoffice = mainoffice;
	}

	public List<Shipped> getShippeds() {
		return this.shippeds;
	}

	public void setShippeds(List<Shipped> shippeds) {
		this.shippeds = shippeds;
	}

	public Shipped addShipped(Shipped shipped) {
		getShippeds().add(shipped);
		shipped.setOffice(this);

		return shipped;
	}

	public Shipped removeShipped(Shipped shipped) {
		getShippeds().remove(shipped);
		shipped.setOffice(null);

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
		sold.setOffice(this);

		return sold;
	}

	public Sold removeSold(Sold sold) {
		getSolds().remove(sold);
		sold.setOffice(null);

		return sold;
	}

}