<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="Beans.ProdottoBar" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
	<head>
		<script src="../js/jquery.js"></script>
		<script src="js/gestioneProdotti.js"></script>
		<meta charset="UTF-8">
		<title>Gestione prodotti bar</title>
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
	
		<div class="content">
			<%! @SuppressWarnings("unchecked") %>
			<%
				List<ProdottoBar> prodottiBar=(List<ProdottoBar>) request.getAttribute("prodottiBar");
				
				if(prodottiBar.size()>0){
			%>
				<table id=prodotti class="customTable">
					<tr>
						<th>Prodotti</th>
						<th>Costo</th>
					</tr>
					
					<% for (int i=0; i<prodottiBar.size(); i++){
						int codProdotto=prodottiBar.get(i).getCodProdotto();
					%>
						<tr>
							<td><label id="nomeProdotto<%=codProdotto%>"><%= prodottiBar.get(i).getNomeProdotto() %></label></td>
							<td><label id="costo<%= codProdotto %>" ><%= prodottiBar.get(i).getCosto() %></label><label> €</label></td>
							<td><a href="./modificaProdottoBar?codProdotto=<%= codProdotto %>"><button class="customButtonSmall">Modifica prodotto</button></a></td>
							<td><button class="customButtonSmall" onclick="deleteProdotto('<%=codProdotto%>')">Elimina prodotto</button></td>
						</tr>
					<%}%>
				</table>
				<%}else{%>
					Non ci sono prodotti nel bar.
				<%} %>
				<br />
				<a href="newProdottoBar"><button class="customButton">Aggiungi nuovo prodotto</button></a>
				<br/><br/>
				<a href="./gestioneBar"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>