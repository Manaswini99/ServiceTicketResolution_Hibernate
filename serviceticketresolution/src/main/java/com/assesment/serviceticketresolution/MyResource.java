package com.assesment.serviceticketresolution;

import java.io.IOException;
import java.util.ArrayList;

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
import pojo.*;
import repository.LoginDao;
import serviceimplementation.LoginOperations;
@Path("myresource")
public class MyResource {
	
	
	
	@POST
//    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("test")
    public String validate(Credentials credentials) throws IOException
    {
    	String role="no";
    	System.out.println("hi rest");
    	if(LoginOperations.checkexistance(credentials) && LoginOperations.validatePassword(credentials))
    	{
    		System.out.println(credentials.toString());
    	  role = LoginOperations.getType(credentials);
    
    	}
    	System.out.println("hi rest");
    	return role;
    }
	@GET
	@Path("deptlist")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<DepartmentBean> getDepartmentList(){
		System.out.println(LoginDao.getDepartmentList());
		return LoginDao.getDepartmentList();
	}
	
}
