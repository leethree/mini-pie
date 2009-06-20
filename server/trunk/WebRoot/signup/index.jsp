<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Sign Up - Mini-Pie Web Service</title>
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
  </head>
  
  <body>
  	<h1>Mini-Pie Web Service</h1>
  	<h2>Sign Up</h2>
    <form name="signup" id="signup" action="signup.jsp" method="post">
      <table border="0">
        <tr>
          <td>Username:</td>
          <td><input type="text" name="username" id="username"/></td>
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
    </form>
  </body>
</html>
