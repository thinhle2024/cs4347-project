package app.regulars.branchoffice.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;

import app.PUFactory;

public class ReportController {
	protected EntityManager em;
	
	///////////////////////////////////////////////// 
	
	@SuppressWarnings("unchecked")
	public static List<ReportModel> generateReport(int officeNum, String storedProcedureName) {
		List<ReportModel> result;
		List<Object[]> temp;
		
		EntityManager sem = PUFactory.FACTORY.createEntityManager();
		
		temp = sem.createStoredProcedureQuery(storedProcedureName)
				.registerStoredProcedureParameter("officeNum", Integer.class, ParameterMode.IN)
				.setParameter("officeNum", officeNum)
				.getResultList();
		
		sem.close();
		
		result = new ArrayList<>();
		
		String date, productName;
		int quantity;

		for (Object[] x : temp) {
			date =		 		x[0] == null ? "" : x[0].toString();
			productName = 		x[1].toString();
			quantity = 			(x[2].getClass() == BigDecimal.class ? ((BigDecimal) x[2]).intValue() : (int)x[2]);

			result.add(new ReportModel(date, productName, quantity));
		}
		
		return result;
	}
	
	///////////////////////////////////////////////// generateBranchOfficeWeeklyOrders
	
	public List<ReportModel> generateBranchOfficeWeeklyOrders(int officeNum) {
		return generateReport(officeNum, "generateBranchOfficeWeeklyOrders");
	}
	
	///////////////////////////////////////////////// generateBranchOfficeInstockQuantity
	
	public List<ReportModel> generateBranchOfficeInstockQuantity(int officeNum) {
		return generateReport(officeNum, "generateBranchOfficeInstockQuantity	");
	}
	
	///////////////////////////////////////////////// generateBranchOfficeNotYetReceived
	
	public List<ReportModel> generateBranchOfficeNotYetReceived(int officeNum) {
		return generateReport(officeNum, "generateBranchOfficeNotYetReceived");
	}


}
