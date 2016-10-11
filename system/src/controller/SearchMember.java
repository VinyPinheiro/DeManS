/*****************************
 * Class name: SearchMember (.java) 
 * 
 * Purpose: Search data members by name or by id.
 *****************************/

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import exception.AddressException;
import exception.MemberException;
import exception.UfException;
import model.Member;
 
public class SearchMember extends HttpServlet {
	
	/**
	 * Method to GET receive data and perform the same process that data received by POST method.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * Method to receive POST data and verify and send result to client
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String error = null;
				
		try {	
			ArrayList<Member> allMembers = MemberDao.allMembers();
			String paramOperation = request.getParameter("operation");
			List<Member> listMemberFound = null;
			
			if(paramOperation.equals("searchById")){
				String paramSearchId = request.getParameter("searchId");
				int searchId = Integer.parseInt(paramSearchId);
				// Input validation.
				if(paramSearchId.equals("")){
					error = "Preencha um dos campos de pesquisa.";
				} else if(searchId < 0) {
					error = "O ID não pode ser negativo.";
				} else {	
					listMemberFound = searchById(allMembers, searchId);
				}
				
			} else if(paramOperation.equals("searchByName")){
				String paramSearchName = request.getParameter("searchName");
				// Input validation.
				if(paramSearchName.equals("")){
					error = "Preencha um dos campos de pesquisa.";
				} else if(paramSearchName.length() < 3) {
					error = "Digite ao menos 3 letras.";
				} else {
					String searchName = paramSearchName.toUpperCase();
					System.out.println("searchName = " + searchName);
					listMemberFound = searchByName(allMembers, searchName);
				}
				
			} else {
				// Nothing to do.
			}
				
			request.setAttribute("listMemberFound", listMemberFound);
			request.getSession().setAttribute("error", error);
			
			RequestDispatcher rs = request.getRequestDispatcher("search_member.jsp");
			rs.forward(request, response);
										
		} catch (NumberFormatException e) {
			final String errorExNumber = "<script>alert('Erro ao Pesquisar Membro.'); history.go(-1);</script>";
			out.print(errorExNumber);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Method to search by a member's name.
	 */
	public static List<Member> searchByName(ArrayList<Member> listMembers, String partOfName){
		List<Member> researchedMembers = new ArrayList<Member>();
		
		for (Member member : listMembers) {
	    	  String name = member.getName().toUpperCase();
		      if (name.contains(partOfName)) {
		    	 researchedMembers.add(member);
		      } else {
		    	  //Nothing to do
		      }
	    }
			    
		return researchedMembers;
	}
	
	/*
	 * Method to search by a member's id.
	 */
	public static List<Member> searchById(ArrayList<Member> listMembers, int idMember){
		List<Member> researchedMembers = new ArrayList<Member>();
		
		for (Member member : listMembers) {
	    	  int id = member.getId();
		      if (id == idMember) {
		    	 researchedMembers.add(member);
		      }else{
		    	  //Nothing to do
		      }
	    }
			    
		return researchedMembers;
	}
	
}
