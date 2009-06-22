
<%@page import="org.net9.minipie.sample.Backend"%><%@page import="org.net9.minipie.sample.WebController"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	WebController controller = new WebController(request);
	//System.out.println(request.getRequestURL());
	//System.out.println(request.getRequestURI());
	//System.out.println(request.getQueryString());
	String cont = request.getRequestURI();
	if (request.getQueryString() != null)
		cont += "?" + request.getQueryString(); 
	try {
		if (!controller.isLoggedIn()) {
			response.sendRedirect(request.getContextPath() + "/login.jsp?continue=" + cont);
			return;
		}
	} catch (Exception e) {
		e.printStackTrace();
		response.sendRedirect(request.getContextPath() + "/error.jsp");
		return;
	}
	Backend ses = controller.getSession();
%>
