<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="Utils.Utils" %>
<%@ page import="Beans.Utente" %>


<%  
	/*
		Se l'utente è gia loggato
		si fa il redirect alla homepage della sezione relativa ai permessi dell'utente stesso
	*/
	Utente utente = Utils.getLoggedInUtente(request);
	if (utente != null)
	{
		switch (utente.getIdPermesso()) {
			case 0:
				response.sendRedirect("cliente/index");
				break;
			case 1:
				response.sendRedirect("bagnino/index");
				break;
			case 2:
				response.sendRedirect("cassiere/index");
				break;
			case 3:
				response.sendRedirect("biglietteria/index");
				break;
			case 4:
				response.sendRedirect("amministratore/index");
				break;
		}
	}
%>  

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/sha256.jquery.debug.js"></script>
		<script src="<%=request.getContextPath()%>/js/login.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
	
		<jsp:include page="header.jsp"/>
	
		<div class="content">	
			<form id="login_form" action='<%=request.getContextPath()%>/LoginServlet' method="post" onsubmit="return validateForm()">
				E-Mail
				<br/>
				<input id="j_username" type="text" name="j_username" size="18" />
				<br>
				<br/>
				Password<br/>
				<input type="hidden" id="j_password" name="j_password" value="">
				<input id="password" type="password" size="18" />
				<br>
				<br>
				<input type="submit" class="customButton" value="Log-In" />

			</form>
			
			<br/>
			<a href="<%=request.getContextPath()%>/forgotPassword.jsp">Password dimenticata?</a>
			<br/>
			<a href="<%=request.getContextPath()%>/signup.jsp">Registra un nuovo account</a>
		</div>
		
		<jsp:include page="footer.jsp"/>
	</body>
</html>