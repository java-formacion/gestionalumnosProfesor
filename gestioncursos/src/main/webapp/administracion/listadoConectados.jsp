<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main class="row">

<%

List<Usuario> usuarios = null;
ServletContext context = session.getServletContext();
usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LIST_USUARIOS);

%>
<form method="post" action="<%=Constantes.SERVLET_LOGOUT%>">
<table border="1">
	<tr>
		<th>ID USUARIO</th>
		<th>ALIAS</th>
		<th>ID SESSION</th>
	</tr>
<% 
for(Usuario usu: usuarios){
	%>
	<tr>
		<td><%=usu.getIdUsuario() %></td>
		<td><%=usu.getAlias() %></td>
		<td><%=usu.getIdSession() %></td>
		<td><a class='btn btn-danger' href='<%=Constantes.SERVLET_ADMIN %>?<%=Constantes.PAR_SESSION_ID%>
							=<%=usu.getIdSession()  %>'><span class='glyphicon glyphicon-remove'></span></a></td>
	</tr>
<% 
}
%>
</table>
</form>
<div>
	<p id="mensaje_conectados"> Total usuarios conectados: <%=usuarios.size() %></p>
</div>

</main>
<%@ include file="../includes/footer.jsp" %>