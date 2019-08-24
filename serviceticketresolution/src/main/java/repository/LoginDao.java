package repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pojo.Credentials;
import pojo.DepartmentBean;

public class LoginDao {
	private static final String PERSISTENCE_UNIT_NAME = "servicetickets";
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	static EntityManager entitymanager = factory.createEntityManager();

	// read the existing entries and check
	public Credentials check(Credentials credentials) {
		entitymanager.getTransaction().begin();
		Credentials a = entitymanager.find(Credentials.class, credentials.getUsername());
		entitymanager.getTransaction().commit();
		return a;

	}
	public static  ArrayList<DepartmentBean> getDepartmentList(){
		
		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>( entitymanager.createQuery("SELECT s FROM DepartmentBean s", DepartmentBean.class).getResultList());
		
		return list;
	}

}
