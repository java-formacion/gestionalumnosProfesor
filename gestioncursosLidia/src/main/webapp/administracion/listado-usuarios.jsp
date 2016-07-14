<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main >
<section >
	<header><h2>Listado de Usuarios Conectados</h2></header>
	<div>
	<%
	ServletContext context = getServletContext();
	List<Usuario> usuarios = (List<Usuario>)context.getAttribute(Constantes.ATT_LIST_USUARIOS);
	
	if(usuarios!=null){
		%>
	<jsp:include page="../includes/mensaje.jsp"/>
		 <table class="table table-hover">
    		<thead>
      			<tr>
        			<th class="col-xs-4">Session Id</th>
        			<th class="col-xs-3">Nombre</th>
					<th class="col-xs-3">Alias</th>
					<th class="col-xs-2"></th>
      			</tr>
    		</thead>
			
			
		
		 <%
		
		for(Usuario user: usuarios){
			
			%>
			<tbody>
				<tr>
				<td class="col-xs-4"><%=user.getSessionid() %></td>
				<td class="col-xs-3"><%=user.getUserName() %></td>
				<td class="col-xs-3"><%=user.getNickname() %></td>
				<td class="col-xs-2"><a class="btn btn-danger" href="<%=Constantes.SERVLET_ADMINISTRACION %>?<%=Constantes.PAR_SESSIONID %>=<%=user.getSessionid()%>"><span class="glyphicon glyphicon-remove-sign"></span>Expulsar Usuario</a></td>
			    </tr>
			</tbody>
	
			<%
		}
	}else{
		%>
		<p >No hay usuarios conectados</p>
		<%
	}
	%>
	</table>
</section>
</main>
<%@include file="../includes/footer.jsp" %>
		