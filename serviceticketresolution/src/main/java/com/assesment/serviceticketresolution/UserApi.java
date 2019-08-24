package com.assesment.serviceticketresolution;

import java.io.IOException;
import java.util.ArrayList;
import pojo.*;
import repository.UserDao;
import serviceimplementation.UserOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("userapi")
public class UserApi {
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("insert")
	public boolean insert(TicketBean ticketbean) throws IOException

	{

		UserOperations useroperations = new UserOperations();
		return useroperations.engineerAssignment(ticketbean);

	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("view")
	public ArrayList<TicketBean> getTicketsList(){
		UserDao userdao=new UserDao();
		return userdao.getTicketList();
	}
	

}
