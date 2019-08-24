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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

import pojo.*;

import java.util.*;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		session.setAttribute("user", username);
		String password = request.getParameter("password");
		Credentials credentials = new Credentials();
		credentials.setUsername(username);
		credentials.setPassword(password);

		Client client = ClientBuilder.newClient(new ClientConfig());

		String apiURL = "http://localhost:8080/serviceticketresolution/webapi/myresource";
	           String role=client.target(apiURL+"/test")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(credentials, MediaType.APPLICATION_JSON))
				.readEntity(String.class);
	          if (role.equals("service_engineer")) {
			RequestDispatcher rs = request.getRequestDispatcher("ServiceEngineer.jsp");
			rs.forward(request, response);
		} else if (role.equals("user")) {
			response.sendRedirect("User.jsp");
		}else if(role.equals("Admin"))
		{
//		ServiceEngineer engineer=new ServiceEngineer();
//		apiURL = "http://localhost:8080/serviceticketresolution/webapi/adminApi";
//		WebTarget webTarget = client.target(apiURL).path("englist");
//		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//		GenericType<ArrayList<ServiceEngineer>> gType = new GenericType<ArrayList<ServiceEngineer>>() {
//		};
//		Response clientResponse = invocationBuilder.get();
//		ArrayList<ServiceEngineer> engineers = clientResponse.readEntity(gType);
//		request.setAttribute("engineers", engineers);		
		RequestDispatcher requestdispatcher = request.getRequestDispatcher("AdminServlet?varname=table");
		requestdispatcher.forward(request, response);
		}
		else {
			out.println("Username or Password incorrect");
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("First.jsp");
			requestdispatcher.include(request, response);
		}

	}
}
