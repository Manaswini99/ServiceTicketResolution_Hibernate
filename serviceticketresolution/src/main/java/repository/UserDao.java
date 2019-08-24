package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo.DepartmentBean;
import pojo.TicketBean;

public class UserDao {
	private static final String PERSISTENCE_UNIT_NAME = "servicetickets";
	 
	public  boolean createticket(TicketBean ticketbean) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entitymanager = factory.createEntityManager();

		entitymanager.getTransaction().begin();
		entitymanager.persist(ticketbean);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		return true;
	}

	public  DepartmentBean getdepart_id(String department_name) throws SQLException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entitymanager = factory.createEntityManager();

		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT s FROM DepartmentBean s where department_name=:dept");
		query.setParameter("dept", department_name);
		ResultSet rs = (ResultSet) query;
		rs.next();
		DepartmentBean bd = new DepartmentBean();
		bd.setDepartment_id(rs.getInt(0));
		bd.setDepartment_name(department_name);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		return bd;
	}

	public  ArrayList<TicketBean> getTicketList() {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entitymanager = factory.createEntityManager();
			ArrayList<TicketBean> list = new ArrayList<TicketBean>(entitymanager.createQuery("SELECT s FROM TicketBean s", TicketBean.class).getResultList());
			return list;
		
	}

}
