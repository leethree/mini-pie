<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.net9.minipie.sample.WebController"%>
<%@page import="org.net9.minipie.sample.exception.LoginFailedException"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Logout - Mini-Pie Sample</title>
</head>
<body>
  	<div id="content" >
	  	<h2>Log Out</h2>
	  	<hr/>
<%
	WebController controller = new WebController(request);
	if (!controller.isLoggedIn()) {
%>
		<p>You've already logged out.</p>
<%
	} else {
		try {
			controller.logout();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
%>
		<p>You've successfully logged out.</p>
<%
	}
%>
    </div>
</body>
</html>