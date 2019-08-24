package com.assesment.serviceticketresolution;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import pojo.*;
import repository.AddDao;
@Path("adminApi")
public class AdminApi {
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("addadmin")
	public boolean addAdmin(Credentials credentials) throws IOException
	{
		
		AddDao add=new AddDao();
		return add.addEngineer(credentials);
	
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("addeng")
	public void addEng(ServiceEngineer engineer) throws IOException
	{
		AddDao add=new AddDao();
		add.engineerinsert(engineer);
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("registeruser")
	public boolean registerUser(Credentials credentials)
	{
		AddDao adddao=new AddDao();
		return adddao.registerUser(credentials);
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("delete")
	public boolean deleteEngineer(String engineername)
	{
		AddDao adddao=new AddDao();
		return adddao.deleteEngineer(engineername);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("englist")
	public ArrayList<ServiceEngineer> getEngineers()
	{
		AddDao adddao=new AddDao();
		return adddao.getEngineersList();
	}
	}

	
