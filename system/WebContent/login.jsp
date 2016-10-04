<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Login Page</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<% if(logado == false){ %>
	<div style = "text-align:center">
	
		<form action="LoginServlet" method="post">
			<br>
			ID: <input type="text" name="user" placeholder="44199" required>
			Senha: <input type="password" name="pwd" placeholder="******">
			<input type="submit" value="Login">
	
		</form>
	</div>
	
	<%}else { %>
	Você já esta logado
	 <%} %>
	
</body>
</html>