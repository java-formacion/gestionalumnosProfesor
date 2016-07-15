<!-- Si no es necesario compilar utilizar la version del footer -->
<%@page import="com.ipartek.formacion.pojo.Idioma"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.ipartek.formacion.service.i18n.i18nmessages"/>
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
      			<h2 class="form-signin-heading"><fmt:message key="login.pleaselogin"/></h2>
      			
      			<input id="<%=Constantes.PAR_USERNAME %>" value="${cookie.usuario.value}" type="text" class="form-control" name="<%=Constantes.PAR_USERNAME %>" placeholder="<fmt:message key="usuario"/>" required="" autofocus="" />
      			
      			<input id="<%=Constantes.PAR_PASSWORD%>" value="${cookie.password.value}" type="password" class="form-control" name="<%=Constantes.PAR_PASSWORD%>" placeholder="<fmt:message key="password"/>" required=""/>      
      			
      			<label class="checkbox">
      			
        		<input type="checkbox" value="remember-me" id="<%=Constantes.PAR_REMEMBER %>" name="<%=Constantes.PAR_REMEMBER %>" value = "1"> Recuerdame </label>
        		
        		<div>
				    <select name="<%=Constantes.PAR_IDIOMA%>">
				   			<c:forEach items="<%=Idioma.values() %>" var="idioma">
   								<option value="${idioma.codigo}">${idioma.nombre}</option>
							</c:forEach>
					</select>
      			</div>
        	<div>
      			<button id ="btn-login" class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.login"/></button>   
   			
   			</div></form>
  		</div>
    </div>
    <% } %>
</div>



</main>
<%@ include file="includes/footer.jsp"%>