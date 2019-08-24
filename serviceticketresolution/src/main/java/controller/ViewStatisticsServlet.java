package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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


public class ViewStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String function = request.getParameter("varname");
			HttpSession session = request.getSession(false);
			String user=(String)session.getAttribute("user");
			Client client = ClientBuilder.newClient(new ClientConfig());
			String apiURL = "http://localhost:8080/serviceticketresolution/webapi/engineerapi";
			if(function.equals("alltickets"))
			{
				WebTarget webTarget = client.target(apiURL).path("viewtickets");
				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
				GenericType<ArrayList<TicketBean>> gType = new GenericType<ArrayList<TicketBean>>() {
				};
				Response clientResponse = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));
				ArrayList<TicketBean> tickets = clientResponse.readEntity(gType);
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("ViewServiceengineerTickets.jsp");
				request.setAttribute("tickets",tickets);
				requestdispatcher.forward(request, response);
			}
			else if(function.equals("changepriority"))
			{
				int ticket_id=Integer.parseInt(request.getParameter("ticket"));
				String newpriority=request.getParameter("newpriority");
				TicketBean updationticket=new TicketBean();
				updationticket.setTicketid(ticket_id);
				updationticket.setAcutal_priority(newpriority);
				WebTarget webTarget = client.target(apiURL).path("updateticketpriority");
				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
				Response clientResponse = invocationBuilder.post(Entity.entity(updationticket, MediaType.APPLICATION_JSON));
				 String assinged=clientResponse.readEntity(String.class);
					if(assinged.equals("true"))
					{
						response.sendRedirect("ViewServiceengineerTickets.jsp");
					}
					else
					{
						out.print("Oops,someting went wrong!!");
						RequestDispatcher requestdispatcher = request.getRequestDispatcher("ServiceEngineer.jsp");
						requestdispatcher.include(request, response);
					}
			}
			else if(function.contentEquals("ticketsage"))
			{
				response.sendRedirect("ticketsage.jsp");
			}
			else if(function.equals("ticketname"))
			{
				String ticket=request.getParameter("ticketname");
				WebTarget webTarget = client.target(apiURL).path("ticketsage");
				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
				Response clientResponse = invocationBuilder.post(Entity.entity(ticket, MediaType.APPLICATION_JSON));
				 String age=clientResponse.readEntity(String.class);
				 if(age.equals("notpreset"))
					 out.print("Enter valid ticketor ongoing/pending ticket");
				 else
					 out.print("<html><body><fieldset><legend>Ticket-Age</legend><center>The ticket Age is "+age+"</center></fieldset></body></html>");
				 RequestDispatcher requestdispatcher = request.getRequestDispatcher("ticketsage.jsp");
				 requestdispatcher.include(request, response);
			}
			else if(function.equals("perseverity"))
			{
				WebTarget webTarget = client.target(apiURL).path("prioritiesseverity");
				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
				GenericType<ArrayList<String>> gType = new GenericType<ArrayList<String>>() {
				};
				Response clientResponse = invocationBuilder.get();
				ArrayList<String> priorityperseverities = clientResponse.readEntity(gType);
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("PrioritiesSeverity.jsp");
				request.setAttribute("priorityperseverities",priorityperseverities);
				requestdispatcher.forward(request, response);
				
			}
			else if(function.equals("engineerseverity"))
			{
				WebTarget webTarget = client.target(apiURL).path("engineerseverity");
				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
				GenericType<List<String>> gType = new GenericType<List<String>>() {
				};
				Response clientResponse = invocationBuilder.get();
				List<String> EngineersSeverity = clientResponse.readEntity(gType);
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("EngineersSeverity.jsp");
				request.setAttribute("EngineersSeverity",EngineersSeverity);
				requestdispatcher.forward(request, response);
			}
			}
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request, response);
		}
}
