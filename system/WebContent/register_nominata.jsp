<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.Member"%>
<%@ page import="model.Office"%>
<%@ page import="dao.MemberDao"%>
<%@ page import="java.util.Vector"%>

<%
	Vector<Member> activeMembers = MemberDao.activeMembers();

	String options = "<option value='' selected>Ninguém</option>";

	for (Member member : activeMembers) {
		options += "<option value='" + member.getId() + "'>" + member.getName() + "</option>";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar Nominata</title>
</head>
<body>

	<form method="POST" action="registerNominata">
		<fieldset>
			<legend>Cadastro de Nominata para gestão subsequente</legend>
	
			<%
				for (int  i = 0; i < Office.VALID_OFFICES.length; ++i)
				{
					out.print("<label>"+Office.VALID_OFFICES[i]+"</label><br>");
					out.print("<select name='"+Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i]+"'>");
					
					out.print(options);
									
					out.print("</select><br>");
				}
			%>
		</fieldset>
		<br> <input type="submit" value="Salvar">
	</form>
</body>
</html>