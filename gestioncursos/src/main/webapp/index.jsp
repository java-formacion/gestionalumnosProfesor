
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/>
	
<main>
	<h3>Bienvenidos a la página de gestión de Alumnos de Ipartek</h3>
	
	<%
	Usuario usuario = new Usuario();
	
	if(session.getAttribute("usuario")==null){
	%>
	
	<div class="row">
	<div class="col-md-4 col-md-offset-7">
	<jsp:include page="includes/mensaje.jsp"/>
     <div class="panel panel-default">
     	<div class="panel-heading"> <strong class="">Login</strong>
		 </div>
		 <div class="panel-body">
		<form class="form-horizontal"  name="formu_login" method="post" action="<%=Constantes.SERVLET_LOGIN%>">
			<div class="form-group">
			<div class="col-sm-3">	
				<label for="<%=Constantes.PAR_ALIAS%>">USUARIO:</label>
				</div>
				<div class="col-sm-7">
				<input type="text" class="form-control" 
					id="<%=Constantes.PAR_ALIAS%>" 
					name="<%=Constantes.PAR_ALIAS%>" 
					value="<%=usuario.getAlias()%>"/>
				</div>
			</div>
			
			<div class="form-group">
			<div class="col-sm-3">	
				<label for="<%=Constantes.PAR_PASSWORD%>">CONTRASEÑA:</label>
			</div>
				<div class="col-sm-7">
				<input type="text" class="form-control" 
					id="<%=Constantes.PAR_PASSWORD%>" 
					name="<%=Constantes.PAR_PASSWORD%>" 
					value="<%=usuario.getPassword()%>"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-9">
					<button type="submit" class="btn btn-success">Entrar</button>
				</div>
			</div>	
		</form>
	</div>
	</div>
	</div>
	</div>
	<%}
	%>
</main>
<%@ include file="includes/footer.jsp" %> 