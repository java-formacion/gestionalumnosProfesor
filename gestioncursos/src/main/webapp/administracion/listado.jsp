<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:include page="../includes/header.jsp"/> 
    <jsp:include page="../includes/mensaje.jsp"/> 
    <%ServletContext context = session.getServletContext();
	List<Usuario> usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LISTA_USUARIOS);
	%>
	<table class="table table-striped">
		<thead>
			<th>Usuario Id</th>
			<th>Nickname</th>
			<th>Usuario Id</th>
		</thead>
	<%
		for(Usuario usuario:usuarios){%>
		<tr>
			<td><%=usuario.getSessionId()%></td>
			<td><%=usuario.getNickname()%></td>
			<td><%=usuario.getUserName()%></td>
			<td><a class="btn btn-danger" href="<%=Constantes.SERVLET_ADMINISTRACION%>?<%=Constantes.PAR_SESSIONID%>=<%=usuario.getSessionId()%>">Expulsar</a></td>
		</tr>
		<%
		}
	
	%>
	</table>