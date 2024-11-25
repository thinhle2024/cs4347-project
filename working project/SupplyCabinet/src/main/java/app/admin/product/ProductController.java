package app.admin.product;

import java.util.List;

import javax.persistence.EntityManager;

import app.MessageFactory;
import app.PUFactory;
import app.admin.office.OfficeController;
import model.Product;

public class ProductController {

	private EntityManager em;

//	em = PUFactory.FACTORY.createEntityManager();
//	em.close();

	////////////////////////////////////////////////////

	public Product createRecord(Product product) {

		if (validateModel(product) == false)
			return null;

		em = PUFactory.FACTORY.createEntityManager();
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		em.refresh(product);
		em.close();

		MessageFactory.inform("Product record created successful!");

		return product;
	}

	public static boolean validateModel(Product product) {
//		MessageFactory.inform("");

		if (product.getProductName().isEmpty()) {
			MessageFactory.inform("Office name must not be empty!");
			return false;
		}

		return true;
	}

	////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public static List<Product> getOfficeList() {
		return (List<Product>) OfficeController.getList("Product.findAll");
	}
	
	////////////////////////////////////////////////////

	public void deleteRecord(Product product) {
		em = PUFactory.FACTORY.createEntityManager();
		try {

			em.getTransaction().begin();
			product = em.find(Product.class, product.getProductNum());
			em.remove(product);
			em.getTransaction().commit();

			product = null;

			MessageFactory.inform("Product record deleted successful!");

		} catch (Exception ex) {

			MessageFactory.inform("Cannot delete this product since it is linked to many line items!");

		} finally {
			em.close();
		}
	}

}
