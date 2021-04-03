<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nuovo posto</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
	
		<div class="content">
			<form action="creaPosto" method="POST">				
				<label for="numeroSdraio">Numero sdraio:</label>
				<input type="number" name="numeroSdraio" min="1" max="4" required />
				<br/><br/>
			
				<label for="Costo">Costo:</label>
				<input type="number" name="costoTotale" min="0" required />
				<br/><br/>
				
				<input type="submit" class="customButton" value="Crea" />
			</form>
			<br/>
			<a href="./posti"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>