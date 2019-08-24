package com.assesment.serviceticketresolution;

import pojo.*;
import repository.StaticticsDao;
import repository.TicketDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("engineerapi")
public class EngineerApi {
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("complete")
	public int complete(String user) throws IOException
	{
		TicketDao Ticketdao=new TicketDao();
		int ticketid=Ticketdao.complete(user);
		return ticketid;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("completedticket")
	public boolean ticketcompleted(String user)
	{
		TicketDao ticketdao=new TicketDao();
		Boolean assinged=ticketdao.assignPendingTicket(user);
		if(assinged)
		{
			 return true;
		}
		else
		{
			return false;
		}
		
		
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("viewtickets")
	public ArrayList<TicketBean> viewtickets(String user)
	{
		TicketDao ticketdao=new TicketDao();
		return ticketdao.viewtickets(user);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateticketpriority")
	public Boolean updatePriorityTicket(TicketBean ticket) {
		TicketDao ticketdao=new TicketDao();
		ticketdao.updatePriority(ticket);
		return true;
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("ticketsage")
	public String getticketage(String ticket) {
		StaticticsDao stats=new StaticticsDao();
		return stats.getticketage(ticket);
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("prioritiesseverity")
	public ArrayList<String> getSeverity()
	{
		StaticticsDao statsdao=new StaticticsDao();
		return statsdao.getSeverityperPriority();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("engineerseverity")
	public List<String> getEngineerSeverity()
	{
		StaticticsDao statsdao=new StaticticsDao();
		return statsdao.getEngineerSeverity();
	}
	
	}