<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Sign Up - Mini-Pie Web Service</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./style.css">
  </head>
  
  <body>
  	<div id="header" >
  		<h1 id="title" ><a href="./">Mini-Pie Web Service</a></h1>
  	</div>
  	<div id="content" >
	  	<h2>Sign Up</h2>
	    <form name="signup" id="signup" action="./signup/signup.jsp" method="post">
	      <table border="0">
	        <tr>
	          <td>Username:</td>
	          <td><input type="text" name="username" id="username"/></td>
	        </tr>
	        <tr>
	          <td>Display name:</td>
	          <td><input type="text" name="displayname" id="displayname"/></td>
	        </tr>
	        <tr>
	          <td>Email:</td>
	          <td><input type="text" name="email" id="email"/></td>
	        </tr>
	        <tr>
	          <td>Password:</td>
	          <td><input type="password" name="password" id="password"/></td>
	        </tr> 
	        <tr>
	          <td colspan="2" align="center"><input type="submit" value="Submit"/></td>
	        </tr>
	      </table>
	      These information could not be modified after submitting.
	    </form>
    </div>
  </body>
</html>
