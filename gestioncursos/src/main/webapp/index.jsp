<%@page import="com.ipartek.formacion.pojo.Idioma"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/> 	
<main class="container-fluid">
	<div class="row">	 
	<section class="col-xs-12 col-md-7">
		<header> <h2>Bienvenido</h2></header>
		<p>
			Bienvenidos a la pagina de Gestion de Alumnos de <span>Ipartek</span>.

		</p>
		<p>Esta apliación ha sido en conjunto de....</p>
		
	</section>
	<aside class="col-xs-12 col-md-5 panel">
		<jsp:include page="includes/mensaje.jsp" />
		<div class="panel panel-success">
   
   		<div class="panel-heading">
       		<div class="panel-title"><h2>Login</h2></div>
       
   			</div>     

   			<div class="panel-body">
       			<form class="form-horizontal" action="<%=Constantes.SERVLET_LOGIN %>" method="post" role="form">
           			<div class="input-group">
           				<label class="sr-only" for="<%=Constantes.PAR_USERNAME %>">Usuario</label><span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input id="<%=Constantes.PAR_USERNAME %>" name="<%=Constantes.PAR_USERNAME %>" type="text" class="form-control" value="" placeholder="Introduzca su usuario">                                        
           			
           			</div>
		           <div class="input-group">
		           		<label class="sr-only" for="<%=Constantes.PAR_PASSWORD%>">Contraseña:</label>
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input name="<%=Constantes.PAR_PASSWORD%>" id="<%=Constantes.PAR_PASSWORD%>" type="password" class="form-control" placeholder="Introduzca su contraseña">
		            </div>
		            <div class="input-group">
						<label>Idioma: </label>
						<select name="<%=Constantes.PAR_DURACION%>">
							<c:set var="idiomas" value="<%=request.getAttribute(Constantes.ATT_LISTA_DURACION_MODULO)%>"/>
							<c:if test="${idiomas!=null}">
								<c:forEach var="i" begin="0" end="${idiomas.length}">
									<option value="${i.getCodigo()}">
										i.getNombre()
									</option>
								</c:forEach>
							</c:if>	
						</div>
		           <div class="input-group">
						<div class="checkbox">
			            	<label>  </label>
								<input id="<%=Constantes.PAR_REMEMBER %>" name="<%=Constantes.PAR_REMEMBER %>" type="checkbox" value="1"> 
								<label for="<%=Constantes.PAR_REMEMBER %>" >Recuerdame </label>
			            </div>
                   </div>
               <div class="form-group">
                   <div class="col-xs-12 controls">
                     <button type="submit" id="btn-login" class="btn btn-success">Aceptar  </button>
                   </div>
               </div>   
           </form>     
       </div><!--panel-body-->                     
   </div>
	</aside> 
	</div>
</main>
<%@ include file="includes/footer.jsp" %>






