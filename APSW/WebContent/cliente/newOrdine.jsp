<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="Beans.Ordine" %>
<%@ page import="Beans.ProdottoBar" %>
<%@ page import="Beans.MetodoPagamento" %>
<%@ page import="java.util.List" %>
<%@ page import="Utils.Utils" %>


<!DOCTYPE html>
<html>
	<head>
		<script src="../js/jquery.js"></script>
		<script src="js/newOrdine.js"></script>
		<meta charset="UTF-8">
		<title>Nuovo ordine</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="content">	
			
			<%! @SuppressWarnings("unchecked") %>
			<%
				List<ProdottoBar> prodottiBar=(List<ProdottoBar>) request.getAttribute("prodottiBar");
				List<MetodoPagamento> metodiPagamento = (List<MetodoPagamento>) request.getAttribute("metodiPagamento");
				
				
				if(prodottiBar.size()>0){
			%>
				<table id=prodotti class="customTable">
					<tr>
						<th>Prodotti</th>
						<th>Costo</th>
						<th>Quantità</th>
					</tr>
					
					<% for (int i=0; i<prodottiBar.size(); i++){
						int codProdotto=prodottiBar.get(i).getCodProdotto();
					%>
						<tr>
							<td><label id="nomeProdotto<%=codProdotto%>"><%= prodottiBar.get(i).getNomeProdotto() %></label></td>
							<td><label id="costo<%= codProdotto %>" ><%= prodottiBar.get(i).getCosto() %></label><label> €</label></td>
							<td><input type="number" id="quantitaProdottiBar<%=codProdotto%>" min="0" step="1" placeholder="Inserire quantità"></td>
							<td><button class="customButtonSmall" onclick="aggiungiProdotti('<%=codProdotto%>')">Aggiungi al carrello</button></td>
						</tr>
					<%}%>
				</table>
				<br />
				<label>Totale: </label><label id="conto">0</label>
				<br /><br />
				<div id="outputDiv">
				</div>
				<br /><br />
				<form action="./creaOrdineBar" method="POST">
					
					<label for="numeroCarta">Metodo Pagamento:</label>
					<select id="numeroCarta" name="numeroCarta" required disabled>
						<%
							for (int i = 0; i < metodiPagamento.size(); ++i) {
						%>
							<option><%=metodiPagamento.get(i).getNumeroCarta().substring(0,4)+"************"%></option>
						<%
							}
						%>		
						<option>Contanti</option>		
					</select>
					<br /><br />
				
					<input class="customButton" type="submit" id="submit" value="Procedi all'ordine" disabled/>
				</form>
				<%}else{%>
					Il bar è momentaneamente chiuso.
				<%}%>
				<br/><br/>
				<a href="./ordini"><button class="customButton">Indietro</button></a>
		</div>
		<jsp:include page="../footer.jsp"/>
	</body>
</html>