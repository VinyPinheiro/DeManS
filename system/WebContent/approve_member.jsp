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
	 //Lista de Membros com solicitação Pendente
		ArrayList<Member> requesterMembers = MemberDao.requesterMembers();
		out.print("<label>"+"Tamanho da lista: " + requesterMembers.size()+"</label><br>");
	%>
	
	<form method="POST" action="approve_member">
		<fieldset>
			<legend>Aprovação de Membros para cadastro no sistema</legend>
						
			<%
				for (int  i = 0; i < requesterMembers.size(); ++i) {
					out.print("<a>"+requesterMembers.get(i).getId()+"</a></pre>");
					out.print("<a>"+requesterMembers.get(i).getName()+"</a><br>");
				}
			%>
			
		</fieldset>
		<br> <input type="submit" value="Concluir">
	</form>
</body>
</html>