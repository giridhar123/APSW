<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.sql.Date" %>
<%@ page import="Beans.Ordine" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ordini in sospeso</title>
		<script src="../js/jquery.js"></script>
		<script src="js/ordiniSospesi.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="content">
			<%! @SuppressWarnings("unchecked") %>
			<%	List<Ordine> listaOrdini = (List<Ordine>) request.getAttribute("ordiniSospesi");
			if (listaOrdini.size() > 0) {
			%>
				<table class="customTable">
				<tr>
					<th>Codice ordine</th>
					<th>Data</th>
					<th>Cliente</th>
					<th>Metodo di pagamento</th>
				</tr>
			<%
				for (int i = 0; i < listaOrdini.size(); ++i) {
					String stato;
					String metodoPagamento=listaOrdini.get(i).getMetodoPagamento();
					if(metodoPagamento.equals("Contanti")){
					}else{
						String tmp = metodoPagamento.substring(0,4)+"************";
						metodoPagamento=tmp;
					}%>
					<tr>
						<td><%= listaOrdini.get(i).getCodOrdine() %></td>
						<td><%= (new SimpleDateFormat("dd/MM/yyyy").format(listaOrdini.get(i).getData())) %></td>
						<td><%= listaOrdini.get(i).getNomeUtente() %></td>
						<td><%= metodoPagamento %></td>
						<td><a href="./dettagliOrdine?codOrdine=<%= listaOrdini.get(i).getCodOrdine() %>"><button class="customButtonSmall">Visualizza dettagli ordine</button></a></td>
					</tr>
				<%
				}
				%>
				</table>
			<%
			} else {
			%>
				Nessun ordine in sospeso
				<br/><br/>
			<%
			}
			%>
			<a href="./index"><button class="customButton">Indietro</button></a>
			<br/>
		</div>
		<jsp:include page="../footer.jsp"/>
	</body>
</html>