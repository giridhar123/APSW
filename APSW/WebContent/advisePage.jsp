<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Avviso</title>
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<jsp:include page="./header.jsp"/>
	
		<div class="content">
			<%= request.getAttribute("errorText") %>
			
			<br/><br/>
			<a href="./<%= request.getAttribute("previousPage") %>"><button class="customButton">Indietro</button></a>
		</div>
		
		<jsp:include page="./footer.jsp"/>
	</body>
</html>