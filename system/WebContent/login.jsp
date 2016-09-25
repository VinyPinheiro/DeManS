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
	
			ID: <input type="text" name="user" pattern="^{6,}[0-9]$" placeholder="44199" required>
			Senha: <input type="password" name="pwd" placeholder="******">
			<input type="submit" value="Login">
	
		</form>
	</div>
	
	<%}else { %>
	Você já esta logado
	 <%} %>
	
</body>
</html>