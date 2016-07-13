
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<main> <%
 	Map<String, Usuario> users = (Map<String, Usuario>) session
 			.getServletContext().getAttribute(
 					Constantes.ATT_LISTADO_USUARIOS);
 %>
<div class="row">
	<div class="col-xs-12 col-md-6">
		<table class="table table-condensed">
			<thead>
				<th>Session ID</th>
				<th>Alias</th>
				<th>Desconectar Usuario</th>
			</thead>
			<tbody>
				<%
					for (Usuario user : users.values()) {
				%>
				<tr>
					<td><%=user.getIdSession()%></td>
					<td><%=user.getAlias()%></td>
					
					<td  align="center">
					<%
							Usuario usuario = (Usuario) session
										.getAttribute(Constantes.ATT_USUARIO);
								if (!user.getIdSession().equals(usuario.getIdSession())) {
						%>
						<a
						href="<%=Constantes.SERVLET_ADMIN + "?"
							+ Constantes.PAR_SESSION_ID + "="
							+ user.getIdSession()%>"
						class="btn btn-danger"><span class="fa fa-times"></span></a> 
							<%
 	}else{
 		out.write("Sesion Actual");
 	}
 %>
					</td>
				
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</div>
</main>
<%@ include file="../includes/footer.jsp"%>