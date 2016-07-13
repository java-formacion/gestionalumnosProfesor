<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:include page="../includes/header.jsp"/> 
    <%ServletContext context = session.getServletContext();
	List<Usuario> usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LISTA_USUARIOS);
		for(Usuario usuario:usuarios){%>
			
			<%=usuario.getSessionId()%>
			<%=usuario.getNickname()%>
			<%=usuario.getUserName()%>
		<%}
	
	%>