<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Errore</title>
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<jsp:include page="./header.jsp"/>
	
		<div class="content">
			
			Si è verificato un errore
			<br/><br/>
			<a href="<%= request.getContextPath()%>/index.jsp"><button class="customButton">Torna alla home</button></a>
		</div>
		
		<jsp:include page="./footer.jsp"/>
	</body>
</html>