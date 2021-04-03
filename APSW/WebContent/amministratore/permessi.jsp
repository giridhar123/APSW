<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifica Permessi</title>
		
		<script src="../js/jquery.js"></script>
		<script src="js/permessi.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
	
		<div class="content">
			Inserisci l'E-Mail dell'account di cui si vuole modificare i permessi
			<br/><br/>
			<input type="email" id="email" name="email" required />
			<p id="errorText"></p>
			<br/>
				
			<button id="submit" class="customButton" onClick="onSubmitButtonClicked()">Cerca</button>
			
			<br/><br/>
			<div id = "outputDiv">
			</div>
			
			<br/>
			<a href="./index"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>