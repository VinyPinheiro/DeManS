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
				
		try {		
			String searchName1 = request.getParameter("searchName");
			String searchName = searchName1.toUpperCase();
			System.out.println("searchName = " + searchName);
			
			ArrayList<Member> allMembers = MemberDao.allMembers();
			System.out.println("allMembers = "+ allMembers.size());
			
			if(searchName.isEmpty()){
				//Nothing to do.
			} else {
				List<Member> listMemberFound = searchByName(allMembers, searchName);
				System.out.println("listMemberFound = "+ listMemberFound);
				
				request.getSession().setAttribute("listMemberFound", listMemberFound);
				RequestDispatcher rs = request.getRequestDispatcher("view_member.jsp");
				rs.forward(request, response); 
			}
										
		} catch (NumberFormatException e) {
			final String error = "<script>alert('Erro ao Pesquisar Membro por Nome.'); history.go(-1);</script>";
			out.print(error);
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
		int numberOfResults = 0;
		List<Member> researchedMembers = new ArrayList<Member>();
		
		System.out.println("DentroMetodo - listMembers = "+ listMembers.size());
		
		
	    for (int aux=0; aux < listMembers.size(); aux++) {
	    	// == 
	    	  String name = listMembers.get(aux).getName().toUpperCase();
		      if (name.contains(partOfName.toUpperCase())) {
		    	  numberOfResults++;
		    	  System.out.println("DentroMetodo - numberOfResults = "+ numberOfResults);
		    	 researchedMembers.add(listMembers.get(aux));
		    	 System.out.println("DentroMetodo - researchedMembers = "+ researchedMembers.size());
		      }else{
		    	  //Nothing to do
		      }
	    }
		return researchedMembers;
	}
	
	/*public static void searchById(String id){
		int cont=0;
		
			if (Principal.posto.size() == 0){
				JOptionPane.showMessageDialog(null, "N�o h� pessoas cadastradas");
			}else{			
				if(Servicos.isCpf(numCpf)){
					for (Pessoa pes : Principal.posto) {
				        if (pes.getCpf().equals(numCpf)) {
				        	if(pes.toString().contains("Masculino"))
				        		JOptionPane.showMessageDialog(null, "Nome: "+pes.getNome()+"\nCPF: "
							        + pes.getCpf() +"\nData Vacina:"+ pes.getDataVacina() + "\nSexo: Masculino" + "\nEstado Civil: " + pes.getDadoPessoa(), "Consulta Pessoa", JOptionPane.PLAIN_MESSAGE);
				        	else
				        		JOptionPane.showMessageDialog(null, "Nome: "+pes.getNome()+"\nCPF: "
								        + pes.getCpf() +"\nData Vacina:"+ pes.getDataVacina() + "\nSexo: Feminino" + "\nQuantidade Gravidez: " + pes.getDadoPessoa() , "Consulta Pessoa", JOptionPane.PLAIN_MESSAGE);
				        }else{
				        	cont++;
				        }
				    }
					if(cont >= Principal.posto.size())
						JOptionPane.showMessageDialog(null, "Registro n�o localizado!");
				}else
					JOptionPane.showOptionDialog(null, "CPF invalido", "Erro", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			}
	} */

}
