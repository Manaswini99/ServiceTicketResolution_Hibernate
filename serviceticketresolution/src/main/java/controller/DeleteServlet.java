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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import pojo.*;


public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ServiceEngineer engineer=new ServiceEngineer();

		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8080/serviceticketresolution/webapi/adminApi";
		WebTarget webTarget = client.target(apiURL).path("delete");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		GenericType<ArrayList<ServiceEngineer>> gType = new GenericType<ArrayList<ServiceEngineer>>() {
		};
		Response clientResponse = invocationBuilder.get();
		ArrayList<ServiceEngineer> engineers = clientResponse.readEntity(gType);
		request.setAttribute("engineers", engineers);		
		RequestDispatcher requestdispatcher = request.getRequestDispatcher("Admin.jsp");
		requestdispatcher.include(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}