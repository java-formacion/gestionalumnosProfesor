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
		<div  class="row">
			<div class="col-xs-4">Session Id</div>
			<div class="col-xs-3">Nombre</div>
			<div class="col-xs-3">Alias</div>
			<div class="col-xs-2"></div>
		</div>
		<%
		
		for(Usuario user: usuarios){
			
			%>
			<div  class="row">
				<div class="col-xs-4"><%=user.getSessionid() %></div>
				<div class="col-xs-3"><%=user.getUserName() %></div>
				<div class="col-xs-3"><%=user.getNickname() %></div>
				<div class="col-xs-2"><a class="btn btn-info" href="<%=Constantes.SERVLET_ADMINISTRACION %>?<%=Constantes.PAR_SESSIONID %>=<%=user.getSessionid()%>">Expulsar Usuario</a></div>
			</div>
			<%
		}
	}else{
		%>
		<p >No hay usuarios conectados</p>
		<%
	}
	%>
	</div>
</section>
</main>
<%@include file="../includes/footer.jsp" %>
		