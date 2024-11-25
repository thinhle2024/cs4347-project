package app.login;

public class LoginInfoModel {
	private String SSN;
	private int officeNum;
	private boolean isMainOffice;
	private boolean isAdministrator;
	
	public LoginInfoModel() {
		super();
		this.SSN = new String();
		officeNum = -1;
		this.isMainOffice = false;
	}
	
	public LoginInfoModel(String SSN, int officeNum, boolean isMainOffice, boolean isAdministrator) {
		super();
		this.SSN = SSN;
		this.officeNum = officeNum;
		this.isMainOffice = isMainOffice;
		this.isAdministrator = isAdministrator;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	public int getOfficeNum() {
		return officeNum;
	}

	public void setOfficeNum(int officeNum) {
		this.officeNum = officeNum;
	}

	public boolean isMainOffice() {
		return isMainOffice;
	}

	public void setMainOffice(boolean isMainOffice) {
		this.isMainOffice = isMainOffice;
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
	
	
}
