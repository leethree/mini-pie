<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="java.util.List"%>
<%@page import="org.net9.minipie.sample.xml.PersonBean"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<%@page import="org.net9.minipie.sample.exception.NotFoundException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phonebook Tag - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Phonebook Tag</h2>
	  	<hr/>
<%
	try {
		long id = Long.decode(request.getParameter("id"));
		try {
			TagBean tag = ses.getTagById(id);
%>
		<h3>Tag Information:</h3>
		<table>
			<tr>
				<th>ID</th>
				<td><%=tag.id %></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><%=tag.tagName %></td>
			</tr>
		</table>
		<h3>User Contacts with This Tag:</h3>
<%
	List<PersonBean> list = ses.listUserContactsWithTagId(id);
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
				<td><img alt="image" src=""/></td>
				<td><a href="contact.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
				<td><%=bean.get("permission") %></td>
				<td>
<%
		for (TagBean tag2 : bean.tags) {
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
		<h3>User Contacts with This Tag:</h3>
<%
	List<PersonBean> list2 = ses.listContactsWithTagId(id);
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
	for (PersonBean bean : list2) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><img alt="image" src=""/></td>
				<td><a href="contact.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
				<td><%=bean.get("permission") %></td>
				<td>
<%
		for (TagBean tag2 : bean.tags) {
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
<%
		}catch (NotFoundException ex) {
%>
			<p>You do not have a tag with ID = <%=id %>.</p>
<%
		} catch (GenericException ex) {
%>
			<p>Sorry, an error occurred:</p>
			<p><%=ex.getMessage() %></p>
<%	
		}
	} catch (Exception e) {
%>
		<form method="get">
			<span>ID:</span>
			<input type="text" name="id" />
			<input type="submit" value="submit" />
		</form>
<%
	}
%>
	</div>
</body>
</html>