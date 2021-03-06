package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dao.NominataDao;
import exception.AddressException;
import exception.MemberException;
import exception.NominataException;
import exception.OfficeException;
import exception.UfException;
import model.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get request parameters for userID and password
		Integer user = Integer.parseInt(request.getParameter("user"));
		String pwd = request.getParameter("pwd");
		Member member;
		try {
			member = MemberDao.findById(user);
		
			if(member != null){
				if(member.getPassword().equals(pwd)){
					HttpSession session = request.getSession();
					session.setAttribute("user", member.getName());
					
					session.setAttribute("official",NominataDao.findNominataBySemester(member.getId(), getCurrentSemester(), getCurrentYear()));
					System.out.println(session.getAttribute("official"));
					
					session.setMaxInactiveInterval(30*60);
					Cookie userName = new Cookie("user",request.getParameter("user"));
					userName.setMaxAge(30*60);
					response.addCookie(userName);
					response.sendRedirect("home.jsp");
				}
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				PrintWriter out= response.getWriter();
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(request, response);
			}
		} catch (SQLException sqlexception){
			System.out.println("There was an error conneting to the server." + sqlexception);
			
			response.sendError(412);
		} catch (AddressException addressException){
			System.out.println("There was an error conneting to the server." + addressException);
			
			response.sendError(412);
		} catch (UfException ufException){
			System.out.println("There was an error conneting to the server." + ufException);
			
			response.sendError(412);
		} catch (MemberException memberException) {
		
			System.out.println("There was an error conneting to the server." + memberException);
			
			response.sendError(412);
		} catch (NominataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OfficeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int getCurrentSemester(){
		
		int month = Calendar.MONTH;
		int semester = 0;
		
		if(month > 6){
			semester = 1;
		}else{
			semester = 2;
		}
		System.out.println(month);
		return semester;
	}
	
	private int getCurrentYear(){
		int year = Calendar.YEAR;
		
		System.out.println(year);
		return year;
	}

}
