<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="dao.NominataDao"%>
 <%@ page import="model.Nominata"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Selecionar Semestre</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Selecione o Semestre/Ano</h1>

	<form method="POST" action="change_nominata.jsp">
		<label>Selecione o semestre/ano</label>
		<select name="nominata">
		<%
			for (Nominata nominata : NominataDao.returnAllNominatas()){
				out.print("<option value='" + nominata.getId() + "'>");
				out.print(nominata.getSemester() + "/" + nominata.getYear());
				out.print("</option>");
			}
		%>
		</select>
		<input type="submit" value="Carregar">
	</form>
</body>
</html>