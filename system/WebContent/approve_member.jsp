<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.Member"%>
<%@ page import="dao.MemberDao"%>
<%@ page import="java.util.ArrayList"%>

<%
	ArrayList<Member> requesterMembers = MemberDao.requesterMembers();
	final String[] offices = { "Mestre Conselheiro", "1º Conselheiro", "2º Conselheiro", "Tesoureiro",
			"Escrivão", "Orador", "1º Diácono", "2º Diácono", "1º Mordomo", "2º Mordomo", "Hospitaleiro",
			"Capelão", "Porta Bandeira", "Sentinela", "Mestre de Cerimônias", "1º Preceptor", "2º Preceptor",
			"3º Preceptor", "4º Preceptor", "5º Preceptor", "6º Preceptor", "7º Preceptor" }; 
	String options = "<option value='' selected>Ninguém</option>"; 

	for (Member member : requesterMembers) {
		options += "<option value='" + member.getId() + "'>" + member.getName() + "</option>";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aprovar Membro</title>
</head>
<body>

	<form method="POST" action="approve_member">
		<fieldset>
			<legend>Aprovação de Membros para cadastro no sistema</legend>
	
			<%
				for (Member member : requesterMembers) {
					out.print("<label>"+member+"</label><br>");
					out.print("<select name='"+member+"'>");
					
					out.print(options);
									
					out.print("</select><br>");
				}
			%>
		</fieldset>
		<br> <input type="submit" value="Concluir">
	</form>
</body>
</html>