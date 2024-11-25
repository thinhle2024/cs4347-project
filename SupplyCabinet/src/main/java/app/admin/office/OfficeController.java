package app.admin.office;

import java.util.List;

import javax.persistence.EntityManager;

import app.MessageFactory;
import app.PUFactory;
import model.Office;

public class OfficeController {

	private EntityManager em;

//	em = PUFactory.FACTORY.createEntityManager();
//	em.close();

	////////////////////////////////////////////////////

	public Office createRecord(Office office) {

		if (validateModel(office) == false)
			return null;

		em = PUFactory.FACTORY.createEntityManager();
		em.getTransaction().begin();
		em.persist(office);
		em.getTransaction().commit();
		em.refresh(office);
		em.close();

		MessageFactory.inform("Office record created successful!");

		return office;
	}

	public static boolean validateModel(Office office) {
//		MessageFactory.inform("");

		if (office.getOfficeName().isEmpty()) {
			MessageFactory.inform("Office name must not be empty!");
			return false;
		} else if (office.getAddress().isEmpty()) {
			MessageFactory.inform("Office address must not be empty!");
			return false;
		}

		return true;
	}

	////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes" })
	public static List getList(String s) {
		List result;

		final EntityManager em = PUFactory.FACTORY.createEntityManager();
		em.getTransaction().begin();

		result = em.createNamedQuery(s).getResultList();

		em.getTransaction().commit();
		em.close();

		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public static List<Office> getOfficeList() {
		return (List<Office>) getList("Office.findAll");
	}
	
	////////////////////////////////////////////////////

	public void deleteRecord(Office office) {
		em = PUFactory.FACTORY.createEntityManager();
		try {

			em.getTransaction().begin();
			office = em.find(Office.class, office.getOfficeNum());
			em.remove(office);
			em.getTransaction().commit();

			office = null;

			MessageFactory.inform("Office record deleted successful!");

		} catch (Exception ex) {

			MessageFactory.inform("Cannot delete this office since it is linked to many employees!");

		} finally {
			em.close();
		}
	} 
	
	////////////////////////////////////////////////////
}
