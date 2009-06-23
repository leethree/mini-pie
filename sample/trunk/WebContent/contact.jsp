<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="org.net9.minipie.sample.xml.PersonBean"%>
<%@page import="org.net9.minipie.sample.exception.NotFoundException"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<%@page import="org.net9.minipie.sample.exception.ForbiddenException"%>
<%@page import="org.net9.minipie.sample.xml.GenericBean"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Non-User Contact - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Browse Non-User Contact</h2>
	  	<hr/>
<%
	try {
		long id = Long.decode(request.getParameter("id"));
		try {
			PersonBean person = ses.browseContact(id);
%>
		<h3>Basic Information:</h3>
		<table>
			<tr>
				<th>ID</th>
				<td><%=person.id %></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><%=person.get("name") %></td>
			</tr>
			<tr>
				<th>Image</th>
				<td><img alt="<%=person.get("image") %>" src="<%=person.get("image") %>"></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><%=person.get("gender") %></td>
			</tr>
			<tr>
				<th>Birthday</th>
				<td><%=person.get("birthday") %></td>
			</tr>
			<tr>
				<th>Nickname</th>
				<td><%=person.get("nickname") %></td>
			</tr>
			<tr>
				<th>Notes</th>
				<td><%=person.get("note") %></td>
			</tr>
		</table>
		<span>This contact is shared by <a href="user.jsp?id=<%=person.get("ownerid") %>"><%=person.get("owner") %></a></span>
		<h3>Detailed Information:</h3>
		<h4>Addresses:</h4>
		<table>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Value</th>
				<th>Zipcode</th>
				<th>Primary</th>
			</tr>
		
<%
			for (GenericBean bean : person.addresses) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><%=bean.get("value") %></td>
				<td><%=bean.get("zipcode") %></td>
				<td><%=bean.get("primary") %></td>
			</tr>
<%		
			}
%>
		</table>
		<h4>Phone Numbers:</h4>
		<table>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Value</th>
				<th>Primary</th>
			</tr>
		
<%
			for (GenericBean bean : person.phones) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><%=bean.get("value") %></td>
				<td><%=bean.get("primary") %></td>
			</tr>
<%		
			}
%>
		</table>
		<h4>Emails:</h4>
		<table>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Value</th>
				<th>Primary</th>
			</tr>
		
<%
			for (GenericBean bean : person.emails) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><a href="mailto:<%=bean.get("value") %>"><%=bean.get("value") %></a></td>
				<td><%=bean.get("primary") %></td>
			</tr>
<%		
			}
%>
		</table>
		<h4>IMs:</h4>
		<table>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Value</th>
				<th>Primary</th>
			</tr>
		
<%
			for (GenericBean bean : person.ims) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><%=bean.get("value") %></td>
				<td><%=bean.get("primary") %></td>
			</tr>
<%		
			}
%>
		</table>
		<h4>URLs:</h4>
		<table>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Value</th>
				<th>Primary</th>
			</tr>
		
<%
			for (GenericBean bean : person.urls) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><a href="<%=bean.get("value") %>"><%=bean.get("value") %></a></td>
				<td><%=bean.get("primary") %></td>
			</tr>
<%		
			}
%>
		</table>
<%
		} catch (NotFoundException ex) {
%>
		<p>The requested contact with ID = <%=id %> does not exist.</p>
<%
		} catch (ForbiddenException ex) {
%>
			<p>Sorry, you're not allowed to view this contact.</p>
<%
		} catch (GenericException ex) {
%>
			<p>Sorry, an error occurred:</p>
			<p><%=ex.getMessage() %></p>
<%	
		}
	} catch (Exception e) {
%>
		<p>Please input an ID to continue:</p>
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