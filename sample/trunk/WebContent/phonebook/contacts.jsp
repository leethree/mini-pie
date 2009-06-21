<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="java.util.List"%>
<%@page import="org.net9.minipie.sample.xml.PersonBean"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phonebook Non-User Contacts - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Phonebook Non-User Contacts</h2>
	  	<hr/>
<%
	List<PersonBean> list = ses.listContacts();
%>
		<table>
			<tr>
				<th>ID</th>
				<th>Image</th>
				<th>Name</th>
				<th>Permission</th>
				<th>Tags</th>
			</tr>			
<%
	for (PersonBean bean : list) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><img alt="<%=bean.get("image") %>" src="<%=bean.get("image") %>"/></td>
				<td><a href="contact.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
				<td><%=bean.get("permission") %></td>
				<td>
<%
		for (TagBean tag : bean.tags) {
%>
				<span><a href="tag.jsp?id=<%=tag.id %>"><%=tag.tagName %></a> </span>
<%
		}
%>
				</td>
			</tr>
<%
	}
%>
		</table>
	</div>
</body>
</html>