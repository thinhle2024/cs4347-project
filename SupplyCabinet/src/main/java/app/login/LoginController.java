package app.login;
import javax.persistence.EntityManager;

import app.PUFactory;
import model.Administrator;
import model.Employee;
import model.Mainoffice;

public class LoginController {
	
	private EntityManager em;
	
	public LoginController() {	
		// pre-load the em factory so succeeding calls to it will run faster 
		em = PUFactory.FACTORY.createEntityManager();
		em.close();
	}
	
	/**
	 * Check SSN
	 * @param SSN
	 * @return officeNum
	 */
	public int checkSSN(String SSN) {
		int officeNum = - 1;
		Employee employee;
		
		em = PUFactory.FACTORY.createEntityManager();
		
		employee = (Employee) em.find(Employee.class, SSN);
		
		if (employee != null) {
		
			em.refresh(employee);
			
			officeNum = employee.getOffice().getOfficeNum();
				
		}
		
		em.close();
		
		return officeNum;
	}
	
	public int getMainOfficeNum() {
		Mainoffice office;
		
		em = PUFactory.FACTORY.createEntityManager();
		
		office = (Mainoffice) em.createNamedQuery("Mainoffice.findAll").getSingleResult();
		
		em.close();
		
		if (office == null)
			return -1;
		else 
			return office.getOfficeNum();
	}
	
	public String getAdminSSN() {
		Administrator admin;
		
		em = PUFactory.FACTORY.createEntityManager();
		
		admin = (Administrator) em.createNamedQuery("Administrator.findAll").getSingleResult();
		
		em.close();
		
		if (admin == null)
			return null;
		else 
			return admin.getSsn();
	}
}
