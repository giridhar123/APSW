<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pannello Amministratore</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
	
		<div class="content">
			<a href="../biglietteria/index"><button class="customButton">Biglietteria</button></a>
			<br/><br/>
			<a href="gestioneBar"><button class="customButton">Gestione bar</button></a>
			<br/><br/>
			<a href="permessi"><button class="customButton">Modifica permessi</button></a>
			<br/><br/>
			<a href="posti"><button class="customButton">Modifica posti</button></a>
			<br /><br />
			<a href="../cliente/modificaDati"><button class="customButton">Modifica dati personali</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>