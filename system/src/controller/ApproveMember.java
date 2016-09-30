package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import exception.AddressException;
import exception.DaoException;
import exception.MemberException;
import exception.OfficeException;
import exception.UfException;
import model.Address;
import model.Member;
import model.Office;
import model.UF;

public class ApproveMember extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Method to receive POST data and verify and send result to client
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {		
			String approve = request.getParameter("approve"); 
			System.out.println("Approve: "+ approve);
			
			System.out.println(request.getSession().getAttribute("idMember"));
			int memberId = (int) request.getSession().getAttribute("idMember");
						
			if(request.getParameter("approve").equals("Aceitar")) {
				System.out.println("Aceitar membro!");
				approve = "Ativo";
				Member member = new Member(memberId, approve);
				MemberDao member_dao = new MemberDao(member);
				
				member_dao.approveMember(member);
			
			} else if (request.getParameter("approve").equals("Recusar")) {
				 System.out.println("Recusar membro!");
				approve = "Recusado";
				Member member = new Member(memberId, approve);
				MemberDao member_dao = new MemberDao(member);
					
				member_dao.rejectMember(member);
		    }
						
			//request.getRequestDispatcher("approveMember").forward(request, response);

        } catch (SQLException e) {            
            e.printStackTrace();	
		} catch (NumberFormatException e) {
			final String error = "<script>alert('Erro ao recuperar ao Aprovar Membro.Tente mais tarde'); history.go(-1);</script>";
			out.print(error);
			e.printStackTrace();
		} catch (MemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
