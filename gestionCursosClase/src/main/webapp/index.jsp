<!-- Si no es necesario compilar utilizar la version del footer -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/>
	<jsp:include page="includes/error.jsp"/>
<!-- Comentarios -->
<main class="container-fluid">
<div class="row">
	<div class="col-xs-12 col-md-7"> 
		<h2>Bienvenidos a la página de gestión de alumnos de Ipartek</h2>
	</div>
	<% 
		
	if(session == null || session.getAttribute(Constantes.ATT_USUARIO) == null)	
	{
		//Crear SERVLET administracion.do que muestre la lista de personas logueadas y permita desloguearlas manualmente
		
	%>
	<div class="col-xs-12 col-md-5 container ">
        <div class="wrapper">
    		<form class="form-signin" action="<%=Constantes.SERVLET_LOGIN%>" method="post" role="form">       
      			<h2 class="form-signin-heading">Please login</h2>
      			
      			<input id="<%=Constantes.PAR_USERNAME %>" type="text" class="form-control" name="<%=Constantes.PAR_USERNAME %>" placeholder="Usuario" required="" autofocus="" />
      			
      			<input id="<%=Constantes.PAR_PASSWORD%>" type="password" class="form-control" name="<%=Constantes.PAR_PASSWORD%>" placeholder="Password" required=""/>      
      			
      			<label class="checkbox">
      			
        		<input type="checkbox" value="remember-me" id="<%=Constantes.PAR_REMEMBER %>" name="<%=Constantes.PAR_REMEMBER %>" value = "1"> Recuerdame
        		
      			</label>
      			<button id ="btn-login" class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
   			</form>
  		</div>
    </div>
    <% } %>
</div>



</main>
<%@ include file="includes/footer.jsp"%>