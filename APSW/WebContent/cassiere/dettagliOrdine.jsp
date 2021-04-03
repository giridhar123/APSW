<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="Beans.DettagliOrdine" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Beans.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>    

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dettagli ordine</title>
		<script src="../js/jquery.js"></script>
		<script src="js/dettagliOrdine.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		
		<%! @SuppressWarnings("unchecked") %>
		<%
			List<DettagliOrdine> dettagli=(List<DettagliOrdine>) request.getAttribute("dettagli");
			Ordine ordine= (Ordine) request.getAttribute("ordine");
		%>
		<jsp:include page="../header.jsp"/>
		
		<div class="content">	
			<table class="customTable">
				<tr>
					<th>Codice ordine</th>
					<th>Data</th>
					<th>Cliente</th>
					<th>Metodo di pagamento</th>
				</tr>
				<tr>
					<td><%=ordine.getCodOrdine() %></td>
					<td><%=(new SimpleDateFormat("dd/MM/yyyy").format(ordine.getData())) %></td>
					<td><%=ordine.getNomeUtente() %></td>
					<td>
						<% 
						String metodoPagamento;
						if(ordine.getMetodoPagamento().equals("Contanti")){
							metodoPagamento=ordine.getMetodoPagamento();
						}else{
							String tmp = ordine.getMetodoPagamento().substring(0,4)+"************";
							metodoPagamento=tmp;							
						}
						%>
						<%=metodoPagamento%>

					</td>
					<td>
						<% 
						if(ordine.getServito()==0){%>
							<button class="customButtonSmall" onclick="onComplete(<%= ordine.getCodOrdine() %>)">Completa ordine</button>		
						<%}%>
						</td>
				</tr>
			</table>
			
			<table class="customTable">
				<tr>
					<th>Prodotto</th>
					<th>Quantita</th>
					<th>Costo</th>
				</tr>
				<% float totale=0;
				for (int i=0; i<dettagli.size(); i++){%>
					<tr>
					<td><%=dettagli.get(i).getProdotto()%></td>
					<td>x <%=dettagli.get(i).getQuantita()%></td>
					<td><%=dettagli.get(i).getCosto()%></td>
					</tr>
				<%totale += dettagli.get(i).getCosto();
				} %>
			</table>
			<label>Totale : <%=totale %> €</label>
			<br/><br/>
			<a href="./index"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>