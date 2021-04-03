<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Gestione bar</title>
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
	
		<div class="content">
			<a href="../cassiere/index"><button class="customButton">Gestione ordini</button></a>
			<br/><br/>
			<a href="gestioneProdotti"><button class="customButton">Gestione prodotti</button></a>
			<br/><br/>
			<a href="./index"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>