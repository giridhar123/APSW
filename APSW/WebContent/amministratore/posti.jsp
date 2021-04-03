<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="Beans.Posto" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifica posti</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
	
		<div class="content">			
			<a href="./newPosto"><button class="customButton">Nuovo Posto</button></a>
			<table class="customTable">
				<tr>
					<th>Codice</th>
					<th>Numero sdraio</th>
					<th>Costo</th>
				</tr>
				<%! @SuppressWarnings("unchecked") %>
				<%
					List<Posto> posti = (List<Posto>) request.getAttribute("posti");
				
					if (posti.size() > 0) {
						
						for (int i = 0; i < posti.size(); ++i) {
						%>
						<tr>
							<td><%=posti.get(i).getCodPosto()%></td>
							<td><%=posti.get(i).getNumeroSdraio()%></td>
							<td><%=posti.get(i).getCostoTotale()%> €</td>
						</tr>
						<%
						}
					}
				%>

			</table>
			
			<a href="./index"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>