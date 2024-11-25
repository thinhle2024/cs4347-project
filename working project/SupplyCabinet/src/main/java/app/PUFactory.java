package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PUFactory {
	
	public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("SupplyCabinet");

}
