<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nuovo prodotto bar</title>
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="content">
			
			<form action="creaProdottoBar" method="POST">
				
				<label>Nome prodotto:</label>
				<input type="text" name="nomeProdotto" required />
				<br/><br/>
				
				<label>Costo:</label>
				<input type="number" name="costo" step="0.01" min="0" required />
				<label>€</label>
				<br/><br/>
				
				<input type="submit" id="submit" class="customButton" value="Aggiungi"/>
				</form>
			<br/>
			<a href="./gestioneProdotti"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>