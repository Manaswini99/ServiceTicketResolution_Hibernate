package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo.*;

public class TicketDao {
	private static final String PERSISTENCE_UNIT_NAME = "servicetickets";

	public int complete(String user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery(
				"SELECT s FROM TicketBean s where status=:status and serviceengineer=:serviceengineer",
				TicketBean.class);
		query.setParameter("status", "ongoing");
		query.setParameter("serviceengineer", user);
		List<TicketBean> list = query.getResultList();
		if (list.size() > 0) {
//			System.out.println(list.get(0).getTicketid());
			entitymanager.getTransaction().commit();
			setcompletedate(list.get(0).getTicketid());
			return list.get(0).getTicketid();
		} else {
			entitymanager.getTransaction().commit();
			return 0;
		}
	}

	private void setcompletedate(int ticketid) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		TicketBean ticket = entitymanager.find(TicketBean.class, ticketid);
		ticket.setCompleted_date(LocalDate.now());
		ticket.setStatus("completed");
		entitymanager.persist(ticket);
		entitymanager.getTransaction().commit();

	}

	public  Boolean assignPendingTicket(String user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery(
				"SELECT s FROM TicketBean s where status=:status and serviceengineer=:serviceengineer order by start_date",
				TicketBean.class);
		query.setParameter("status", "pending");
		query.setParameter("serviceengineer", user);
		List<TicketBean> list = query.getResultList();
		if (list.size() > 0) {
			int ticket_to_be_assigned = list.get(0).getTicketid();
			entitymanager.getTransaction().commit();
			updateStatus(ticket_to_be_assigned);
			updateTicketsWorked(user, list.get(0).getAcutal_priority());
			return true;
			
		} else {

			entitymanager.getTransaction().commit();
			updateCurrentWorkingPriority(user);
			return false;
		}

	}

	private  void updateCurrentWorkingPriority(String user) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		ServiceEngineer engineer = entitymanager.find(ServiceEngineer.class, user);
		engineer.setCurrentpriority_ticket("no");
		engineer.setNo_of_tickets_worked(engineer.getNo_of_tickets_worked()+1);
		entitymanager.persist(engineer);
		entitymanager.getTransaction().commit();

	}

	private  void updateTicketsWorked(String user, String priority) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		ServiceEngineer engineer = entitymanager.find(ServiceEngineer.class, user);
		engineer.setCurrentpriority_ticket(priority);
		engineer.setNo_of_tickets_worked(engineer.getNo_of_tickets_worked() + 1);
		entitymanager.persist(engineer);
		entitymanager.getTransaction().commit();
	}

	private  void updateStatus(int ticket_to_be_assigned) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		TicketBean ticket = entitymanager.find(TicketBean.class, ticket_to_be_assigned);
		ticket.setStatus("ongoing");
		entitymanager.persist(ticket);
		entitymanager.getTransaction().commit();

	}

	public  ArrayList<TicketBean> viewtickets(String user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		ArrayList<TicketBean> list = (ArrayList<TicketBean>) entitymanager.createQuery("SELECT s FROM TicketBean s where serviceengineer=:serviceengineer", TicketBean.class).setParameter("serviceengineer", user).getResultList();
		entitymanager.getTransaction().commit();
		return list;
		
	}

	public  boolean updatePriority(TicketBean ticket) {
		int ticket_id=ticket.getTicketid();
		String priority=ticket.getAcutal_priority();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		TicketBean newticket=entitymanager.find(TicketBean.class, ticket_id);
		newticket.setAcutal_priority(priority);
		entitymanager.persist(newticket);
		entitymanager.getTransaction().commit();
		return true;
	}
}
