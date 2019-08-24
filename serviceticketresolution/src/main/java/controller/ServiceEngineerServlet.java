package controller;
import java.io.IOException;
import java.io.PrintWriter;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class ServiceEngineerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String function = request.getParameter("varname");
			HttpSession session = request.getSession(false);
			String user=(String)session.getAttribute("user");
			Client client = ClientBuilder.newClient(new ClientConfig());
			String apiURL = "http://localhost:8080/serviceticketresolution/webapi/engineerapi";
			if(function.equals("complete"))
			{
				WebTarget webTarget = client.target(apiURL).path("complete");
				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
				Response clientResponse = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));
				int ticketid =Integer.parseInt(clientResponse.readEntity(String.class));
				session.setAttribute("ticketid",ticketid);
				if(ticketid!=0)
				{
					response.sendRedirect("complete.jsp");
				}
				else
				{
					out.print("No ticket alloted!!");
					RequestDispatcher requestdispatcher = request.getRequestDispatcher("ServiceEngineer.jsp");
					requestdispatcher.include(request, response);
				}
			}
			else if(function.equals("completedticket"))
					{
						int ticketcompleted=(int) session.getAttribute("ticketid");
						WebTarget webTarget = client.target(apiURL).path("completedticket");
						Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
						Response clientResponse = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));
						 String assinged=clientResponse.readEntity(String.class);
						if(assinged.equals("true"))
						{
							out.print("New ticket alloted!!");
							RequestDispatcher requestdispatcher = request.getRequestDispatcher("ServiceEngineer.jsp");
							requestdispatcher.include(request, response);
						}
						else
						{
							out.print("No Pending Tickets!");
							RequestDispatcher requestdispatcher = request.getRequestDispatcher("ServiceEngineer.jsp");
							requestdispatcher.include(request, response);
						}
					}
			
	 }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
		}
}