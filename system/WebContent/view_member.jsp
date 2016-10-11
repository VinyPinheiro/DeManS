<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Member"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DeManS - Visualizar Membro</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<% 
		//HttpSession secao = request;
		Member member = (Member) request.getSession().getAttribute("dtMember");
		
		//ArrayList<Member> listMembersFound = (ArrayList<Member>) request.getAttribute("listMemberFound");
	%>

	<fieldset>
		<legend>
				Dados do Membro
		</legend>
			<table>
				<tr>
					<td><b>ID:</b> &nbsp;&nbsp;<%=member.getId()%></td>
				</tr>
				<tr>
					<td><b>Nome:</b> &nbsp;&nbsp;<%=member.getName()%></td>
				</tr>
				<tr>
					<td><b>Data de Nascimento:</b>&nbsp;&nbsp; <%=String.valueOf(member.getBirthdate())%></td>
				</tr>			
				<tr>
					<td><b>Telefone:</b>  &nbsp;&nbsp;<%=member.getPhone()%></td>	
				</tr>
				<tr>
					<td><b>Telefone (Responsável):</b>  &nbsp;&nbsp;<%=member.getDad_phone()%></td>	
				</tr>
				<tr>
					<td><b>Endereço:</b> &nbsp;&nbsp;<%=member.getAddress()%></td>
				</tr>
				<tr>
					<td><b>Cidade:</b>&nbsp;&nbsp;<%=member.getAddress().getCity()%></td>
				</tr>
				<tr>
					<td><b>Aprovado por:</b>&nbsp;&nbsp;<%=member.getId()%>
				</tr>
				<tr>
						<%if(member.getDegree().equalsIgnoreCase("Iniciatico")){%>
							<td><b>Grau:</b>&nbsp;&nbsp; Iniciático </td>
						<%} else if(member.getDegree().equalsIgnoreCase("Demolay")){%>
							<td><b>Grau:</b>&nbsp;&nbsp; Demolay </td>
						<%} else if(member.getDegree().equalsIgnoreCase("Macom")){%>
							<td><b>Grau:</b>&nbsp;&nbsp; Maçom </td>
						<%} else {%>
							<td><b>Grau:</b>&nbsp;&nbsp; Não especificado. </td>
						<%}%>
				</tr>	
				<!-- Situação -->
				<tr>
						<%if(member.getSituation().equalsIgnoreCase("Ativo")){%>
							<td><b>Situação:</b>&nbsp;&nbsp; Ativo </td>
						<%} else if(member.getSituation().equalsIgnoreCase("Irregular")){%>
							<td><b>Situação:</b>&nbsp;&nbsp; Irregular </td>
						<%} else if(member.getSituation().equalsIgnoreCase("Senior")){%>
							<td><b>Situação:</b>&nbsp;&nbsp; Senior </td>
						<%} else if(member.getSituation().equalsIgnoreCase("Pendente")){%>
							<td><b>Situação:</b>&nbsp;&nbsp; Pendente </td>
						<%} else if(member.getSituation().equalsIgnoreCase("Recusado")){%>
							<td><b>Situação:</b>&nbsp;&nbsp; Recusado </td>
						<%} else { %>
							<td><b>Situação:</b>&nbsp;&nbsp; Não especificado. </td>
						<%}%>
				</tr>	
			</table>
	</fieldset>
</body>
</html>