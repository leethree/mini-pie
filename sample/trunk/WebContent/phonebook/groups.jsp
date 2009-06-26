<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="java.util.List"%>
<%@page import="org.net9.minipie.sample.xml.GenericBean"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<%@page import="org.net9.minipie.sample.exception.NotFoundException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phonebook Groups - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Phonebook Groups</h2>
		<hr/>
		<span>&gt; <a href="index.jsp">Phonebook</a> &gt; Groups</span>
	  	<hr/>
	  	<h4>Create a Group:</h4>
<%
	String method = request.getParameter("method");
	try {
		if (method != null && method.equals("create")){
			String groupName = request.getParameter("groupname");
			if (groupName == null || groupName.isEmpty()) {
	%>
				<p>Invalid group name.</p>
	<%
			} else {
				try{
					ses.createGroup(groupName);
	%>
					<p>Group created successfully</p>
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
 		  	<form name="create" id="create" method="post">
 		  		<input type="hidden" name="method" id="method" value="create"/>
 				<span>Group Name:</span>
 				<input type="text" name="groupname" />
 				<input type="submit" value="Submit" />
 		  	</form>
 		  	<h4>Join a Group:</h4>
 <%
 		  	if (method != null && method.equals("add")){
			String groupidStr = request.getParameter("groupid");
			long groupid = 0;
			try {
				groupid = Long.decode(groupidStr);
			} catch (NumberFormatException ex) {}
			if (groupid == 0) {
	%>
				<p>Invalid group ID</p>
	<%
			} else {
				try{
					ses.joinGroup(groupid);
	%>
					<p>Request sent successfully</p>
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
 				<span>Group ID:</span>
 				<input type="text" name="groupid" />
 				<input type="submit" value="Submit" />
 		  	</form>
<%
	List<GenericBean> list = ses.listGroups();
%>
		<table>
			<tr>
				<th>ID</th>
				<th>Group Name</th>
			</tr>			
<%
	for (GenericBean bean : list) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><a href="group.jsp?id=<%=bean.id %>"><%=bean.get("name") %></a></td>
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