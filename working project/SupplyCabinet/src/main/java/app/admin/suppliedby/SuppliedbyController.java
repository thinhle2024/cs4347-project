package app.admin.suppliedby;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import app.MessageFactory;
import app.PUFactory;
import app.admin.office.OfficeController;
import model.Product;
import model.Suppliedby;
import model.Vendor;

public class SuppliedbyController {
	
	private EntityManager em;
	
	////////////////////////////////////////////////////
	
	public boolean checkVendorExistence(Vendor v) {
		em = PUFactory.FACTORY.createEntityManager();
		int old = v.getVendorNum();
		try {

			v = em.find(Vendor.class, v.getVendorNum());
			
			if (v.getVendorNum() == old)			
				return true;
			
			return false;

		} catch (Exception ex) {

			return false;
			
		} finally {
			em.close();
		}
	}

	////////////////////////////////////////////////////
	
	public static boolean checkProductExistence(Product p) {
		EntityManager e = PUFactory.FACTORY.createEntityManager();
		int old = p.getProductNum();
		try {

			p = e.find(Product.class, p.getProductNum());

			if (p.getProductNum() == old)			
				return true;
			
			return false;

		} catch (Exception ex) {

			return false;
			
		} finally {
			e.close();
		}
	}

	////////////////////////////////////////////////////
	
	public boolean updateAssociate(Suppliedby s) {
		Suppliedby result = null;
		em = PUFactory.FACTORY.createEntityManager();
		try {

			Query q = em.createNativeQuery("select * from suppliedby where vendorNum = ? and productNum = ?")
						.setParameter(1, s.getVendor().getVendorNum())
						.setParameter(2, s.getProduct().getProductNum());
			
			em.getTransaction().begin();
			result = (Suppliedby) q.getSingleResult();
			em.getTransaction().commit();

			if (result == null)
				return false;

			return true;

		} catch (Exception ex) {

			return false;

		} finally {
			em.close();
		}
	}
	
	////////////////////////////////////////////////////
	
	public Suppliedby associateVendorProduct(Suppliedby s) {
		
		if (s.getQuantityAvailable() <= 0) {
			MessageFactory.inform("Quantity must > 0!");
			return null;
		}
		
		if (s.getUnitPriceAvailable() <= 0) {
			MessageFactory.inform("Unit price must > 0!");
			return null;
		}
		
		if (!checkVendorExistence(s.getVendor())) {
			return null;
		}
		
		if (!checkProductExistence(s.getProduct())) {
			return null;	
		}			

		try {
			
			if (updateAssociate(s) == false) {
				em = PUFactory.FACTORY.createEntityManager();
				em.getTransaction().begin();
				em.persist(s);
				em.getTransaction().commit();
				em.refresh(s);	

				MessageFactory.inform("Suppliedby record created successful!");
				
				return s;
			}

			MessageFactory.inform("Suppliedby record updated successful!");

			return s;
			
		} catch (Exception ex) {

			MessageFactory.inform("Unsure why this error has occurred!");
			
			return null;
			
		} finally {
			
			em.close();
			
		}
	}
	
	////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public static List<Suppliedby> getSuppliedbyList() {		
		return (List<Suppliedby>) OfficeController.getList("Suppliedby.findAll");
	}
	
	////////////////////////////////////////////////////
	
	public void unlinkVendorProduct(Suppliedby s) {
		em = PUFactory.FACTORY.createEntityManager();
		try {

			Query q = em.createNativeQuery("delete from suppliedby where vendorNum = ? and productNum = ?")
						.setParameter(1, s.getVendor().getVendorNum())
						.setParameter(2, s.getProduct().getProductNum());
			
			em.getTransaction().begin();
			q.executeUpdate();
			em.getTransaction().commit();

			s = null;

			MessageFactory.inform("Suppliedby record deleted successful!");

		} catch (Exception ex) {

			MessageFactory.inform("Cannot delete this Suppliedby since it is linked to many other records!");

		} finally {
			em.close();
		}
	}

	
	////////////////////////////////////////////////////
	
	public Suppliedby associateProductVendor(Suppliedby s) {
		return associateVendorProduct(s);
	}
	
	////////////////////////////////////////////////////
	
	public void unlinkProductVendor(Suppliedby s) {
		unlinkVendorProduct(s);
	}	
}
