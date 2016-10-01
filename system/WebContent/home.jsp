<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
//allow access only if session exists

String user = null;
if(logado == false){
	response.sendRedirect("login.jsp");
}else {
	user = (String) session.getAttribute("user"); 
	}%>


<h3>Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %></h3>
<br>
User= <%=user %>
<br>

<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>