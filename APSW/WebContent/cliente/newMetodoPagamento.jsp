<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nuovo metodo di pagamento</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		
		<script src="../js/jquery.js"></script>
		<script src="js/newMetodoPagamento.js"></script>
	</head>
	<body>
	
	<jsp:include page="../header.jsp"/>
	
	<div class="content">
		<br />
		<form onsubmit="return validateForm()">
			<label for="numeroCarta">Numero Carta:</label>
			<input type="text" name="numeroCarta" id="numeroCarta" minlength="16" maxlength="16" required />
			<br />
			<br />
			
			<label for="mese">Scadenza Mese:</label>
			<select name="mese" id="scadenzaMese">
				<%
					for (int i = 1; i <= 12; ++i) {
				%>
						<option><%=i%></option>
				<%
					}
				%>
			</select>
			<br />
			<br />
			
			<label for="anno">Scadenza Anno:</label>
			<select name="anno" id="scadenzaAnno">
				<%
					for (int i = 2020; i <= 2040; ++i) {
				%>
						<option><%=i%></option>
				<%
					}
				%>
			</select>
			<br />
			<br />
			
			<label for="vcc">VCC:</label>
			<input type="text" name="vcc" id="vcc" minlength="3" maxlength="3" required />
			<br />
			<br />
			<input type="submit" class="customButton" value="Crea" />
			<br />
			<br />
		</form>
		<a href="./metodiPagamento"><button class="customButton">Indietro</button></a>
		<br />
	</div>
	
	<jsp:include page="../footer.jsp"/>
	
	</body>
</html>