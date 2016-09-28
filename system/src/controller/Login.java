package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import exception.AddressException;
import exception.MemberException;
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
					//setting session to expiry in 30 mins
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
		}
	}
	
	private int getCurrentSemester(){
		LocalDateTime date = LocalDateTime.now();
		int month = date.getMonthValue();
		
		
		return month;
	}
	
	private int getCurrentYear(){
		LocalDateTime date = LocalDateTime.now();
		int year = date.getYear();
		
		
		return year;
	}

}
