<%@ page language="java" import="java.util.*, org.net9.minipie.server.web.WebController" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String username = request.getParameter("username");
String displayname = request.getParameter("displayname");
String email = request.getParameter("email");
String password = request.getParameter("password");
if (username == null || username.isEmpty() || 
		displayname == null || displayname.isEmpty() ||
		email == null || email.isEmpty() || 
		password == null || password.isEmpty())
	response.sendRedirect("./signup/");
else try {
	long id = WebController.signUp(username, displayname, email, password);
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>Welcome - Mini-Pie Web Service</title>
	<link rel="stylesheet" type="text/css" href="./style.css">
  </head>
  
  <body>
    <h1 id="title" ><a href="./">Mini-Pie Web Service</a></h1>
  	<h2>Welcome!</h2>
    Congratulations! You've successfully signed up to Mini-Pie Web Service. Here's your registration information: <br>
    <table border="0">
        <tr>
          <td>Username:</td>
          <td><%=username %></td>
        </tr>
        <tr>
          <td>Display name:</td>
          <td><%=displayname %></td>
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
	<link rel="stylesheet" type="text/css" href="./style.css">
  </head>
  
  <body>
    <h1 id="title" ><a href="./">Mini-Pie Web Service</a></h1>
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
