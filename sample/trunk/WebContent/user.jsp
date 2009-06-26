<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="java.util.List"%>
<%@page import="org.net9.minipie.sample.xml.PersonBean"%>
<%@page import="org.net9.minipie.sample.exception.NotFoundException"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<%@page import="org.net9.minipie.sample.xml.GenericBean"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse User - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Browse User</h2>
	  	<hr/>
<%
	try {
		long id = Long.decode(request.getParameter("id"));
%>
	  	<span>&gt; <a href="user.jsp">Users</a> &gt; <%=id %></span>
	  	<hr/>
<%
		try {
			PersonBean person = ses.browseUser(id);
			if (person.get("type") != null ) {
				if (person.get("type").equals("user_contact")) {
%>
		<a href="phonebook/user.jsp?id=<%=id %>">View in your phonebook</a>
<%
				} else if (person.get("type").equals("self")) {
%>
		<a href="profile.jsp">View your profile</a>
<%
				} else if (person.get("type").equals("group_member")) {
%>
		<span>This user is a member of a group that you joined</span>
<%
				} else if (person.get("type").equals("public_user")) {
%>
					<form name="add" id="add" action="phonebook/users.jsp" method="post">
						<input type="hidden" name="method" id="method" value="add"/>
						<input type="hidden" name="userid" value="<%=id %>"/>
						<input type="submit" value="Add as contact" />
					</form>
<%
				}
			}
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
		<h3>Contacts shared by this user</h3>
<%
	List<PersonBean> list = ses.listContactsSharedBy(id);
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
				<td><a href="contact.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
			</tr>
<%
	}
%>
		</table>
		<h3>User Contacts shared by this user</h3>
<%
	List<PersonBean> list2 = ses.listUsersSharedBy(id);
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
				<td><a href="user.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
			</tr>
<%
	}
%>
		</table>
<%
		} catch (NotFoundException ex) {
%>
		<p>The requested user with ID = <%=id %> does not exist.</p>
<%
		} catch (GenericException ex) {
%>
			<p>Sorry, an error occurred:</p>
			<p><%=ex.getMessage() %></p>
<%	
		}
	} catch (Exception ex) {
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