<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.Member"%>
<%@ page import="model.Office"%>
<%@ page import="dao.MemberDao"%>
<%@ page import="java.util.Vector"%>
<%@page import="model.Nominata"%>
<%@page import="dao.NominataDao"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Vector<Member> activeMembers = MemberDao.activeMembers();

		int nominata_code = Integer.parseInt(request.getParameter("nominata"));

		Nominata nominata = NominataDao.findNominata(nominata_code);

		Vector<Integer> indexesOfOffice = new Vector<Integer>();
	%>
	<form method="POST" action="updateNominata">
		<input type="hidden" name="code" value="<% out.print(nominata_code); %>">
		<fieldset>
			<legend>Cadastro de Nominata para gestão subsequente</legend>
			<%
				for (int i = 0; i < Office.VALID_OFFICES.length; ++i) {
					out.print("<label>" + Office.VALID_OFFICES[i] + "</label><br>");
					out.print("<select name='" + Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i] + "'>");

					String options = "<option value=''>Ninguém</option>";
					for (Office office : nominata.getNominataList()) {
						if (Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i].equals(office.getOffice())) {
							options += "<option value='" + office.getMember().getId() + "' selected>" + office.getMember().getName() + "</option>";
						} else {
							options += "<option value='" + office.getMember().getId() + "'>" + office.getMember().getName() + "</option>";
						}

					}

					out.print(options);

					out.print("</select><br>");
				}
			%>
		</fieldset>
		<br> <input type="submit" value="Salvar">
	</form>
</body>
</html>