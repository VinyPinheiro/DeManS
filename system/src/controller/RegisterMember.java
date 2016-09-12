/*****************************
 * Class name: RegisterMember (.java)
 * 
 * Purpose: Controller class to create Member object and persist in database
 *****************************/

package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterMember extends HttpServlet {
	
	/**
	 * Method to receive POST data and verify and send result to client
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");  
		
		out.print("Teste de recepção");
		out.println(id + "<br>" + name);
	}

}
