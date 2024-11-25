package app.admin.vendor;

import java.util.List;

import javax.persistence.EntityManager;

import app.MessageFactory;
import app.PUFactory;
import app.admin.office.OfficeController;
import model.Vendor;

public class VendorController {

	private EntityManager em;

//	em = PUFactory.FACTORY.createEntityManager();
//	em.close();

	////////////////////////////////////////////////////

	public Vendor createRecord(Vendor vendor) {
		if (validateModel(vendor) == false)
			return null;

		em = PUFactory.FACTORY.createEntityManager();
		em.getTransaction().begin();
		em.persist(vendor);
		em.getTransaction().commit();
		em.refresh(vendor);
		em.close();

		MessageFactory.inform("Vendor record created successful!");

		return vendor;
	}
	
	////////////////////////////////////////////////////

	public static boolean validateModel(Vendor vendor) {
//		MessageFactory.inform("");

		if (vendor.getVendorName().isEmpty()) {
			MessageFactory.inform("Name must not be empty!");
			return false;
		} else if (vendor.getPhoneNum().isEmpty()) {
			MessageFactory.inform("Phone number must not be empty!");
			return false;
		} else if (vendor.getAddress().isEmpty()) {
			MessageFactory.inform("Address must not be empty!");
			return false;
		}

		return true;
	}
	
	////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public static List<Vendor> getVendorList() {		
		return (List<Vendor>) OfficeController.getList("Vendor.findAll");
	}
	
	////////////////////////////////////////////////////

	public void deleteRecord(Vendor vendor) {
		em = PUFactory.FACTORY.createEntityManager();
		try {

			em.getTransaction().begin();
			vendor = em.find(Vendor.class, vendor.getVendorNum());
			em.remove(vendor);
			em.getTransaction().commit();

			vendor = null;

			MessageFactory.inform("Vendor record deleted successful!");

		} catch (Exception ex) {

			MessageFactory.inform("Cannot delete this vendor since it is linked to many products!");

		} finally {
			em.close();
		}
	}
	
	////////////////////////////////////////////////////

}
