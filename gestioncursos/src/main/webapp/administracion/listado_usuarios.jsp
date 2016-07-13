<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<main> <%
 	ServletContext context = getServletContext();
 	List<Usuario> usuarios = (List<Usuario>) context
 			.getAttribute(Constantes.ATT_LIST_USUARIO);%>
 	<table class="table table-striped">

	<tr>
		<thead>
			<th>Usuarios conectados</th>
			<th>Alias del usuario</th>
			<th>Hora de conexion</th>
		</thead>
	</tr>
	<%
 	if (usuarios != null) {
 		String formulario = "";
 		for (Usuario us : usuarios) {
 %>
	<tr>
		<th><%=us.getUserName() %></th>
		<th><%=us.getNickname() %></th>
		<th><%=us.getfConexion() %></th>
		<th><a class="btn btn-info" href="<%=Constantes.SERVLET_ADMIN %>?<%=Constantes.PAR_IDSESSION %>=<%=us.getSessionId()%>">Expulsar Usuario</a></th>
	</tr>
	
<%
		}
	}
%> 

	
</table>
</main>