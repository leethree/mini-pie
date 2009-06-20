<%@ page language="java" import="java.util.*, org.net9.minipie.server.web.WebController" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String username = request.getParameter("username");
String email = request.getParameter("email");
String password = request.getParameter("password");
if (username == null || username.isEmpty())
	response.sendRedirect("index.jsp");
try {
	long id = WebController.signUp(username, email, password);
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>Welcome - Mini-Pie Web Service</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <h1>Mini-Pie Web Service</h1>
  	<h2>Welcome!</h2>
    Congratulations! You've successfully signed up to Mini-Pie Web Service. Here's your registration information: <br>
    <table border="0">
        <tr>
          <td>Username:</td>
          <td><%=username %></td>
        </tr>
        <tr>
          <td>Email:</td>
          <td><%=email %></td>
        </tr>
        <tr>
          <td>User ID:</td>
          <td><%=id %></td>
        </tr> 
      </table>
  </body>
</html>
<%
} catch (Exception e) {
	e.printStackTrace();
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>Error - Mini-Pie Web Service</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <h1>Mini-Pie Web Service</h1>
  	<h2>Sorry</h2>
    We encountered an error with the following information:<br>
	<p>
		<%=e.getMessage() %>
	</p>
  </body>
</html>
<%
	//response.sendRedirect("error.jsp");
}
%>
