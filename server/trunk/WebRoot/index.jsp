<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Mini-Pie Web Service</title>
	<link rel="stylesheet" type="text/css" href="style.css">
  </head>
  
  <body>
  	<div id="header" >
   		<h1 id="title" ><a href="./">Mini-Pie Web Service</a></h1>
    </div>
    <div id="content" >
	  	<h2>Welcome to Mini-Pie Web Service homepage!</h2>
	    <hr/>
	    <ul>
	    	<li><a href="./signup/">Sign Up</a>
	    	</li>
	    	<li><a href="">Documentations</a> (not available yet)
	    	</li>
	    	<li><a href="http://code.google.com/p/mini-pie">Google Code site</a>
	    	</li>
	    	<li><a href="http://groups.google.com/group/mini-pie">Team mailing list</a>
	    	</li>
	    	<li><a href="">About</a> (not available yet)
	    	</li>
	    </ul>
    </div>
	<div id="footer">
	</div>
  </body>
</html>
