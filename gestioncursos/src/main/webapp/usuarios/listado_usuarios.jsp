<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<main>
	<%
	List<Usuario> usuarios = (List<Usuario>)getServletContext().getAttribute(Constantes.ATT_LIST_USUARIO);
	 %>
</main>