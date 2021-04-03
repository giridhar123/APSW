<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="Utils.Utils" %>
<%@ page import="Beans.Utente" %>

<!DOCTYPE html>
<html>
	<footer>
	
	<%
		/*
		*	Se l'utente è loggato mostro la voce di logout
		*/
		Utente utente = Utils.getLoggedInUtente(request);
		if (utente != null){
	%>
		<a href="<%=request.getContextPath()%>/logout">Logout</a>
	<%
		}
	%>
	
	<br/>
	La Valle Beach - Siracusa <br/>
	Università degli studi di Palermo<br/>
	Anno Accademico 2019-2020<br/>
	Architetture e Progettazione di Sistemi Web<br/>
	Irene Siragusa<br/>
	Davide Lavalle<br/>
	
	</footer>
</html>