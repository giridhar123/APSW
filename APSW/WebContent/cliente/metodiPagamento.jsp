<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="Beans.MetodoPagamento" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Metodi di Pagamento</title>
		
		<script src="../js/jquery.js"></script>
		<script src="js/metodiPagamento.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>

		<div class="contentLarge">
			<br/>
			<a href="./newMetodoPagamento"><button class="customButton">Nuovo</button></a>
			<br/>
			<%! @SuppressWarnings("unchecked") %>
			<%
			List<MetodoPagamento> metodiPagamento = (List<MetodoPagamento>) request.getAttribute("metodiPagamento");
			if (metodiPagamento.size() > 0) {
			%>
				<table class="customTable">
				<tr><th>Numero Carta</th><th>Scadenza</th><th>VCC</th></tr>
			<%
				for (int i = 0; i < metodiPagamento.size(); ++i) {
				%>
					<tr>
					<td><%= metodiPagamento.get(i).getNumeroCarta().substring(0,4)+"************" %></td>
					<td><%= metodiPagamento.get(i).getScadenza() %></td>
					<td><%= metodiPagamento.get(i).getVcc() %></td>
					<td><button class="customButton" onClick="onDeleteButtonClicked(<%=metodiPagamento.get(i).getNumeroCarta()%>)">Elimina</button></td>
					</tr>
				<%
				}
				%>
				</table>
			<%
			}
			else {
			%>
				<br/>
				Nessun metodo di pagamento inserito
			<%
			}
			%>
			<br/><br/>
			<a href="./index"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>