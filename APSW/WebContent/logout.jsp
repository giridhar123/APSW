<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="Utils.Utils" %>
<%@ page import="Beans.Utente" %>

<jsp:useBean id="utente" type="Beans.Utente" scope="request" />
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Logout</title>
		
		<link rel="stylesheet" type="text/css" href="css/main.css">
		
		<!-- Redirect alla home in 3 secondi -->
		<meta http-equiv="Refresh" content="3;url=index.jsp">
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		
		<div class="content">
			Arrivederci ${utente.nome}
			<%request.logout(); %>
			<br/>
			Logout effettuato con successo.<br/>
			Verrai reindirizzato alla home page tra 3 secondi,<br>
			in caso contrario clicca al seguente <a href="./index.jsp">link</a>
		</div>
		
		<jsp:include page="footer.jsp"/>
		
	</body>
</html>
