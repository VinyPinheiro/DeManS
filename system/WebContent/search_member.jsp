<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.io.IOException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Member"%>
<%@page import="java.util.List"%>
<%@page import="dao.MemberDao"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta Membros</title>
</head>

	<% 
		public static  selectMember(){
			System.out.println("Entro no metodo selectMember"); 
			request.getSession().setAttribute("dtMember", member);
			System.out.println("selectMember: depois do request");
			RequestDispatcher rs = request.getRequestDispatcher("view_member.jsp");
			rs.forward(request, response); 
		} %>

<body>
	 <div>
	 	<%@ include file="header.jsp" %>
	 </div>
	 
	 <form method="POST" action="searchMember">
		 <fieldset>
		 	<legend>Pesquisa por Nome</legend>
			 <div>
			 	<table style='width:100%'>
					 <tr>
						 <td><input type="text" name="searchName"></td>
						 <td><button type="submit" name="operation" value="searchByName">Pesquisar</button></td>
					 </tr>
				</table>
			 </div>
		 </fieldset>
	 </form>
	 <form method="POST" action="searchMember">
		 <fieldset>
		 	<legend>Pesquisa por Id</legend>
			 <div>
			 	<table style='width:100%'>
					 <tr>
						 <td><input type="text" name="searchId"></td>
						 <td><button type="submit" name="operation" value="searchById">Pesquisar</button></td>
					 </tr>
				</table>
			 </div>
		 </fieldset>
	 </form>
		 <fieldset>
		 	<legend>Resultado da Pesquisa de Membros</legend>
			 <br><br>
			 <div>
				 <table style='width:100%'>
					  <%					    
							// List of pending registrations.
							ArrayList<Member> listMembersFound = (ArrayList<Member>) request.getAttribute("listMemberFound");							
							String error = String.valueOf(request.getSession().getAttribute("error"));
					  		
							// Validation
					  		if(listMembersFound == null || listMembersFound.size() == 0){
								out.print("<td><center>Nenhum membro encontrado!</center></td>");
					  		} else if(!error.equals("null")){
					  			out.print("<td><center>"+error+"</center></td>");							
							} else {
								for (Member member : listMembersFound) {
									out.print("<td><a>"+ member.getId() +"</a></td>");
									out.print("<td><a href='view_member.jsp' onclick='selectMember("+member+")'>" + member.getName() + "</a></td>");
									out.print("</tr>");												
								}		
							}
					  %>
				 </table>
			 </div>
		 </fieldset>
	</body>
</html>


	
	
