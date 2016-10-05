/*****************************
 * Class name: SearchMember (.java)
 * 
 * Purpose: 
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
import exception.DaoException;
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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String error = null;
		String searchName = null;
				
		try {	
			
			ArrayList<Member> allMembers = MemberDao.allMembers();
			System.out.println("allMembers = "+ allMembers.size());
			List<Member> listMemberFound = null;
			
			String paramOperation = request.getParameter("operation");
			System.out.println("paramOperation = "+ paramOperation);
			
			if(paramOperation.equals("searchById")){
				String paramSearchId = request.getParameter("searchId");
				if(paramSearchId.equals("")){
					//Nothing to do.
					error = "Id vazio.";
				} else if(paramSearchId.length() < 3) {
					//Nothing to do.
					error = "O Id deve ter no mínimo 3 caracteres.";
				} else {	
					int searchId = Integer.parseInt(paramSearchId);
					System.out.println("searchId = " + searchId);
					listMemberFound = searchById(allMembers, searchId);
				}
				
			} else if(paramOperation.equals("searchByName")){
				String paramSearchName = request.getParameter("searchName");
				if(paramSearchName.equals("")){
					//Nothing to do.
					error = "Nome vazio.";
				} else if(paramSearchName.length() < 3) {
					//Nothing to do.
					error = "O Nome deve ter no mínimo 3 caracteres.";
				} else {
					searchName = paramSearchName.toUpperCase();
					System.out.println("searchName = " + searchName);
					listMemberFound = searchByName(allMembers, searchName);
				}
				
			} else {
				// Nothing to do.
			}
			
			System.out.println("listMemberFound = "+ listMemberFound);
				
			request.getSession().setAttribute("listMemberFound", listMemberFound);
				 
			request.getSession().setAttribute("error", error);
			RequestDispatcher rs = request.getRequestDispatcher("view_member.jsp");
			rs.forward(request, response);
										
		} catch (NumberFormatException e) {
			final String errorExNumber = "<script>alert('Erro ao Pesquisar Membro por Nome.'); history.go(-1);</script>";
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
		
		System.out.println("DentroMetodo - listMembers = "+ listMembers.size());
		
	    for (int aux=0; aux < listMembers.size(); aux++) {
	    	  String name = listMembers.get(aux).getName().toUpperCase();
		      if (name.contains(partOfName)) {
		    	 researchedMembers.add(listMembers.get(aux));
		    	 System.out.println("DentroMetodo - researchedMembers = "+ researchedMembers.size());
		      }else{
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
		
		System.out.println("DentroMetodo - listMembers = "+ listMembers.size());
		
	    for (int aux=0; aux < listMembers.size(); aux++) {
	    	  int id = listMembers.get(aux).getId();
		      if (id == idMember) {
		    	 researchedMembers.add(listMembers.get(aux));
		      }else{
		    	  //Nothing to do
		      }
	    }
	    System.out.println("DentroMetodo - researchedMembers = "+ researchedMembers.size());
		return researchedMembers;
	}
	
}
