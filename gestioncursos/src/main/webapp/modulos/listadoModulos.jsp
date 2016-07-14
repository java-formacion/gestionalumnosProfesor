<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp" />

<!--  BOOTSTRAP JS LIBS -->
<script src="js/bootstrap.min.js"></script>
		
<!-- JQUERY LIBRARY 1.11.3  -->
<script src="js/jquery.min.js"></script>

<main>
		<a href="index.jsp" class="btn btn-warning">
			<span class="fa fa-arrow-left" aria-hidden="true"></span>
			Atras
		</a>
		
		<a href="<%= Constantes.SERVLET_MODULOS+"?"+Constantes.PAR_CODIGO + "=" + Modulo.CODIGO_MODULO %>" class="btn btn-success">
			<span class="fa fa-plus fblack" aria-hidden="true"></span>
			Modulo
		</a>
		<br/>
		
		<%		
			List<Modulo> modulos = (List<Modulo>) request.getAttribute("listado_modulos");
			if(modulos!=null){
				String formulario = "";
				for(Modulo modulo: modulos){
					formulario = "<form class='col-xs-2' action='"+ Constantes.SERVLET_MODULOS +"' method='post'>";
					// variable opcion
					formulario += "<input type ='hidden' name='" + Constantes.PAR_OPERACION + "' value='"+ Constantes.OP_DELETE +"' />";
					
					// variable codigo curso
					formulario += "<input type ='hidden' name='" + Constantes.PAR_CODIGO + "' value='"+ modulo.getCodigo() +"' />";
					
					// boton de borrar
					formulario += "<button type='submit' class='btn btn-danger'><span class='fa fa-trash-o' aria-hidden='true'> </span> Borrar</button>";
					formulario += "</form>";
					
		%>
					<div class="row">
						<a class="col-xs-4 col-md-4" href='<%= Constantes.SERVLET_MODULOS %>
							?<%= Constantes.PAR_CODIGO %>
							=<%= modulo.getCodigo() %>'>
								<%= modulo.getNombre() %>
						</a>
						
						<%= formulario %>
					</div>
		<%
					//out.write("<p><a href='"+ Constantes.SERVLET_MODULOS + "?" + Constantes.PAR_CODIGO + "=" + modulo.getCodigo() + "'>Modulo " + i + ": " + modulo.getNombre() +"</a>"+ formulario +"</p>");
				}
			}
		%>

<%@ include file="/includes/footer.jsp" %>