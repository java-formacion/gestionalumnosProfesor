<%@page import="com.ipartek.formacion.pojo.Idioma"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %><!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/> 	
<main class="container-fluid">
	<div class="row">	 
	<section class="col-xs-12 col-md-7">
		<header> <h2>Bienvenido</h2></header>
		<p>
			Bienvenidos a la página de Gestión de Alumnos de <span>Ipartek</span>.

		</p>
		
		
	</section>
	<aside class="col-xs-12 col-md-5 panel">
		<jsp:include page="includes/mensaje.jsp" />
		<div class="panel panel-primary">
   
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
						<div class="checkbox">
			            	<label>  </label>
								<input type="checkbox" value="1"> Recuérdame
			            </div>
                   </div>
                   <div class="input-group">
  						<i class="fa fa-globe" aria-hidden="true"></i><label>Selecciona idioma</label>
 						<select class="form-control" id="sel1">
    						<option><%=Idioma.CASTELLANO %></option>
    						<option><%=Idioma.EUSKERA %></option>
    						<option><%=Idioma.INGLES %></option>
  						</select>
</div>


               <div class="form-group">
                   <div class="col-xs-12 controls">
                     <button type="submit" id="btn-login" class="btn btn-primary">Aceptar  </button>
                    

                   </div>
               </div>   
           </form>     
       </div><!--panel-body-->                     
   </div>
	</aside> 
	</div>
</main>
<%@ include file="includes/footer.jsp" %>








