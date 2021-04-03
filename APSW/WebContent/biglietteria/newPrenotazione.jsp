<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="Beans.Posto" %>
<%@ page import="java.util.List" %>

<%! @SuppressWarnings("unchecked") %>
<% List<Posto> listaPosti = (List<Posto>) request.getAttribute("listaPosti"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nuova prenotazione</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		
		<script src="../js/jquery.js"></script>
		<script src="js/newPrenotazione.js"></script>
	</head>
	<body>
	
		<jsp:include page="../header.jsp"/>
	
		<div class="content">
			<br/>
			<div id="errorDiv">
			</div>
			<form action="creaPrenotazioneBiglietteria" method="POST">
				<label for="data">Data:</label>
				<input type="date" id="data" name="data" onChange="onDateChanged()" placeholder="yyyy-mm-dd" required />
				<br/><br/>
				
				<label for="numeroPersone">Numero Persone:</label>
				<input type="number" id="numeroPersone" onChange="onNumeroPersoneChanged()" name="numeroPersone" min="1" max="4" required disabled/>
				<br/><br/>
				
				<label for="turno">Turno:</label>
				<select name="turno" id="turno" onChange="onTurnoChanged()" required disabled>
				</select>

				<br/>
				<br/>
				
				<table id="tabellaPosti">
					<%
						int colonne = 5;
						int righe = (listaPosti.size() / colonne) + 1;
						int index = 0;
						
						for (int i = 0; i < righe; ++i) {
							%> <tr> <%
							for (int j = 0; j < colonne; ++j) {
								%> <td> <%
								try {
									Posto currentPosto = listaPosti.get(index);
									%>
									<br/>
									Codice Postazione<br/><%=currentPosto.getCodPosto()%>
									<br/>
									Numero sdraio<br/><%=currentPosto.getNumeroSdraio()%>
									<br/>
									<input type="radio" id="posto<%=currentPosto.getCodPosto()%>" name="codPosto" value="<%=currentPosto.getCodPosto()%>" onChange="updateCosto()" required disabled />
									<%
								}
								catch (IndexOutOfBoundsException exc) {
								}
								++index;
								%> </td> <%
							}
							%> </tr> <%
						}
					%>
				</table>
				
				Costo: <div id="costoTotale">0.00 €</div> 
				<br/>
			
				<label for="number">Nome:</label>
				<input type="text" name="nome" required />
				<br/><br/>
				
				<label for="number">Cognome:</label>
				<input type="text" name="cognome" required />
				<br/>
				<br/>
				
				<label for="isContanti">Contanti:</label>
				<input type="checkbox" name="isContanti" id="isContanti" value="Contanti" onChange="checkBoxChanged()" checked>
				<br/><br/>
				
				<label for="numeroCarta">Numero Carta:</label>
				<input type="text" name="numeroCarta" id="numeroCarta" minlength="16" maxlength="16" disabled required />
				<br><br/>
				
				<input class="customButton" type="submit" id="submit" class="customButton" value="Prenota" disabled />
			</form>
			<br/><br/>
			<a href="./index"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>