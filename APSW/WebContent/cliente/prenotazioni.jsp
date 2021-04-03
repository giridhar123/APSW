<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="Beans.Prenotazione" %>
<%@ page import="Beans.Turno" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Prenotazioni</title>
		
		<script src="../js/jquery.js"></script>
		<script src="js/prenotazioni.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="contentLarge">
			<br/>
			<a href="./newPrenotazione"><button class="customButton">Nuova Prenotazione</button></a>
			<br/>
			<%! @SuppressWarnings("unchecked") %>
			<%
			List<Prenotazione> listaPrenotazioni = (List<Prenotazione>) request.getAttribute("listaPrenotazioni");
			if (listaPrenotazioni.size() > 0) {
			%>
				<table class="customTable">
				<tr>
					<th>Data</th>
					<th>Turno</th>
					<th>Posto</th>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Metodo di pagamento</th>
					<th>Costo</th>
					<th>Pagato</th>
				</tr>
			<%
			
				for (int i = 0; i < listaPrenotazioni.size(); ++i) {
				%>
					<tr>
					<td><%= new SimpleDateFormat("dd/MM/yyyy").format(listaPrenotazioni.get(i).getData()) %></td>
					<td><%= listaPrenotazioni.get(i).getRefTurno().getFasciaOraria() %></td>
					<td><%= listaPrenotazioni.get(i).getRefPosto().getCodPosto() %></td>
					<td><%= listaPrenotazioni.get(i).getNome() %></td>
					<td><%= listaPrenotazioni.get(i).getCognome() %></td>
					<% 
						String metodoPagamento;
						if(listaPrenotazioni.get(i).getMetodoPagamento().getNumeroCarta().equals("Contanti")){
							metodoPagamento=listaPrenotazioni.get(i).getMetodoPagamento().getNumeroCarta();
						}else{
							String tmp = listaPrenotazioni.get(i).getMetodoPagamento().getNumeroCarta().substring(0,4)+"************";
							metodoPagamento=tmp;							
						}
						%>
					<td> <%= metodoPagamento %> </td>
					<td> <%= listaPrenotazioni.get(i).getCosto() %> € </td>
					<td><%= listaPrenotazioni.get(i).getPagato() ? "Si" : "No" %></td>
					<%
						Date oggi = new Date();
						if (listaPrenotazioni.get(i).getData().after(oggi)) {
					%>
							<td><button class="customButtonSmall" onClick="onDeleteButtonClicked(<%= listaPrenotazioni.get(i).getCodPrenotazione() %>)">X</button></td>
					<%
						}
					%>
					</tr>
				<%
				}
				%>
				</table>
			<%
			}
			else
			{
			%>
				<br/>
				Nessuna prenotazione effettuata
			<%
			}
			%>
			<br/><br/>
			<a href="./index"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>