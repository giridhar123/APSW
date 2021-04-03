<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login fallito</title>
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		
		<!-- Redirect alla home in 5 secondi -->
		<meta http-equiv="Refresh" content="5;url=index.jsp">
	</head>
	<body>
		<jsp:include page="header.jsp"/>
	
		<div class="content">
		I dati inseriti per il login non sono corretti.<br/>
		Verrai reindirizzato alla home page tra 5 secondi,<br/>
		in caso contrario clicca al seguente <a href="./index.jsp">link</a>
		</div>
		
		<jsp:include page="footer.jsp"/>
	</body>
</html>