package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo.ServiceEngineer;
import pojo.TicketBean;

public class StaticticsDao {
	private static final String PERSISTENCE_UNIT_NAME = "servicetickets";
	public static String getticketage(String ticket) {
		int ticket_id=Integer.parseInt(ticket);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		TicketBean ticketbean=entitymanager.find(TicketBean.class,ticket_id);
		String status=ticketbean.getStatus();

		if(ticketbean.getTicketid()!=0)
		{
			if(status.equals("ongoing")||status.equals("pending"))
			{
				List<String> age= entitymanager.createNativeQuery("select   AVG ( TIMESTAMPDIFF ( DAY, start_date,curdate())) from TicketBean where status=:status and ticketid=:id").setParameter("status",status).setParameter("id", ticketbean.getTicketid()).getResultList();
				System.out.println("age"+age.toString());
				return String.valueOf(age.get(0));
			}
		}
		else
		{
			return "notpreset";
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
		return "notpreset";
		
	}
	public ArrayList getSeverityperPriority() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		ArrayList<String> list = new ArrayList<String>();
		List<String> low = entitymanager.createNativeQuery("select CONCAT('', SEC_TO_TIME(AVG(TIMESTAMPDIFF(SECOND, start_date, completed_date)))) from TicketBean where status = ?1 and priority=?2").setParameter(1, "completed").setParameter(2,"low").getResultList();
		List<String> medium = entitymanager.createNativeQuery("select CONCAT('', SEC_TO_TIME(AVG(TIMESTAMPDIFF(SECOND, start_date, completed_date)))) from TicketBean where status = ?1 and priority=?2").setParameter(1, "completed").setParameter(2,"medium").getResultList();
		List<String> high = entitymanager.createNativeQuery("select CONCAT('', SEC_TO_TIME(AVG(TIMESTAMPDIFF(SECOND, start_date, completed_date)))) from TicketBean where status = ?1 and priority=?2").setParameter(1, "completed").setParameter(2,"high").getResultList();
		list.add(low.get(0));
		list.add(medium.get(0));
		list.add(high.get(0));
		entitymanager.getTransaction().commit();
		entitymanager.close();
		return list;
	}
	public List<String> getEngineerSeverity() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();

		
		List<String> avgservice = new ArrayList<String>();
		List<ServiceEngineer> l =(List<ServiceEngineer>) entitymanager.createQuery("select l from ServiceEngineer l").getResultList();
		for(int j=0;j<l.size();j++)
		{
		List a =	entitymanager.createNativeQuery(
		"select (AVG(TIMESTAMPDIFF(day, start_date, completed_date))) from TicketBean  where status = ?1 and serviceengineer=?2")
		.setParameter(1, "Completed").setParameter(2, l.get(j).getServiceengineer()).getResultList();
		avgservice.add(l.get(j).getServiceengineer().toString());
		if(a.get(0)==null)
		{
			avgservice.add("No Tickets Resolved");
		}
		else {
		avgservice.add(a.get(0).toString());
		}
		}
		for(int i=0;i<avgservice.size();i++)
		{
			System.out.println(avgservice.get(i));
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
		return avgservice;
	}
		
	
	
}
