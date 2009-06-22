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
<title>Notifications - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Notifications</h2>
	  	<hr/>
<%
	String confirm = request.getParameter("confirm");
	String idStr = request.getParameter("id");
	if (idStr != null && confirm != null) {
		long id = 0;
		try {
			id = Long.decode(idStr);
		} catch (NumberFormatException ex) {}
		
		if (id == 0) {
%>
		<p>Invalid notification ID</p>
<%
		} else if (confirm == null || (!confirm.equals("no") && !confirm.equals("yes"))) {
%>
		<p>Invalid confirmation type</p>
<%
		} else {
			boolean conf = false;
			if (confirm.equals("yes"))
				conf = true;
			
			try{
				ses.confirmNotification(id, conf);
%>
			<p>Notification confirmed/rejected</p>
<%
			} catch (NotFoundException ex) {
%>
			<p>You may not have a notification with ID = <%=id %>.</p>
<%
			} catch (GenericException e) {
				e.printStackTrace();
%>
			<p>An error occurred. Please check your input information</p>
<%
			}
		}
	}
	try {
	List<GenericBean> list = ses.listNotifications();
%>
		<table>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Sender</th>
				<th>Message</th>
			</tr>			
<%
	for (GenericBean bean : list) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><%=bean.get("type") %></td>
				<td><a href="user.jsp?id=<%=bean.get("sender") %>"><%=bean.get("sender") %></a></td>
				<td><%=bean.get("content") %></td>
				<td><form name="confirm" method="post">
					<input type="hidden" name="id" value="<%=bean.id %>"/>
					<input type="hidden" name="confirm" value="yes"/>
					<input type="submit" value="Confirm"/>
				</form></td>
				<td><form name="reject" method="post">
					<input type="hidden" name="id" value="<%=bean.id %>"/>
					<input type="hidden" name="confirm" value="no"/>
					<input type="submit" value="Reject"/>
				</form></td>
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