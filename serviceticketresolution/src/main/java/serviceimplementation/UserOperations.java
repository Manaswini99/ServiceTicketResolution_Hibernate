package serviceimplementation;

import java.util.Random;

import pojo.*;
import repository.*;

public class UserOperations {

	
	/*Assings engineer to ticket raised based on department selected and priority*/
	
	public boolean engineerAssignment(TicketBean ticketbean) {
		Random rand = new Random();
		int ticket = rand.nextInt(9000000) + rand.nextInt(1000);
		ticketbean.setTicketid(ticket);
		EngineerDao engineerdao=new EngineerDao();
		String service_engineer=engineerdao.checkDepaertmentEngineers(ticketbean);
		System.out.println(service_engineer);
		
		
		if(service_engineer.contentEquals("no"))
		{
			Boolean engineer_assinged=engineerdao.checkPriorities(ticketbean);
			System.out.println("engineer_assinged"+engineer_assinged);
			if(!engineer_assinged)
			{
				engineerdao.assingNewAsPending(ticketbean);
				return true;
			}
			
		}
		else
		{
			ServiceEngineer engineerbean=new ServiceEngineer();
			engineerbean.setServiceengineer(service_engineer);
			ticketbean.setService_engineer(engineerbean);
			ticketbean.setStatus("ongoing");
			engineerdao.changeEngineerCurrentPriority(ticketbean);
			System.out.println("ticketbean in engineerAssiginment set to ongoing"+ticketbean.toString());
			UserDao userdao=new UserDao();
			return userdao.createticket(ticketbean);
			
		}
		return true;
	}

}
