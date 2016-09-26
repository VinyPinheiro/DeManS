    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/header.css">    
	<div class="container" id = "headercontainer">
	  <header>
	  	<%
	  	String userName = null;
	  	String sessionID = null;
	  	boolean logado = false;
	  	if( session.getAttribute("user") == null) {
	  	
	    	logado = false;
		} else {
			logado = true;
			Cookie[] cookies = request.getCookies();	
	    	if(cookies != null){
		    	for(Cookie cookie : cookies){
		    		if(cookie.getName().equals("user")) userName = cookie.getValue();
		    		if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
		    		
		    		
		    	}
	    	}
    	}
	  	%>
	  	<% if(logado == false){  %>
			<a href="/DeMans/">Inicio</a> |
	    	<a href="/DeMans/register.jsp">Cadastrar</a> |
	    	<a href="/DeMans/login.jsp">Entrar</a>
			
		<% } else { %>
			<h3> Logado como <%=userName %> !</h3>	
		<% } %>    	
	  	
	  </header>
	</div>
