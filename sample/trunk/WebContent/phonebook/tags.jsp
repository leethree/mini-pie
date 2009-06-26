<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="java.util.List"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phonebook Tags - Mini-Pie Sample</title>
</head>
<body>
	<div id="content" >
		<h2>Phonebook Tags</h2>
	  	<hr/>
	  	<span>&gt; <a href="index.jsp">Phonebook</a> &gt; <a href="tags.jsp">Tags</a></span>
	  	<hr/>
<%
	String method = request.getParameter("method");
	try {
%>
	  	<h4>Create a Tag:</h4>
<%
		if (method != null && method.equals("add")){
			String tagname = request.getParameter("tagname");
			if (tagname == null || tagname.isEmpty()) {
	%>
				<p>Tag name should not be empty</p>
	<%
			} else {
				try{
					ses.createTag(tagname);
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
	  	<form name="add" id="add" method="post">
	  		<input type="hidden" name="method" id="method" value="add"/>
			<span>New tag name:</span>
			<input type="text" name="tagname" />
			<input type="submit" value="Submit" />
	  	</form>
<%
	List<TagBean> list = ses.listTags();
%>
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>			
<%
	for (TagBean bean : list) {
%>
			<tr>
				<td><%=bean.id %></td>
				<td><a href="tag.jsp?id=<%=bean.id %>"><%=bean.tagName %></a></td>
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