<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="Beans.Prenotazione" %>
<%@ page import="Beans.Utente" %>

<%! @SuppressWarnings("unchecked") %>
<%
	Utente utente = (Utente) request.getAttribute("utente");
	List<Prenotazione> listaPrenotazioni = (List<Prenotazione>) request.getAttribute("listaPrenotazioni");
	String data = (String) request.getAttribute("data");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pagina Biglietteria</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		
		<script src="../js/jquery.js"></script>
		<script src="js/index.js"></script>
	</head>
	
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="contentLarge">
		
		<a href="newPrenotazioneBiglietteria"><button class="customButton">Nuova prenotazione</button></a>
		<br/><br/>
		
		<form action="">
			<input type="date" name="data" placeholder="yyyy-mm-dd" />
			<br/><br/>
			<input class="customButton" type="submit" value="Cerca" />
		</form>
		<%			
			if (listaPrenotazioni == null) {
		%>
			Seleziona una data
		<%
			}
			else if (listaPrenotazioni.size() > 0) {
		%>
			<br/><br/>
			Le prenotazioni della data <%=data%> sono:
			<br/>
			<br/>
			<table class="customTable">
				<tr>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Posto</th>
					<th>Numero Sdraio</th>
					<th>Fascia Oraria</th>
					<th>Costo</th>
					<th>Pagato</th>
					<th>Paga</th>
					<th>Elimina</th>
				</tr>
				<%
				for (int i = 0; i < listaPrenotazioni.size(); ++i) {
				%>
					<tr>
						<td><%=listaPrenotazioni.get(i).getNome()%></td>
						<td><%=listaPrenotazioni.get(i).getCognome()%></td>
						<td><%=listaPrenotazioni.get(i).getRefPosto().getCodPosto()%></td>
						<td><%=listaPrenotazioni.get(i).getRefPosto().getNumeroSdraio()%></td>
						<td><%=listaPrenotazioni.get(i).getRefTurno().getFasciaOraria()%></td>
						<td><%=listaPrenotazioni.get(i).getCosto()%> €</td>
						<td><%=listaPrenotazioni.get(i).getPagato() ? "Si" : "No" %></td>
						
						<% if (!listaPrenotazioni.get(i).getPagato()) { %>
							<td><a href="#"><button class="customButtonSmall" onClick="segnaPagato(<%=listaPrenotazioni.get(i).getCodPrenotazione()%>)">Paga</button></a></td>
						<% } else { %>
							<td></td>
						<% } %>
						
						<td>
							<button class="customButtonSmall" onClick="onDeleteButtonClicked(<%=listaPrenotazioni.get(i).getCodPrenotazione()%>)">X</button>
						</td>
					</tr>
				<%
				}
				%>
			</table>
			<%
			} else {
		%>
				<br/><br/>
				Non ci sono prenotazioni previste in data: <%=data%>
		<%
			}
		%>
		<br /><br />
		<a href="../cliente/modificaDati"><button class="customButton">Modifica dati personali</button></a>
		<%
			//Visualizza il tasto indietro solo se l'utente loggato è un amministratore 
			if (utente.getIdPermesso() == 4) {
				%>
				<br/><br/>
				<a href="../amministratore/index"><button class="customButton">Indietro</button></a>
				<%
			}
		%>
		
		</div>
		
		<jsp:include page="../footer.jsp"/>
	
	</body>
</html>