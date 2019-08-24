package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import pojo.*;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String function = request.getParameter("varname");
		HttpSession session = request.getSession(false);
		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8080/serviceticketresolution/webapi/adminApi";
		
		if (function.equals("addengineer")) {
			request.getRequestDispatcher("add.jsp").forward(request, response);
		}
		else if(function.equals("addeng"))
		{
			Credentials credentials=new Credentials();
			credentials.setUsername(request.getParameter("name"));
			credentials.setPassword(request.getParameter("password"));
			WebTarget webTarget = client.target(apiURL).path("addadmin");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(credentials, MediaType.APPLICATION_JSON));
			String registered=clientResponse.readEntity(String.class);
			if(registered.equals("true"))
			{
			ServiceEngineer engineer=new ServiceEngineer();
			DepartmentBean dep = new DepartmentBean();
			dep.setDepartment_id(Integer.parseInt(request.getParameter("department")));
			engineer.setServiceengineer(request.getParameter("name"));
			engineer.setDepartment(dep);
			WebTarget webTarget1 = client.target(apiURL).path("addeng");
			Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
			Response clientResponse1 = invocationBuilder1.post(Entity.entity(engineer, MediaType.APPLICATION_JSON));
			out.print("Added Succesfully");
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("Admin.jsp");
			requestdispatcher.include(request, response);
			}
			else
			{
				out.print("Engineer Already Added");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("Admin.jsp");
				requestdispatcher.include(request, response);
			}
		}
		else if(function.equals("adduser"))
		{
			request.getRequestDispatcher("addUser.jsp").forward(request, response);
		}
		else if(function.equals("addinguser"))
		{
			Credentials credentials=new Credentials();
			credentials.setUsername(request.getParameter("newname"));
			credentials.setPassword(request.getParameter("newpassword"));
			WebTarget webTarget = client.target(apiURL).path("registeruser");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(credentials, MediaType.APPLICATION_JSON));
			String registered=clientResponse.readEntity(String.class);
			if(registered.equals("true"))
			{
				out.println("Successfully Added!!");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("Admin.jsp");
				requestdispatcher.include(request, response);
			}
			else
			{
				out.print("User Already Added");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("Admin.jsp");
				requestdispatcher.include(request, response);
			}
		}
		
		else if(function.equals("delete"))
		{
			String engineername=request.getParameter("id");
			WebTarget webTarget = client.target(apiURL).path("delete");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(engineername, MediaType.APPLICATION_JSON));
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("AdminServlet?varname=table");
			requestdispatcher.include(request, response);
		}
		else if(function.equals("table"))
		{
			ServiceEngineer engineer=new ServiceEngineer();
		apiURL = "http://localhost:8080/serviceticketresolution/webapi/adminApi";
		WebTarget webTarget = client.target(apiURL).path("englist");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			GenericType<ArrayList<ServiceEngineer>> gType = new GenericType<ArrayList<ServiceEngineer>>() {
			};
			Response clientResponse = invocationBuilder.get();
			ArrayList<ServiceEngineer> engineers = clientResponse.readEntity(gType);
			request.setAttribute("engineers", engineers);	
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("Admin.jsp");
			requestdispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}