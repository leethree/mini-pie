<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="java.util.List"%>
<%@page import="org.net9.minipie.sample.xml.PersonBean"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
<%@page import="org.net9.minipie.sample.xml.GenericBean"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<%@page import="org.net9.minipie.sample.exception.NotFoundException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.net9.minipie.sample.exception.ForbiddenException"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Group - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Browse Group</h2>
	  	<hr/>
<%
	String method = request.getParameter("method");
	try {
		long id = Long.decode(request.getParameter("id"));
%>
	  	<span>&gt; <a href="group.jsp">Groups</a> &gt; <%=id %></span>
	  	<hr/>
		<h4>Disband This Group (Admin Operation):</h4>
<%
		if (method != null && method.equals("delete")) {
			try{
				ses.disbandGroup(id);
%>
				<p>This group is disbanded.</p>
<%
				return;
			} catch (ForbiddenException ex) {
%>
				<p>You must be the group administrator to perform this operation.</p>
<%
			} catch (GenericException e) {
				e.printStackTrace();
%>
				<p>An error occurred while quitting</p>
<%
			}
		}
%>
		<form name="delete" id="delete" method="post">
			<input type="hidden" name="method" id="method" value="delete"/>
			<input type="submit" value="Disband" />
		</form>
<%
		try {
			GenericBean group = ses.getGroupById(id);
%>
		<h3>Group Information:</h3>
		<table>
			<tr>
				<th>ID</th>
				<td><%=group.id %></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><%=group.get("name") %></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><%=group.get("description") %></td>
			</tr>
			<tr>
				<th>Creator</th>
				<td><a href="../user.jsp?id=<%=group.get("creatorid") %>"><%=group.get("creator") %></a></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><%=group.get("permission") %></td>
			</tr>
		</table>
		<h3>User in This Group:</h3>
<%
	List<PersonBean> list = ses.listGroupUsers(id);
%>
		<table>
			<tr>
				<th>ID</th>
				<th>Image</th>
				<th>Name</th>
				<th>Group Admin</th>
			</tr>			
<%
	for (PersonBean bean : list) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><img alt="<%=bean.get("image") %>" src="<%=bean.get("image") %>"/></td>
				<td><a href="../user.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
				<td><%=bean.get("admin") %></td>
			</tr>
<%
	}
%>
		</table>
		<h3>Contacts Shared by Members in This Group:</h3>
<%
	List<PersonBean> list2 = ses.listGroupContacts(id);
%>
		<table>
			<tr>
				<th>ID</th>
				<th>Image</th>
				<th>Name</th>
			</tr>			
<%
	for (PersonBean bean : list2) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><img alt="<%=bean.get("image") %>" src="<%=bean.get("image") %>"/></td>
				<td><a href="contact.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
			</tr>
<%
	}
%>
		</table>
<%
		} catch (NotFoundException ex) {
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
			<input type="submit" value="Submit" />
		</form>
<%
	}
%>
	</div>
</body>
</html>