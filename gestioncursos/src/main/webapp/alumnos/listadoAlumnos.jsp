<%@page import="com.ipartek.formacion.service.AlumnoService"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.List"%>
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
		<a href="<%= Constantes.SERVLET_ALUMNOS+"?"+Constantes.PAR_CODIGO + "=" + Alumno.CODIGO_ALUMNO %>" class="btn btn-success">
			<span class="fa fa-plus fblack" aria-hidden="true"></span>
			Alumno
		</a>
		<br/>
		
		<%		
			List<Alumno> alumnos = (List<Alumno>) request.getAttribute("listado_alumnos");
			if(alumnos!=null){
				int i=1;
				String formulario = "";
				for(Alumno alumno: alumnos){
					formulario = "<form class='col-xs-2' action='"+ Constantes.SERVLET_ALUMNOS +"' method='post'>";
					// variable opcion
					formulario += "<input type ='hidden' name='" + Constantes.PAR_OPERACION + "' value='"+ Constantes.OP_DELETE +"' />";
					
					// variable codigo curso
					formulario += "<input type ='hidden' name='" + Constantes.PAR_CODIGO + "' value='"+ alumno.getCodigo() +"' />";
					
					// boton de borrar
					formulario += "<button type='submit' class='btn btn-danger'>Borrar</button>";
					formulario += "</form>";
					
		%>
					<div class="row">
						<a class="col-xs-4 col-md-4" href='<%= Constantes.SERVLET_ALUMNOS %>
							?<%= Constantes.PAR_CODIGO %>
							=<%= alumno.getCodigo() %>'>
								<%= alumno.getApellidos() + ", " + alumno.getNombre() + " - " + alumno.getDni() %>
						</a>
						
						<%= formulario %>
					</div>
		<%	
					//out.write("<p><a href='"+ Constantes.SERVLET_ALUMNOS + "?" + Constantes.PAR_CODIGO + "=" + alumno.getCodigo() + "'>Alumno " + i + ": " + alumno.getNombre() + " " + alumno.getApellidos() + " " + alumno.getDni() +"</a>"+ formulario +"</p>");
					i++;
				}
			}
		%>

<%@ include file="/includes/footer.jsp" %>