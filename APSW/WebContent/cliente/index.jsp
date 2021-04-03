<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cliente</title>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		
		<div class="content">
			<a href="metodiPagamento"><button class="customButton">Metodi di pagamento</button></a>
			<br />
			<br />
			<a href="prenota"><button class="customButton">Prenota</button></a>
			<br />
			<br />
			<a href="ordini"><button class="customButton">Ordini bar</button></a>
			<br />
			<br />
			<a href="modificaDati"><button class="customButton">Modifica dati personali</button></a>
			<br />
		</div>
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>