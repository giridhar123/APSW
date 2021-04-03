<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.lang.String" %>
<%@ page import="Beans.Ordine" %>
<%@ page import="java.sql.Date" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ordini completati</title>
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="content">
		
			<form action="">
			<input type="date" name="data" placeholder="yyyy-mm-dd" />
			<br/><br/>
			<input type="submit" class="customButton" value="Cerca" />
			<br/><br/>
		</form>
		
		<%! @SuppressWarnings("unchecked") %>
		<%
			List<Ordine> ordiniCompletati = (List<Ordine>) request.getAttribute("oridiniCompletati");
			String data = (String) request.getAttribute("data");
			
			if (ordiniCompletati == null) {
		%>
			Seleziona una data
		<%
			}
			else if (ordiniCompletati.size() > 0) {
		%>
			Gli ordini del bar serviti in data <%=data%> sono:
			<br/>
			<br/>
			<table class="customTable">
				<tr>
					<th>Codice ordine</th>
					<th>Data</th>
					<th>Cliente</th>
					<th>Metodo di pagamento</th>
				</tr>
			<%
				for (int i = 0; i < ordiniCompletati.size(); ++i) {
					String stato;
					String metodoPagamento=ordiniCompletati.get(i).getMetodoPagamento();
					if(metodoPagamento.equals("Contanti")){
					}else{
						String tmp = metodoPagamento.substring(0,4)+"************";
						metodoPagamento=tmp;
					}%>
					<tr>
						<td><%= ordiniCompletati.get(i).getCodOrdine() %></td>
						<td><%= (new SimpleDateFormat("dd/MM/yyyy").format(ordiniCompletati.get(i).getData())) %></td>
						<td><%= ordiniCompletati.get(i).getNomeUtente() %></td>
						<td><%= metodoPagamento %></td>
						<td><a href="./dettagliOrdine?codOrdine=<%= ordiniCompletati.get(i).getCodOrdine() %>"><button class="customButtonSmall">Visualizza dettagli ordine</button></a></td>
					</tr>
				<%
				}
				%>
				</table>
			<%
			}else {
		%>
				<br/><br/>
				Non ci sono ordini completati in data: <%=data%>
		<%
			}
		%>
			<br/><br/>
			<a href="./index"><button class="customButton">Indietro</button></a>
		</div>
		<jsp:include page="../footer.jsp"/>
	</body>
</html>