<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.io.IOException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Member"%>
<%@page import="java.util.List"%>
<%@ page import="dao.MemberDao"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta Membros</title>
</head>
	
<body>
	 <div id="top">
	 	<%@ include file="header.jsp" %>
	 </div>
	 
	 <form method="POST" action="searchMember">
		 <fieldset>
		 	<legend>Pesquisa por nome</legend>
			 <div>
			 	<table style='width:100%'>
					 <tr>
						 <td><input type="text" name="searchName"></td>
						 <td><button type="submit" value="pesquisar">Pesquisar</button></td>
					 </tr>
				</table>
			 </div>
		 </fieldset>
	 </form>
		 <fieldset>
		 	<legend>Membros cadastrados no Sistema</legend>
			 <br><br>
			 <div>
				 <table style='width:100%'>
					  <%
					  
						// List of pending registrations.
						ArrayList<Member> listMembersFound = (ArrayList<Member>) request.getSession().getAttribute("listMemberFound");
						for (Member member : listMembersFound) {
									
							out.print("<td>"+member.getId()+"</td>");
							out.print("<th>"+member.getName()+"</th>");
							out.print("<td>"+member.getBirthdate()+"</td>");
							out.print("<th>"+member.getSituation()+"</th>");
							out.print("</tr>");												
						}		
					  %>
				 </table>
			 </div>
		 </fieldset>
	

</body>
</html>
