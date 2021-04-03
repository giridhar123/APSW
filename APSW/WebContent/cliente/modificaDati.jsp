<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="utente" type="Beans.Utente" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifica Dati</title>
		
		<script src="../js/jquery.js"></script>
		<script src="../js/sha256.jquery.debug.js"></script>
		<script src="js/modificaDati.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
	
		<div class="content">
			<div id="errors">
			</div>
			<form onsubmit="return validateForm()">
				<label for="number">Nome:</label>
				<input type="text" id="nome" required value="${utente.nome}"/>
				<br/><br/>
				
				<label for="number">Cognome:</label>
				<input type="text" id="cognome" required value="${utente.cognome}"/>
				<br/><br/>
				
				<label for="name">E-mail:</label>
				<input type="email" id="email" onChange="checkEmail()" required value="${utente.email}"/>
				<br/><br/>
				
				<label for="name">Ripeti E-mail:</label>
				<input type="email" id="repeatEmail" required value="${utente.email}"/>
				<br/><br/>
				
				<label for="name">Password corrente:</label>
				<input type="password" id="password" onChange="checkPassword()" required />
				<br/><br/>
				
				<label for="name">Nuova Password:</label>
				<input type="password" id="newPassword" />
				<br/><br/>
				
				<label for="name">Ripeti Nuova Password:</label>
				<input type="password" id="repeatNewPassword" />
				<br/><br/>
				
				<input class="customButton" type="submit" value="Modifica" />
			</form>
			<br/><br/>
			
			<%
			//Il tasto indietro fa tornare alla home di ciascun utente
			if (utente.getIdPermesso() == 4) {
				%>
				<a href="../amministratore/index"><button class="customButton">Indietro</button></a>
				<%
			}else if(utente.getIdPermesso() == 3){
				%>
				<a href="../biglietteria/index"><button class="customButton">Indietro</button></a>
				<%
			}else if(utente.getIdPermesso() == 2){
				%>
				<a href="../cassiere/index"><button class="customButton">Indietro</button></a>
				<%
			}else if(utente.getIdPermesso() == 1){
				%>
				<a href="../bagnino/index"><button class="customButton">Indietro</button></a>
				<%
			}else if(utente.getIdPermesso() == 0){%>
				<a href="./index"><button class="customButton">Indietro</button></a>
			<% }%>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>