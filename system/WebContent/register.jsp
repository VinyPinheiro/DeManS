<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DeManS - Cadastrar</title>
</head>
<body>
	<%@ include file="header.jsp" %>

	<div style = "text-align:center">
		<form action="registerMember" method="POST">
			<label>ID</label><br>
			<input type="text" name="id" pattern="^{7}[0-9]$" placeholder="44199" required><br>
			<label>Nome</label><br>
			<input type="text" name="name" size="50" placeholder="Jo�o da Silva" required><br>
			<label>Data de nascimento</label><br>
			<input type="date" name="birthdate" placeholder="1995-02-14" pattern="/^[1-2][0-9]{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/" required><br>
			<label>Senha</label><br>
			<input type="password" name="password" required><br>
			<label>Confirma��o de Senha</label><br>
			<input type="password" name="password_confirmation" required><br>
			<label>Telefone</label><br>
			<input type="tel" name="phone" placeholder="(61) 98145-8085" required><br>
			<label>Telefone(Respons�vel)</label><br>
			<input type="tel" name="dad_phone" placeholder="(61) 98145-8085" ><br>
			
			<label>Rua</label><br>
			<input type="text" name="address" placeholder="Quadra 07" required><br>
			<label>N�mero</label><br>
			<input type="text" name="number" placeholder="Lote 09" pattern="^{7}[0-9]$" required><br>
			<label>Complemento</label><br>
			<input type="text" name="complement"><br>
			<label>CEP</label><br>
			<input type="text" name="zipcode" placeholder="72450-000" pattern="^{5}[0-9]/-{3}[0-9]$" required><br>
			<label>Cidade</label><br>
			<input type="text" name="city" required><br>
			<label>Estado</label><br>
			<input type="text" name="state" placeholder="DF" required><br>
			
			
			
			<input type="submit" value="Cadastrar">
		
		</form>
	</div>
</body>
</html>