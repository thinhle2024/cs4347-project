package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the administrator database table.
 * 
 */
@Entity
@Table(name="administrator")
@NamedQuery(name="Administrator.findAll", query="SELECT a FROM Administrator a")
public class Administrator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=9)
	private String ssn;

	//bi-directional one-to-one association to Employee
	@OneToOne
	@JoinColumn(name="SSN", nullable=false, insertable=false, updatable=false)
	private Employee employee;

	public Administrator() {
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}