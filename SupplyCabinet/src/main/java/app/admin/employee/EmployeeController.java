package app.admin.employee;

import java.util.List;

import javax.persistence.EntityManager;

import app.MessageFactory;
import app.PUFactory;
import app.admin.office.OfficeController;
import model.Employee;
import model.Office;

public class EmployeeController {

	private EntityManager em;

//	em = PUFactory.FACTORY.createEntityManager();
//	em.close();

	////////////////////////////////////////////////////

	public Employee createRecord(Employee employee) {

		if (validateModel(employee) == false)
			return null;

		em = PUFactory.FACTORY.createEntityManager();
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		em.refresh(employee);
		em.close();

		MessageFactory.inform("Employee record created successful!");

		return employee;
	}

	public static boolean validateModel(Employee employee) {
//		MessageFactory.inform("");

		if (employee.getSsn().isEmpty()) {
			MessageFactory.inform("SSN must not be empty!");
			return false;
		} else if (employee.getFirstName().isEmpty()) {
			MessageFactory.inform("First name must not be empty!");
			return false;
		} else if (employee.getLastName().isEmpty()) {
			MessageFactory.inform("Last name must not be empty!");
			return false;
		}

		return true;
	}

	////////////////////////////////////////////////////
	public static List<Office> getOfficeList() {
		return OfficeController.getOfficeList();
	}
	
	////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public static List<Employee> getEmployeeList() {		
		return (List<Employee>) OfficeController.getList("Employee.findAll");
	}

	public void deleteRecord(Employee employee) {
		em = PUFactory.FACTORY.createEntityManager();
		try {

			em.getTransaction().begin();
			employee = em.find(Employee.class, employee.getSsn());
			em.remove(employee);
			em.getTransaction().commit();

			employee = null;

			MessageFactory.inform("Employee record deleted successful!");

		} catch (Exception ex) {

			MessageFactory.inform("Cannot delete this employee since it is linked to many order placements!");

		} finally {
			em.close();
		}
	}
}
