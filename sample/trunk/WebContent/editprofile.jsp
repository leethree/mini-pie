<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/session.jsp"%>
<%@page import="org.net9.minipie.sample.xml.UpdateBean"%>
<%@page import="org.net9.minipie.sample.exception.NotFoundException"%>
<%@page import="org.net9.minipie.sample.exception.GenericException"%>
<%@page import="org.net9.minipie.sample.xml.GenericBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Profile - Mini-Pie Sample</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
  	<div id="content" >
	  	<h2>Edit Profile</h2>
	  	<hr/>
	  	<span>&gt; <a href="profile.jsp">Profile</a> &gt; edit</span>
	  	<hr/>
	  	<a href="profile.jsp">View profile</a>
<%
	String method = request.getParameter("method");
%>
	  	<h3>Edit information</h3>
<%
	if (method != null && method.equals("edit")) {
		String type = request.getParameter("type");
		String idStr = request.getParameter("id");
		String field = request.getParameter("field");
		String value = request.getParameter("value");
		if (value==null || value.isEmpty())
			value = request.getParameter("perm");
		if (field!=null && field.equals("addascontactpermission"))
			value = request.getParameter("addperm");
		long id = 0;
		if (idStr != null && !idStr.isEmpty())
			try {
				id = Long.decode(idStr);
			} catch (NumberFormatException e) {
%>
		<p>ID should be a valid integer</p>
<%
			}
		if (type == null || type.isEmpty() || 
				field == null || field.isEmpty() || 
				value == null || value.isEmpty()) {
%>
			<p>Type, field or value should not be empty</p>
<%
		} else {
			UpdateBean update = new UpdateBean(type, field, id, value);
			try {
				ses.updateProfile(update);
%>
				<p>Information updated successfully</p>
<%
			} catch (NotFoundException e) {
%>
				<p>The target information does not exist</p>
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
	          <td>Type:</td>
	          <td><select name="type" id="edit_type" onchange="minipie.onEditTypeChange(1)">
		          <option value="basic">Basic Information</option>
		          <option value="address">Addresses</option>
		          <option value="phone">Phone Numbers</option>
		          <option value="email">Emails</option>
		          <option value="im">IMs</option>
		          <option value="url">URLs</option>
	          </select></td>
	        </tr>
	        <tr id="edit_id" style="display: none;">
	          <td>ID:</td>
	          <td><input type="text" name="id" id="id"/></td> 
	          <td>(required for detailed information)</td>
	        </tr> 
	        <tr>
	          <td>Field:</td>
	          <td><select name="field" id="edit_field" onchange="minipie.onEditFieldChange()">
		          <option value="displayName">Name</option>
		          <option value="nickname">Nickname</option>
		          <option value="birthday">Birthday</option>
		          <option value="gender">Gender</option>
		          <option value="note">Notes</option>
		          <option value="birthyearPermission">Permission for birth year</option>
		          <option value="birthdayPermission">Permission for birth date</option>
		          <option value="genderPermission">Permission for gender</option>
		          <option value="addascontactpermission">Permission for adding-as-contact</option>
	          </select></td>
	        </tr> 
	        <tr>
	          <td>New value:</td>
	          <td><input type="text" name="value" id="edit_value"/>
	          <select name="perm" id="edit_value_perm" style="display: none;">
		          <option value="to_self">Visible to myself</option>
		          <option value="to_contacts">Visible to contacts</option>
		          <option value="to_everyone">Visible to everyone</option>
	          </select><select name="addperm" id="edit_value_addperm" style="display: none;">
		          <option value="confirmed_ones">Confirmation needed</option>
		          <option value="everyone">Without confirmation</option>
		          <option value="no_one">Forbidden</option>
	          </select></td>
	          <td>(*required)</td>
	        </tr> 
	        <tr>
	          <td colspan="2" align="center"><input type="submit" value="Submit"/></td>
	        </tr>
	      </table>
	  	</form>
	  	<h3>Add information</h3>
<%
	if (method != null && method.equals("add")) {
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		String valueType = request.getParameter("valuetype");
		String permission = request.getParameter("permission");
		String zipcode = request.getParameter("zipcode");
		String primary = request.getParameter("primary");
		if (type == null || type.isEmpty() || value == null || value.isEmpty()) {
%>
			<p>Information value should not be empty</p>
<%
		} else {
			GenericBean bean = new GenericBean(type);
			bean.set("value", value);
			bean.set("type", valueType);
			bean.set("permission", permission);
			if (type != null && type.equals("address"))
				bean.set("zipcode", zipcode);
			if (primary != null && primary.equals("on"))
				bean.set("primary", "true");
			UpdateBean update = new UpdateBean(bean);
			try {
				ses.updateProfile(update);
%>
				<p>Information updated successfully</p>
<%
			} catch (GenericException e) {
%>
				<p>An error occurred. Please check your input information</p>
<%
			}
		}
	}

%>
	  	<form name="add" id="add" method="post">
	  		<input type="hidden" name="method" id="method" value="add"/>
	  		<table>
	        <tr>
	          <td>Type:</td>
	          <td><select name="type" id="add_type" onchange="minipie.onAddTypeChange()">
		          <option value="address">Addresses</option>
		          <option value="phone">Phone Numbers</option>
		          <option value="email">Emails</option>
		          <option value="im">IMs</option>
		          <option value="url">URLs</option>
	          </select></td>
	        </tr>
	        <tr>
	          <td>Value:</td>
	          <td><input type="text" name="value" id="value"/></td> 
	          <td>(*required)</td>
	        </tr> 
	        <tr>
	          <td>Value type:</td>
	          <td><input type="text" name="valuetype" id="valuetype"/></td> 
	        </tr> 
	        <tr id="add_zipcode">
	          <td>ZIP code:</td>
	          <td><input type="text" name="zipcode" id="zipcode"/></td>
	          <td>(for addresses only)</td> 
	        </tr> 
	        <tr>
	          <td></td>
	          <td><input type="checkbox" name="primary" id="primary"/>Primary</td>
	        </tr> 
	        <tr>
	          <td>Permission:</td>
	          <td><select name="permission">
		          <option value="to_self">Visible to myself</option>
		          <option value="to_contacts">Visible to contacts</option>
		          <option value="to_everyone">Visible to everyone</option>
	          </select></td> 
	        </tr> 
	        
	        <tr>
	          <td colspan="2" align="center"><input type="submit" value="Submit"/></td>
	        </tr>
	      </table>
	  	</form>
	  	<h3>Delete information</h3>
<%
	if (method != null && method.equals("delete")) {
		String type = request.getParameter("type");
		String idStr = request.getParameter("id");
		long id = 0;
		if (idStr != null && !idStr.isEmpty())
			try {
				id = Long.decode(idStr);
			} catch (NumberFormatException e) {
%>
		<p>ID should be a valid integer</p>
<%
			}
		if (type == null || type.isEmpty() || id == 0) {
%>
			<p>Type or ID should not be empty</p>
<%
		} else {
			UpdateBean update = new UpdateBean(type, id);
			try {
				ses.updateProfile(update);
%>
				<p>Information updated successfully</p>
<%
			} catch (NotFoundException e) {
%>
				<p>The target information does not exist</p>
<%
			} catch (GenericException e) {
%>
				<p>An error occurred. Please check your input information</p>
<%
			}
		}
	}

%>
	  	<form name="delete" id="delete" method="post">
	  		<input type="hidden" name="method" id="method" value="delete"/>
	  		<table>
	        <tr>
	          <td>Type:</td>
	          <td><select name="type">
		          <option value="address">Addresses</option>
		          <option value="phone">Phone Numbers</option>
		          <option value="email">Emails</option>
		          <option value="im">IMs</option>
		          <option value="url">URLs</option>
	          </select></td>
	        </tr>
	        <tr>
	          <td>ID:</td>
	          <td><input type="text" name="id" id="id"/></td> 
	          <td>(*required)</td>
	        </tr> 
	        <tr>
	          <td colspan="2" align="center"><input type="submit" value="Submit"/></td>
	        </tr>
	      </table>
	  	</form>
	  	<h3>Upload new image</h3>
<%
	//String imageUploadUrl = ses.getServiceBaseUrl() + "profile/image";
%>
		<p>Sample not supported yet</p>
	</div>
</body>
</html>