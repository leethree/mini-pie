<%@ page language="java" import="java.util.*, org.net9.minipie.server.web.WebController" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
request.setCharacterEncoding("UTF-8");
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
     <div id="header" >
   		<h1 id="title" ><a href="./">Mini-Pie Web Service</a></h1>
    </div>
    <div id="content" >
	    <h2>Congratulations!</h2>
	    You've successfully signed up to Mini-Pie Web Service. Here's your registration information: <br>
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
      </div>
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
    <div id="header" >
   		<h1 id="title" ><a href="./">Mini-Pie Web Service</a></h1>
    </div>
     <div id="content" >
	    <h2>Sorry</h2>
	    We've encountered an error with the following message:<br>
		<p>
			<%=e.getMessage() %>
		</p>
	</div>
  </body>
</html>
<%
	//response.sendRedirect("error.jsp");
}
%>
