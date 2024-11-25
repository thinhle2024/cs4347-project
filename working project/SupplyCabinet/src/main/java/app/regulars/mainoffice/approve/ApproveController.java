package app.regulars.mainoffice.approve;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import app.PUFactory;

public class ApproveController {
	
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static List<CustomShippedModel> getUnapprovedOrderList() {
		List<CustomShippedModel> result;
		List<Object[]> temp;
		
		em = PUFactory.FACTORY.createEntityManager();
		temp = em.createStoredProcedureQuery("findUnApprovedOrders").getResultList();
		em.close();
		
		result = new ArrayList<>();
		
		String officeName, productName;
		int quantity, officeNum, productNum;
		for (Object[] x : temp) {
			officeName = 	x[0].toString();
			productName = 	x[1].toString();
			quantity = 		(int)x[2];
			officeNum = 	(int)x[3];
			productNum = 	(int)x[4];
			
			result.add(new CustomShippedModel(officeName, productName, quantity, officeNum, productNum));
		}
		
		return result;
	}
	
	public static void approve(CustomShippedModel c) {
		Query q;
		
		em = PUFactory.FACTORY.createEntityManager();
		q = em.createNativeQuery("update shipped set shippedDate = curdate() where officeNum = ? and productNum = ?")
				.setParameter(1, c.getOfficeNum())
				.setParameter(2, c.getProductNum());
		
		em.getTransaction().begin();
		q.executeUpdate();		
		em.getTransaction().commit();
		
		em.close();
	}
	
}
