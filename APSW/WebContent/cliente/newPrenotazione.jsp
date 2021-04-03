<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="Beans.MetodoPagamento" %>
<%@ page import="Beans.Utente" %>
<%@ page import="Beans.Posto" %>
<%@ page import="Beans.Prenotazione" %>
<%@ page import="Utils.Utils" %>
<%@ page import="java.util.List" %>
  
<%! @SuppressWarnings("unchecked") %>
    
<%
	List<MetodoPagamento> metodiPagamento = (List<MetodoPagamento>) request.getAttribute("metodiPagamento");
	List<Posto> listaPosti = (List<Posto>) request.getAttribute("listaPosti");
%>

<jsp:useBean id="utente" type="Beans.Utente" scope="request" />
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nuova prenotazione</title>
		<script src="../js/jquery.js"></script>
		<script src="js/newPrenotazione.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
	
		<jsp:include page="../header.jsp"/>
	
		<div class="content">
			<div id="errorDiv">
			</div>
			<br/>
			<form action="creaPrenotazione" method="POST">
				<label for="data">Data:</label>
				<input type="date" id="data" name="data" onChange="onDateChanged()" placeholder="yyyy-mm-dd" required />
				<br/><br/>
				
				<label for="number">Numero Persone:</label>
				<input type="number" id="numeroPersone" onChange="onNumeroPersoneChanged()" name="numeroPersone" min="1" max="4" required disabled/>
				<br/><br/>
				
				<label for="turno">Turno:</label>
				<select name="turno" id="turno" onChange="onTurnoChanged()" required disabled>
				</select>
				
				<br/><br/>
				
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
				<br/><br/>
			
				<label for="number">Nome:</label>
				<input type="text" name="nome" required value="${utente.nome}"/>
				<br/><br/>
				
				<label for="number">Cognome:</label>
				<input type="text" name="cognome" required value="${utente.cognome}"/>
				<br/><br/>
				
				<label for="numeroCarta">Metodo Pagamento:</label>
				<select name="numeroCarta" id="numeroCarta" required disabled>
					<%
						for (int i = 0; i < metodiPagamento.size(); ++i) {
					%>
						<option><%=metodiPagamento.get(i).getNumeroCarta().substring(0,4)+"************"%></option>
					<%
						}
					%>		
					<option>Contanti</option>		
				</select>
				<br/><br/>
				
				<input type="submit" id="submit" class="customButton" value="Prenota" disabled />
			</form>
			<br/><br/>
			<a href="./prenota"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>