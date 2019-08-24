package repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pojo.*;
import serviceimplementation.*;

public class AddDao {
	private static final String PERSISTENCE_UNIT_NAME = "servicetickets";

	public boolean addEngineer(Credentials credentials) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		Roles roles = new Roles();
		roles.setRoleID(2);
		credentials.setRoles(roles);
		LoginOperations loginoperations=new LoginOperations();
		if(loginoperations.checkexistance(credentials))
    	{
			return false;
    	}
		else
		{

			entitymanager.persist(credentials);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			return true;
		}
		
	}

	public void engineerinsert(ServiceEngineer engineer) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
		entitymanager.getTransaction().begin();
		engineer.setCurrentpriority_ticket("no");
		engineer.setNo_of_tickets_worked(0);
		entitymanager.persist(engineer);

		entitymanager.getTransaction().commit();
		entitymanager.close();

	}

	public boolean registerUser(Credentials credentials) {
		LoginOperations loginoperations=new LoginOperations();
		if(loginoperations.checkexistance(credentials))
    	{
			return false;
    	}
		else
		{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = factory.createEntityManager();
			entitymanager.getTransaction().begin();
			Roles roles = new Roles();
			roles.setRoleID(1);
			credentials.setRoles(roles);

			entitymanager.persist(credentials);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			return true;
		}
	}

	public ArrayList<ServiceEngineer> getEngineersList() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<ServiceEngineer> query = entityManager.createQuery("SELECT c FROM ServiceEngineer c ",
				ServiceEngineer.class);
		ArrayList<ServiceEngineer> list = (ArrayList<ServiceEngineer>) query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
		return list;
	}

	public boolean deleteEngineer(String engineername) {
		LoginOperations loginoperations=new LoginOperations();
		Credentials credentials=new Credentials();
		credentials.setUsername(engineername);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		ServiceEngineer engineer=new ServiceEngineer();
		engineer=entityManager.find(ServiceEngineer.class,engineername);
		entityManager.remove(engineer);
		Credentials cred=new Credentials();
		cred=entityManager.find(Credentials.class, engineername);
		entityManager.remove(cred);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
		
	}
}
