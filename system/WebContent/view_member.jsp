<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.io.IOException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Member"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta Membros</title>
</head>

<body>
 <div id="top">
 <h1>Consulta Membros</h1>
 </div>
 <ul>
 <li><a href="header.jsp"></a><li>
 </ul>
 <fieldset>
 <div id="conteudo">

 <table border="2" class="tabela">
	 <tr>
		 <td class="coluna" align="center">Id</td>
		 <td class="coluna" align="center">Nome</td>
		 <td class="coluna" align="center">Data Nascimento</td>
	 </tr>
	 <%
		 @SuppressWarnings("unchecked")
		 List<Member> membros = (List<Member>) request.getAttribute("membros");
		 for(Member member : membros){
	 %>
	 <tr>
		 <td>
		 	<%out.println(member.getId());%>
		 </td>
		 <td>
		 	<%out.println(member.getName()); %>
		 </td>
		 <td>
		 <%
			out.println(new SimpleDateFormat("dd/MM/yyyy").format(member.getBirthdate())); 
			%>
		 </td>
	 </tr>
	 <%} %>
 </table>

 </div>
 </fieldset>

</body>
</html>
