package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mainoffice database table.
 * 
 */
@Entity
@Table(name="mainoffice")
@NamedQuery(name="Mainoffice.findAll", query="SELECT m FROM Mainoffice m")
public class Mainoffice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int officeNum;

	//bi-directional one-to-one association to Office
	@OneToOne
	@JoinColumn(name="officeNum", nullable=false, insertable=false, updatable=false)
	private Office office;

	public Mainoffice() {
	}

	public int getOfficeNum() {
		return this.officeNum;
	}

	public void setOfficeNum(int officeNum) {
		this.officeNum = officeNum;
	}

	public Office getOffice() {
		return this.office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

}