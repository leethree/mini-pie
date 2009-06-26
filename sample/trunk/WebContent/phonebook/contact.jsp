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
<title>Phonebook Non-User Contact - Mini-Pie Sample</title>
</head>
<body>
<div id="content">
<h2>Phonebook Non-User Contact</h2>
<hr />
<%
	String method = request.getParameter("method");
	try {
		long id = Long.decode(request.getParameter("id"));
%>
	  	<span>&gt; <a href="index.jsp">Phonebook</a> &gt; <a href="contacts.jsp">Contacts</a> &gt; <%=id %></span>
	  	<hr/>
 <a href="../contact.jsp?id=<%=id%>">Switch to browse mode</a>
 <a href="editcontact.jsp?id=<%=id%>">Edit contact</a>
 <%
 	if (method != null && method.equals("delete")) {
 			try {
 				ses.deleteContact(id);
 %>
<p>Contact deleted successfully</p>
<%
	return;
			} catch (GenericException e) {
				e.printStackTrace();
%>
<p>An error occurred while deleting the contact</p>
<%
	}
		}
%>
<form name="delete" id="delete" method="post"><input
	type="hidden" name="method" id="method" value="delete" /> <input
	type="submit" value="Delete" /></form>
<h4>Edit contact-sharing permission</h4>
<%
	if (method != null && method.equals("perm")) {
			String perm = request.getParameter("perm");
			if (perm != null && !perm.isEmpty()) {
				try {
					ses.editContactPermission(id, perm);
%>
<p>Permission updated successfully</p>
<%
	} catch (GenericException e) {
					e.printStackTrace();
%>
<p>An error occurred while updating the contact</p>
<%
	}
			}
		}
%>
<form name="perm" id="perm" method="post"><input type="hidden"
	name="method" id="method" value="perm" /> <select name="perm">
	<option value="to_self">Visible to myself</option>
	<option value="to_contacts">Visible to contacts</option>
	<option value="to_everyone">Visible to everyone</option>
</select> <input type="submit" value="Submit" /></form>
<%
	try {
			PersonBean person = ses.getContactById(id);
%>
<h3>Basic Information:</h3>
<table>
	<tr>
		<th>ID</th>
		<td><%=person.id%></td>
	</tr>
	<tr>
		<th>Name</th>
		<td><%=person.get("name")%></td>
	</tr>
	<tr>
		<th>Image</th>
		<td><img alt="<%=person.get("image")%>"
			src="<%=person.get("image")%>"></td>
	</tr>
	<tr>
		<th>Gender</th>
		<td><%=person.get("gender")%></td>
	</tr>
	<tr>
		<th>Birthday</th>
		<td><%=person.get("birthday")%></td>
	</tr>
	<tr>
		<th>Nickname</th>
		<td><%=person.get("nickname")%></td>
	</tr>
	<tr>
		<th>Notes</th>
		<td><%=person.get("note")%></td>
	</tr>
	<tr>
		<th>Relationship</th>
		<td><%=person.get("rel")%></td>
	</tr>
	<tr>
		<th>Permission</th>
		<td><%=person.get("permission")%></td>
	</tr>
	<tr>
		<th>Tags</th>
		<td>
		<%
			for (TagBean tag : person.tags) {
		%> <span><a href="tag.jsp?id=<%=tag.id%>"><%=tag.tagName%></a>
		</span> <%
 	}
 %>
		</td>
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
	</tr>

	<%
		for (GenericBean bean : person.addresses) {
	%>
	<tr>
		<td><%=bean.id%></td>
		<td><%=bean.get("type")%></td>
		<td><%=bean.get("value")%></td>
		<td><%=bean.get("zipcode")%></td>
		<td><%=bean.get("primary")%></td>
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
		<td><%=bean.id%></td>
		<td><%=bean.get("type")%></td>
		<td><%=bean.get("value")%></td>
		<td><%=bean.get("primary")%></td>
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
		<td><%=bean.id%></td>
		<td><%=bean.get("type")%></td>
		<td><a href="mailto:<%=bean.get("value")%>"><%=bean.get("value")%></a></td>
		<td><%=bean.get("primary")%></td>
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
		<td><%=bean.id%></td>
		<td><%=bean.get("type")%></td>
		<td><%=bean.get("value")%></td>
		<td><%=bean.get("primary")%></td>
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
		<td><%=bean.id%></td>
		<td><%=bean.get("type")%></td>
		<td><a href="<%=bean.get("value")%>"><%=bean.get("value")%></a></td>
		<td><%=bean.get("primary")%></td>
	</tr>
	<%
		}
	%>
</table>
<%
	} catch (NotFoundException ex) {
%>
<p>The requested non-user contact with ID = <%=id%> does not exist.</p>
<%
	} catch (GenericException ex) {
%>
<p>Sorry, an error occurred:</p>
<p><%=ex.getMessage()%></p>
<%
	}
	} catch (Exception e) {
%>
<form method="get"><span>ID:</span> <input type="text" name="id" />
<input type="submit" value="Submit" /></form>
<%
	}
%>
</div>
</body>
</html>