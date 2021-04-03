<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Password dimenticata</title>
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		
		<div class="content">
			Inserisci l'e-mail dell'account di cui non ricordi la password.<br/><br/>
			<form action="./ForgotPassword" method="POST">
				<input type="email" name="email" required />
				<br/><br/>
				
				<input class="customButton" type="submit" value="Conferma" />
			</form>
			<br/><br/>
			<a href="./index.jsp"><button class="customButton">Indietro</button></a>
		</div>
	
		<jsp:include page="footer.jsp"/>	
	</body>
</html>