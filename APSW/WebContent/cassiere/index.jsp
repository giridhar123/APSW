<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="Beans.Utente" %>

<%
	Utente utente = (Utente) request.getAttribute("utente");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pagina Cassiere</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="content">		
			<a href="newOrdineBar"><button class="customButton">Crea nuovo ordine</button></a>
			<br/><br/>
			<a href="ordiniSospesi"><button class="customButton">Ordini in sospeso</button></a>
			<br/><br/>
			<a href="ordiniCompletati"><button class="customButton">Vedi ordini</button></a>
			<br/><br/>
			<a href="../cliente/modificaDati"><button class="customButton">Modifica dati personali</button></a>
			<br /><br />
			
			<%
				//Visualizza il tasto indietro solo se l'utente loggato è un amministratore 
				if (utente.getIdPermesso() == 4) {
					%>
					<a href="../amministratore/index"><button class="customButton">Indietro</button></a>
					<br/>
					<%
				}
			%>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	
	</body>
</html>