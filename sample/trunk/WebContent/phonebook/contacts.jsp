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
<title>Phonebook Non-User Contacts - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Phonebook Non-User Contacts</h2>
	  	<hr/>
	  	<span>&gt; <a href="index.jsp">Phonebook</a> &gt; Contacts</span>
	  	<hr/>
<%
	String method = request.getParameter("method");
	try {
%>
	  	<h4>Create New Contact:</h4>
<%
		if (method != null && method.equals("add")){
			String contactname = request.getParameter("contactname");
			if (contactname == null || contactname.isEmpty()) {
	%>
				<p>Contact name should not be empty</p>
	<%
			} else {
				try{
					ses.createContact(contactname);
	%>
					<p>Contact updated successfully</p>
	<%
				} catch (GenericException e) {
					e.printStackTrace();
	%>
					<p>An error occurred. Please check your input information</p>
	<%
				}
			}
		}
%>
	  	<form name="add" id="add" method="post">
	  		<input type="hidden" name="method" id="method" value="add"/>
			<span>New contact name:</span>
			<input type="text" name="contactname" />
			<input type="submit" value="Submit" />
	  	</form>
<%
	List<PersonBean> list = ses.listContacts();
%>
		<table>
			<tr>
				<th>ID</th>
				<th>Image</th>
				<th>Name</th>
				<th>Permission</th>
				<th>Relationships</th>
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
				<td><%=bean.get("rel") %></td>
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
<%
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