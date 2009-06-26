<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="java.util.List"%>
<%@page import="org.net9.minipie.sample.xml.PersonBean"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Users - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Browse Users</h2>
	  	<hr/>
	  	<span>&gt; Users</span>
	  	<hr/>
<%
	String method = request.getParameter("method");
	try {
%>
	  	<h4>Search Users:</h4>
	  	<form name="search" id="search" method="post">
	  		<input type="hidden" name="method" id="method" value="search"/>
			<span>Search Query:</span>
			<input type="text" name="query" />
			<input type="submit" value="Search" />
	  	</form>
<%
		if (method != null && method.equals("search")){
			String query = request.getParameter("query");
			if (query == null || query.isEmpty()) {
	%>
				<p>Query string should not be empty</p>
	<%
			} else {
				try{
	List<PersonBean> list = ses.searchUser(query);
%>
		<table>
			<tr>
				<th>ID</th>
				<th>Image</th>
				<th>Name</th>
			</tr>			
<%
	for (PersonBean bean : list) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><img alt="<%=bean.get("image") %>" src="<%=bean.get("image") %>"/></td>
				<td><a href="user.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
			</tr>
<%
	}
%>
		</table>
<%
				} catch (GenericException e) {
					e.printStackTrace();
	%>
					<p>An error occurred. Please check your input information</p>
	<%
				}
			}
		}
	} catch (Exception ex) {
%>
		<p>Sorry, an error occurred:</p>
		<p><%=ex.getMessage() %></p>
<%
	}
%>
	</div>
</body>
</html>