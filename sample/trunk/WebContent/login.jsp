<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.net9.minipie.sample.WebController"%>
<%@page import="org.net9.minipie.sample.exception.LoginFailedException"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login - Mini-Pie Sample</title>
</head>
<body>
  	<div id="content" >
	  	<h2>Log In</h2>
	  	<hr/>
<%
	request.setCharacterEncoding("UTF-8");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	WebController controller = new WebController(request);
	if (controller.isLoggedIn()) {
%>
		<p>You've already logged in.</p>
<%
	} else if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
%>
	  	<p>Please log in to continue:</p>
	    <form name="login" id="login" method="post">
	      <table>
	        <tr>
	          <td>Username:</td>
	          <td><input type="text" name="username" id="username"/></td>
	        </tr>
	        <tr>
	          <td>Password:</td>
	          <td><input type="password" name="password" id="password"/></td>
	        </tr> 
	        <tr>
	          <td colspan="2" align="center"><input type="submit" value="Submit"/></td>
	        </tr>
	      </table>
	    </form>
<%
	} else {
		try {
			controller.login(username, password);
			String cont = request.getParameter("continue");
			if (cont != null && !cont.isEmpty()) {
				response.sendRedirect(cont);
				return;
			}
%>
			<p>You've successfully logged in as <%=username %>.</p>
<%
		} catch(LoginFailedException e){
%>
		<p>Login failed.</p>
<%
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
			return;
		}
	}
%>
    </div>
</body>
</html>