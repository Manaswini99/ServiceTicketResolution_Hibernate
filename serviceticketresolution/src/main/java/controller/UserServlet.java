package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
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

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String function = request.getParameter("varname");
		HttpSession session = request.getSession(false);
		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8080/serviceticketresolution/webapi/userapi";

		if (function.equals("raiseticket")) {
			response.sendRedirect("raiseticket.jsp");
		} else if (function.equals("issues")) {
			
			TicketBean ticketbean = new TicketBean();
			DepartmentBean dep = new DepartmentBean();
			dep.setDepartment_id(Integer.parseInt(request.getParameter("department")));
			ticketbean.setDepartment(dep);		    
			ticketbean.setStart_date(LocalDate.now());
			ticketbean.setRequestedend_date(LocalDate.parse(request.getParameter("date")));
			ticketbean.setPriority(request.getParameter("priority"));
			ticketbean.setAcutal_priority(request.getParameter("priority"));
			ticketbean.setCompleted_date(null);
			ticketbean.setDescription(request.getParameter("issue"));
			ticketbean.setCustomer((String) session.getAttribute("user"));
			WebTarget webTarget = client.target(apiURL).path("insert");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(ticketbean, MediaType.APPLICATION_JSON));
			String insertionstatus = clientResponse.readEntity(String.class);
	
			if (insertionstatus.equals("true")) {
				response.sendRedirect("User.jsp");
			} else {
				out.println("Oops,something went wrong!!Please Raise ticket again...");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("User.jsp");
				requestdispatcher.include(request, response);
			}

		}
		else if(function.equals("view"))
		{
			WebTarget webTarget = client.target(apiURL).path("view");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			GenericType<ArrayList<TicketBean>> gType = new GenericType<ArrayList<TicketBean>>() {
			};
			Response clientResponse = invocationBuilder.get();
			ArrayList<TicketBean> tickets = clientResponse.readEntity(gType);
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("view.jsp");
			request.setAttribute("tickets",tickets);
			requestdispatcher.forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}