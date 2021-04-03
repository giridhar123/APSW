<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registrazione</title>
		
		<script src="js/jquery.js"></script>
		<script src="js/sha256.jquery.debug.js"></script>
		<script src="js/signup.js"></script>
		
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>
	
		<jsp:include page="header.jsp"/>
		
		<div class="content">
			<div id="errors">
			</div>
			<form action="SignUp" method="POST" onsubmit="return validateForm()">
				<label for="name">Nome:</label>
				<input type="text" name="nome" required />
				<br/><br/>
	
				<label for="name">Cognome:</label>
				<input type="text" name="cognome" required />
				<br/><br/>
				
				<label for="name">E-mail:</label>
				<input type="email" name="email" id="email" onChange="checkEmail()" required />
				<br/><br/>
				
				<label for="name">Ripeti E-mail:</label>
				<input type="email" id="repeatEmail" required />
				<br/><br/>
				
				<label for="name">Password:</label>
				<input type="password" name="password" id="password" required />
				<br/><br/>
				
				<label for="name">Ripeti password:</label>
				<input type="password" id="repeatPassword" required />
				<br/>
				<br/>
				<input class="customButton" type="submit" value="Registra" />
			</form>	
		</div>
		
		<jsp:include page="footer.jsp"/>
	</body>
</html>