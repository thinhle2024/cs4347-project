package app.regulars.branchoffice.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import app.PUFactory;
import app.admin.product.ProductController;
import app.regulars.branchoffice.report.ReportModel;
import model.Office;
import model.Product;
import model.Shipped;

public class OrderController {
	
	private static EntityManager em;
	
	////////////////////////////////////////////////////
	
	public static List<ReportModel> generateMainOfficeInstockProducts() {
		return app.regulars.mainoffice.report.ReportController.generateMainOfficeInstockProducts();
	}
	
	////////////////////////////////////////////////////
	
	public static List<CustomProductModel> getCustomProductList() {
		List<CustomProductModel> result = new ArrayList<CustomProductModel>();
		
		List<Product> pl = ProductController.getOfficeList();
		List<ReportModel> instockProductList = generateMainOfficeInstockProducts();
		
		for (int i=0, i_size=instockProductList.size() ; i<i_size ; ++i) {
			for (int j=0, j_size=pl.size() ; j<j_size ; ++j) {
				if (instockProductList.get(i).getProductName().equals(pl.get(j).getProductName())) {
					result.add(new CustomProductModel(
								pl.get(j).getProductNum(),
								pl.get(j).getProductName(),
								instockProductList.get(i).getQuantity(),
								0
							));
				}
			}
		}
		
		return result;
	}
	
	////////////////////////////////////////////////////
	
	public static void placeOrder(int officeNum, List<CustomProductModel2> l) {
		em = PUFactory.FACTORY.createEntityManager();
		
		int need;
		int avai;
		
		em.getTransaction().begin();
		
		for (int i=0;i<l.size();++i) {			
			
			need = l.get(i).getQuantityNeeded(); 
			avai = l.get(i).getQuantityAvailable();
			
			if (need > avai) {
				em.persist(new Shipped(new Office(officeNum), new Product(l.get(i).getProductNum()), avai));
				em.persist(new Shipped(new Office(officeNum), new Product(l.get(i).getProductNum()), need - avai));
			} else 
				em.persist(new Shipped(new Office(officeNum), new Product(l.get(i).getProductNum()), need));
			
			em.getTransaction().commit();
			em.getTransaction().begin();			
		}
		
		em.close();
	}
	
	////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////
}
