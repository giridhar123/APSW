<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="Beans.ProdottoBar" %>    

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifica prodotto</title>
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="content">
			<%
				ProdottoBar prodottoBar = (ProdottoBar) request.getAttribute("prodottoBar");
			%>
			
			<form action="modifyProdottoBar" method="POST">
				
				<label>Nome prodotto:</label>
				<input type="text" name="nomeProdotto" required value="<%= prodottoBar.getNomeProdotto()%>"/>
				<br/><br/>
				
				<label>Costo:</label>
				<input type="number" name="costo" step="0.01" min="0" required value="<%= prodottoBar.getCosto()%>"/>
				<label>€</label>
				<br/><br/>
				
				<input type="hidden" name="codProdotto" value="<%= prodottoBar.getCodProdotto()%>"/>
			
				<input type="submit" id="submit" class="customButton" value="Modifica"/>
				<br/><br/>
				<a href="./gestioneProdotti"><button class="customButton">Indietro</button></a>
			</form>
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>