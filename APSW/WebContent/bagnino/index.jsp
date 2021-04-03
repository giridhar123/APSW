<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>  
<%@ page import="Beans.Posto" %> 
<%@ page import="Beans.Turno" %>  
<%@ page import="Beans.Prenotazione" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Bagnino</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
	
		<jsp:include page="../header.jsp"/>
		
		<div class="content">
			<%! @SuppressWarnings("unchecked") %>
			<%
			//Data di oggi
			List<Prenotazione> prenotazioni = (List<Prenotazione>) request.getAttribute("listaPrenotazioni");
			List<Prenotazione> prenotazioni1 = (List<Prenotazione>) request.getAttribute("prenotazioni");
			List<Posto> listaPosti = (List<Posto>) request.getAttribute("listaPosti");
			List<Turno> turni = (List<Turno>) request.getAttribute("turni");
			int turno = (int) request.getAttribute("turno");
			%>
			
			<%
			if (prenotazioni1.size() > 0) {
				//esiste almeno una prenotazione nella data odeirna
			%>
				<br/>
				<form action="">
					<label>Seleziona una fascia oraria</label>
					<select id="turnoSelezionato" name="turnoSelezionato" onchange="cambioTurno(<%=listaPosti%>, <%=prenotazioni%>)">
							<% for(int i=0; i<turni.size(); i++){ %>
								<option value="<%=turni.get(i).getCodTurno() %>"><%= turni.get(i).getFasciaOraria() %></option>
							<% } %>
					</select>
					<br/><br/>
					<input type="submit" id="submit" class="customButton" value="Vai" />
				</form>
				<br/><br/>
				
				<%
				if (prenotazioni.size() > 0) {
					//visualizzo le prenotazioni di oggi nella fascia oraria scelta
				%>
					Le prenotazioni di oggi sono per la fascia oraria <%=turni.get(turno-1).getFasciaOraria() %>: <br/><br/>
					
					<table id="postiOccupati">
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
										Cod postazione<br/><%=currentPosto.getCodPosto()%>
										<br/>
										N° sdraio<br/><%=currentPosto.getNumeroSdraio()%>
										<br/>
										<%for (int k=0; k<prenotazioni.size(); k++){
											if(prenotazioni.get(k).getRefPosto().getCodPosto()==currentPosto.getCodPosto()){%>
												<span style="color: red; font-style: italic"><%= prenotazioni.get(k).getNome() %>
												<%= prenotazioni.get(k).getCognome() %> </span><br/>
											<%}
										}
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
				<%} else { %>
					Non è prevista alcuna prenotazione per la fascia oraria <%=turni.get(turno-1).getFasciaOraria() %>
				<%}
			} else {
			%>
				Oggi non è prevista alcuna prenotazione
			<%
			}
			%>
			<br /><br />
			<a href="../cliente/modificaDati"><button class="customButton">Modifica dati personali</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>