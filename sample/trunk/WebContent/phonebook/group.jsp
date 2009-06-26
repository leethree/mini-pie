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
<title>Phonebook Group - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Phonebook Group</h2>
	  	<hr/>
<%
	String method = request.getParameter("method");
	try {
		long id = Long.decode(request.getParameter("id"));
%>
	  	<span>&gt; <a href="index.jsp">Phonebook</a> &gt; <a href="groups.jsp">Groups</a> &gt; <%=id %></span>
	  	<hr/>
	  	<a href="../group.jsp?id=<%=id%>">Switch to browse mode</a>
		<h4>Quit This Group:</h4>
<%
		if (method != null && method.equals("delete")) {
			try{
				ses.quitGroup(id);
%>
				<p>You've quit successfully</p>
<%
				return;
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
			<input type="submit" value="Quit" />
		</form>
		<h4>Edit Group Information (Admin Operation):</h4>
		<%
			if (method != null && method.equals("edit")) {
				String field = request.getParameter("field");
				String value = request.getParameter("value");
				if (field == null || field.isEmpty() || value == null || value.isEmpty()) {
		%>
					<p>Field or value should not be empty</p>
		<%
				} else {
					try{
						ses.editGroup(id, field, value);
		%>
						<p>Group updated successfully</p>
		<%
					} catch (ForbiddenException ex) {
%>
						<p>You must be the group administrator to perform this operation.</p>
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
					<table>
				        <tr>
				          <td>Field:</td>
				          <td><select name="field" id="edit_field">
					          <option value="name">Group Name</option>
					          <option value="description">Description</option>
				          </select></td>
				          <td>(*required)</td>
				        </tr> 
				        <tr>
				          <td>New value:</td>
				          <td><input type="text" name="value" id="value"/></td>
				          <td>(*required)</td>
				        </tr> 
				        <tr>
				          <td colspan="2" align="center"><input type="submit" value="Submit"/></td>
				        </tr>
				      </table>
				</form>
		<h4>Edit Group Permission (Admin Operation):</h4>
		<%
			if (method != null && method.equals("perm")) {
				String perm = request.getParameter("perm");
				if (perm == null || perm.isEmpty()) {
		%>
					<p>Permission should not be empty</p>
		<%
				} else {
					try{
						ses.editGroup(id, "permission", perm);
		%>
						<p>Group updated successfully</p>
		<%
					} catch (ForbiddenException ex) {
%>
						<p>You must be the group administrator to perform this operation.</p>
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
		<form name="perm" id="perm" method="post"><input type="hidden"
			name="method" id="method" value="perm" /> <select name="perm">
			<option value="to_contacts">Private</option>
			<option value="to_everyone">Public</option>
		</select> <input type="submit" value="Submit" /></form>
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
		<h4>Invite user to this group (Admin Operation):</h4>
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
						ses.inviteUserToGroup(id, userid);
		%>
						<p>User <%=userid %> is invited successfully</p>
		<%
					} catch (ForbiddenException ex) {
%>
						<p>You must be the group administrator to perform this operation.</p>
<%
					} catch (NotFoundException ex) {
%>
						<p>User with ID = <%=userid %> may not exist.</p>
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
			if (method != null && (method.equals("removeuser") || method.equals("appointadmin"))) {
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
						if (method.equals("removeuser")) {
							ses.removeUserFromGroup(id, userid);
		%>
						<p>User <%=userid %> removed from group successfully</p>
		<%
						} else if (method.equals("appointadmin")) {
							ses.appointAdmin(id, userid);
		%>
						<p>User <%=userid %> is appointed as group administrator</p>
		<%
						}
					} catch (ForbiddenException ex) {
%>
						<p>You must be the group administrator to perform this operation.</p>
<%
					}  catch (NotFoundException ex) {
%>
						<p>There may not be a user with ID = <%=userid %> in this group.</p>
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
				<td><form name="removeuser" method="post">
					<input type="hidden" name="method" id="method" value="removeuser"/>
					<input type="hidden" name="userid" value="<%=bean.id %>"/>
					<input type="submit" value="Remove"/>
				</form></td>
<%
		if (bean.get("admin").equals("false")) {
%>
				<td><form name="appointadmin" method="post">
					<input type="hidden" name="method" id="method" value="appointadmin"/>
					<input type="hidden" name="userid" value="<%=bean.id %>"/>
					<input type="submit" value="Appoint as Admin"/>
				</form></td>
<%
		}
%>
			</tr>
<%
	}
%>
		</table>
		<h3>Contacts Shared by Members in This Group:</h3>
		<h4>Share your non-user contact to this group:</h4>
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
						ses.shareContactToGroup(id, contactid);
		%>
						<p>Contact <%=contactid %> shared to group successfully</p>
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
			if (method != null && method.equals("removecontact")) {
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
						ses.unshareContactFromGroup(id, contactid);
		%>
						<p>Contact <%=contactid %> unshared from group successfully</p>
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
				<td><a href="../contact.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
				<td><form name="removecontact" method="post">
					<input type="hidden" name="method" id="method" value="removecontact"/>
					<input type="hidden" name="contactid" value="<%=bean.id %>"/>
					<input type="submit" value="Remove"/>
				</form></td>
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
		e.printStackTrace();
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