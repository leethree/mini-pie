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
	String method = request.getParameter("method");
	try {
		long id = Long.decode(request.getParameter("id"));
%>
		<h4>Delete This Tag:</h4>
<%
		if (method != null && method.equals("delete")) {
			try{
				ses.deleteTag(id);
%>
				<p>Tag deleted successfully</p>
<%
				return;
			} catch (GenericException e) {
				e.printStackTrace();
%>
				<p>An error occurred while deleting the tag</p>
<%
			}
		}
%>
		<form name="delete" id="delete" method="post">
			<input type="hidden" name="method" id="method" value="delete"/>
			<input type="submit" value="Delete" />
		</form>
		<h4>Edit This Tag:</h4>
		<%
			if (method != null && method.equals("edit")) {
				String tagname = request.getParameter("tagname");
				if (tagname == null || tagname.isEmpty()) {
		%>
					<p>Tag name should not be empty</p>
		<%
				} else {
					try{
						ses.editTag(id, tagname);
		%>
						<p>Tag updated successfully</p>
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
				<form name="edit" id="edit" method="post">
					<input type="hidden" name="method" id="method" value="edit"/>
					<span>New tag name:</span>
					<input type="text" name="tagname" />
					<input type="submit" value="Submit" />
				</form>
<%
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
		<h4>Add this tag to a user contact:</h4>
		<%
			if (method != null && method.equals("adduser")) {
				String useridStr = request.getParameter("userid");
				long userid = 0;
				try {
					userid = Long.decode(useridStr);
				} catch (NumberFormatException ex) {}
				if (userid == 0) {
		%>
					<p>Invalid user ID</p>
		<%
				} else {
					try{
						ses.addTagToUser(id, userid);
		%>
						<p>Tag added to user <%=userid %> successfully</p>
		<%
					} catch (NotFoundException ex) {
%>
						<p>You may not have a user contact with ID = <%=userid %>.</p>
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
		<form name="adduser" id="adduser" method="post">
			<input type="hidden" name="method" id="method" value="adduser"/>
			<span>User ID:</span>
			<input type="text" name="userid" />
			<input type="submit" value="Submit" />
		</form>
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
				<td><img alt="<%=bean.get("image") %>" src="<%=bean.get("image") %>"/></td>
				<td><a href="user.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
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
		<h3>Non-user Contacts with This Tag:</h3>
		<h4>Add this tag to a non-user contact:</h4>
		<%
			if (method != null && method.equals("addcontact")) {
				String contactidStr = request.getParameter("contactid");
				long contactid = 0;
				try {
					contactid = Long.decode(contactidStr);
				} catch (NumberFormatException ex) {}
				if (contactid == 0) {
		%>
					<p>Invalid contact ID</p>
		<%
				} else {
					try{
						ses.addTagToContact(id, contactid);
		%>
						<p>Tag added to contact <%=contactid %> successfully</p>
		<%
					} catch (NotFoundException ex) {
%>
						<p>You may not have a contact with ID = <%=contactid %>.</p>
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
		<form name="addcontact" id="addcontact" method="post">
			<input type="hidden" name="method" id="method" value="addcontact"/>
			<span>Contact ID:</span>
			<input type="text" name="contactid" />
			<input type="submit" value="Submit" />
		</form>
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
				<td><img alt="<%=bean.get("image") %>" src="<%=bean.get("image") %>"/></td>
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