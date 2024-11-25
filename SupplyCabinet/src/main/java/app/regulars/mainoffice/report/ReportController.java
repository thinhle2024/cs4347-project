package app.regulars.mainoffice.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import app.PUFactory;

public class ReportController {
	
	private static EntityManager em;
	
	////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public static List<ReportModel> generateReport(String storedProcedureName) {
		List<ReportModel> result;
		List<Object[]> temp;
		
		em = PUFactory.FACTORY.createEntityManager();
		temp = em.createStoredProcedureQuery(storedProcedureName).getResultList();
		em.close();
		
		result = new ArrayList<>();
		
		String requestDate, arrivalDate, fullName, officeName, vendorName, productName;
		int quantity;
		double unitPrice;
		for (Object[] x : temp) {
			requestDate = 	x[0].toString();
			arrivalDate = 	x[1].toString();
			fullName = 		x[2].toString();
			officeName = 	x[3].toString();
			vendorName = 	x[4].toString();
			productName = 	x[5].toString();
			
			quantity = 		(x[6].getClass() == BigDecimal.class ? ((BigDecimal)x[6]).intValue() : (int)x[6]);
			unitPrice = 	(double)x[7];
			
			result.add(new ReportModel(requestDate, arrivalDate, fullName, officeName, productName, vendorName, quantity, unitPrice));
		}
		
		return result;
	}
	
	/////////////////////////////////////////////////// generateMainOfficeWeeklyOrders
	public static List<ReportModel> generateMainOfficeWeeklyOrders() {
		return generateReport("generateMainOfficeWeeklyOrders");
	}
	
	///////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public static List<app.regulars.branchoffice.report.ReportModel> generateReport2(String storedProcedureName) {
		List<app.regulars.branchoffice.report.ReportModel> result;
		List<Object[]> temp;
		
		em = PUFactory.FACTORY.createEntityManager();
		
		temp = em.createStoredProcedureQuery(storedProcedureName)
				.getResultList();
		
		em.close();
		
		result = new ArrayList<>();
		
		String date, productName;
		int quantity;

		for (Object[] x : temp) {
			date =		 		x[0] == null ? "" : x[0].toString();
			productName = 		x[1].toString();
			quantity = 			(x[2].getClass() == BigDecimal.class ? ((BigDecimal) x[2]).intValue() : (int)x[2]);

			result.add(new app.regulars.branchoffice.report.ReportModel(date, productName, quantity));
		}
		
		return result;
	}
	
	/////////////////////////////////////////////////// generateMainOfficeShippedProducts
	
	public static List<app.regulars.branchoffice.report.ReportModel> generateMainOfficeShippedProducts() {
		return generateReport2("generateMainOfficeShippedProducts");
	}
	
	// in stock at main office = received (arrivalDate is not null) + not shipped (shipped date is null)
	/////////////////////////////////////////////////// generateMainOfficeInstockProducts
	public static List<app.regulars.branchoffice.report.ReportModel> generateMainOfficeInstockProducts() {
		return generateReport2("generateMainOfficeInstockProducts");
	}
	
}
