package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo.*;

public class EngineerDao {
	private static final String PERSISTENCE_UNIT_NAME = "servicetickets";
	

	/*
	 * checks if any enginner of tickets issue dept is free and returns engineer id
	 * if any
	 */
	public  String checkDepaertmentEngineers(TicketBean ticketbean) {
		 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT s FROM ServiceEngineer s where department_id=:dept_id and currentpriority_ticket=:curr",
				ServiceEngineer.class);
		query.setParameter("dept_id", ticketbean.getDepartment().getDepartment_id());
		query.setParameter("curr", "no");
		List<ServiceEngineer> list = query.getResultList();
		String service_engineer = "no";
		if(list.size()>0)
		{
			service_engineer = list.get(0).getServiceengineer();
		}
		
		entitymanager.getTransaction().commit();
		
		return service_engineer;
	}

	public  boolean checkPriorities(TicketBean ticketbean) {
		 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery(
				"SELECT t FROM TicketBean t where status=:status and department_id=:dept_id order by requestedend_date desc",
				TicketBean.class);
		query.setParameter("status", "ongoing");
		query.setParameter("dept_id", ticketbean.getDepartment().getDepartment_id());
		List<TicketBean> list = query.getResultList();
		if (list.size() > 0) {
			if (ticketbean.getAcutal_priority().equals("high")) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getAcutal_priority().equalsIgnoreCase("low")
							|| list.get(i).getAcutal_priority().equalsIgnoreCase("medium")) {
						int lesspriorityticket = list.get(i).getTicketid();
						String service_engineer = list.get(i).getService_engineer().getServiceengineer();

						ServiceEngineer engineerbean = new ServiceEngineer();
						engineerbean.setServiceengineer(service_engineer);
						ticketbean.setService_engineer(engineerbean);
						ticketbean.setStatus("ongoing");
						entitymanager.persist(ticketbean);
						entitymanager.getTransaction().commit();
						changeToPending(lesspriorityticket);
						changeEngineerCurrentPriority(ticketbean);
					
						
						return true;
					}
				}
			} else if (ticketbean.getAcutal_priority().equalsIgnoreCase("medium")) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getAcutal_priority().equalsIgnoreCase("low")) {
						int lowpriorityticket = list.get(i).getTicketid();
						String service_engineer = list.get(i).getService_engineer().getServiceengineer();

						ServiceEngineer engineerbean = new ServiceEngineer();
						engineerbean.setServiceengineer(service_engineer);

						ticketbean.setService_engineer(engineerbean);
						ticketbean.setStatus("ongoing");
						entitymanager.persist(ticketbean);
						entitymanager.getTransaction().commit();
						changeToPending(lowpriorityticket);
						changeEngineerCurrentPriority(ticketbean);
						
						
						return true;
					}
				}
			}
		}
		entitymanager.getTransaction().commit();
		
		return false;
	}

	public  void changeEngineerCurrentPriority(TicketBean ticketbean) {
		 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		ServiceEngineer serviceengineer=entitymanager.find(ServiceEngineer.class, ticketbean.getService_engineer().getServiceengineer());
		serviceengineer.setCurrentpriority_ticket(ticketbean.getAcutal_priority());
		entitymanager.persist(serviceengineer);
		entitymanager.getTransaction().commit();
		
	}

	public  void changeToPending(int lowpriorityticket) {
		 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		TicketBean ticket = entitymanager.find(TicketBean.class,lowpriorityticket);
		ticket.setStatus("pending");
		entitymanager.persist(ticket);
		entitymanager.getTransaction().commit();
		
	}

	public  void assingNewAsPending(TicketBean ticketbean) {
		 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery(
				"SELECT t FROM TicketBean t where status=:status and department_id=:dept_id order by requestedend_date desc",
				TicketBean.class);
		query.setParameter("status", "ongoing");
		query.setParameter("dept_id", ticketbean.getDepartment().getDepartment_id());
		List<TicketBean> list = query.getResultList();
		ServiceEngineer engineer = list.get(0).getService_engineer();
		ticketbean.setService_engineer(engineer);
		ticketbean.setStatus("pending");
		entitymanager.persist(ticketbean);
		entitymanager.getTransaction().commit();
		
	}


}
