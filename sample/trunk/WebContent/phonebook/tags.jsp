<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="java.util.List"%>
<%@page import="org.net9.minipie.sample.xml.TagBean"%>
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
	</div>
</body>
</html>