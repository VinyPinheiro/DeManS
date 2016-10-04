<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.Member"%>
<%@ page import="dao.MemberDao"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aprovar Membro</title>
</head>
<body>

	<%@ include file="header.jsp" %>

	<%
	 // Member List with Pending request.
		ArrayList<Member> requesterMembers = MemberDao.requesterMembers();
		out.print("<label>"+"Tamanho da lista: " + requesterMembers.size()+"</label><br>");
	%>
		
	<form method="POST" action="approveMember">
		<fieldset>
			<legend>Aprovação de Membros para cadastro no sistema</legend>
								
			<%
				out.print("<table style='width:100%'><tr>");
				for (int  i = 0; i < requesterMembers.size(); ++i) {
					
					out.print("<td>"+requesterMembers.get(i).getId()+"</td>");
					out.print("<th>"+requesterMembers.get(i).getName()+"</th>");
					out.print("<td>"+requesterMembers.get(i).getBirthdate()+"</td>");
					out.print("<td>"+requesterMembers.get(i).getAddress().toString()+"</td>");
					out.print("<td>"+requesterMembers.get(i).getPhone()+"</td>");
					out.print("<th>"+requesterMembers.get(i).getSituation()+"</th>");
					out.print("<td><button type='submit' name='approve' value='Aceitar"+i+"''>Aceitar</button></td>");
					out.print("<td><button type='submit' name='approve' value='Recusar"+i+"''>Recusar</button></td>");
					out.print("</tr>");
														
				}
				
				int i = 1;
				request.getSession().setAttribute("positionMember", i);
				request.getSession().setAttribute("requesterMembers", requesterMembers);
				out.print("</table>");
				
				// Get the user id that approved the request pending registration.
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("user"))
							userName = cookie.getValue();
							request.getSession().setAttribute("userName", userName);
					}
				}
				
			%>
									
		</fieldset>
	</form>
</body>
</html>