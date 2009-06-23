<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="org.net9.minipie.sample.xml.PersonBean"%>
<%@page import="org.net9.minipie.sample.exception.NotFoundException"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<%@page import="org.net9.minipie.sample.xml.GenericBean"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Profile</h2>
	  	<hr/>
	  	<a href="editprofile.jsp">Edit profile</a>
<%
	try {
		PersonBean person = ses.getProfile();
%>
		<a href="user.jsp?id=<%=person.id %>">Switch to browse mode</a>
<%
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
				<th>Username</th>
				<td><%=person.get("username") %></td>
			</tr>
			<tr>
				<th>Registration Email</th>
				<td><%=person.get("register_email") %></td>
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
				<th>Gender Permission</th>
				<td><%=person.get("gender_perm") %></td>
			</tr>
			<tr>
				<th>Birthday</th>
				<td><%=person.get("birthday") %></td>
			</tr>
			<tr>
				<th>Birth Year Permission</th>
				<td><%=person.get("birth_year_perm") %></td>
			</tr>
			<tr>
				<th>Birth Date Permission</th>
				<td><%=person.get("birth_date_perm") %></td>
			</tr>
			<tr>
				<th>Nickname</th>
				<td><%=person.get("nickname") %></td>
			</tr>
			<tr>
				<th>Notes</th>
				<td><%=person.get("note") %></td>
			</tr>
			<tr>
				<th>Add-as-contact Permission</th>
				<td><%=person.get("add_as_contact_perm") %></td>
			</tr>
		</table>
		<h3>Detailed Information:</h3>
		<h4>Addresses:</h4>
		<table>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Value</th>
				<th>Zipcode</th>
				<th>Primary</th>
				<th>Permission</th>
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
				<td><%=bean.get("permission") %></td>
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
				<th>Permission</th>
			</tr>
		
<%
			for (GenericBean bean : person.phones) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><%=bean.get("value") %></td>
				<td><%=bean.get("primary") %></td>
				<td><%=bean.get("permission") %></td>
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
				<th>Permission</th>
			</tr>
		
<%
			for (GenericBean bean : person.emails) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><a href="mailto:<%=bean.get("value") %>"><%=bean.get("value") %></a></td>
				<td><%=bean.get("primary") %></td>
				<td><%=bean.get("permission") %></td>
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
				<th>Permission</th>
			</tr>
		
<%
			for (GenericBean bean : person.ims) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><%=bean.get("value") %></td>
				<td><%=bean.get("primary") %></td>
				<td><%=bean.get("permission") %></td>
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
				<th>Permission</th>
			</tr>
		
<%
			for (GenericBean bean : person.urls) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><a href="<%=bean.get("value") %>"><%=bean.get("value") %></a></td>
				<td><%=bean.get("primary") %></td>
				<td><%=bean.get("permission") %></td>
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