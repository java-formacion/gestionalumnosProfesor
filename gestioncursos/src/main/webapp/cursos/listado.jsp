<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp" />

<!--  BOOTSTRAP JS LIBS -->
<script src="js/bootstrap.min.js"></script>
		
<!-- JQUERY LIBRARY 1.11.3  -->
<script src="js/jquery.min.js"></script>

<main>

<!-- 
	COLORES DE BOOTSTRAP

	WARNING -> naranja
	DANGER	-> rojo
	INFO	-> azul claro
	SUCCESS	-> verde
	DEFAULT -> blanco transparente
	PRIMARY -> azul oscuro
 -->

		<a href="index.jsp" class="btn btn-warning">
			<span class="fa fa-arrow-left" aria-hidden="true"></span>
			Atras
		</a>
		
		<a href="<%= Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO + "=" + Curso.CODIGO_CURSO %>" class="btn btn-success">
			<span class="fa fa-plus fblack" aria-hidden="true"></span>
			Curso
		</a>
		<br/>
		
		<%		
			List<Curso> cursos = (List<Curso>) request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
			if(cursos!=null){
				int i=1;
				String formulario = "";
				for(Curso curso: cursos){
					formulario = "<form class='col-xs-2' action='"+ Constantes.SERVLET_CURSOS +"' method='post'>";
					// variable opcion
					formulario += "<input type ='hidden' name='" + Constantes.PAR_OPERACION + "' value='"+ Constantes.OP_DELETE +"' />";
					
					// variable codigo curso
					formulario += "<input type ='hidden' name='" + Constantes.PAR_CODIGO + "' value='"+ curso.getCodigo() +"' />";
					
					// boton de borrar
					formulario += "<button type='submit' class='btn btn-danger'><span class='fa fa-trash-o' aria-hidden='true'> </span> Borrar</button>";
					formulario += "</form>";
					
		%>
					<div class="row">
						<a class="col-xs-4 col-md-4" href='<%= Constantes.SERVLET_CURSOS %>
							?<%= Constantes.PAR_CODIGO %>
							=<%= curso.getCodigo() %>'>
								<%= curso.getNombre() %>
						</a>
						
						<%= formulario %>
					</div>		
		<%			
					// out.write("<div><a href='"+ Constantes.SERVLET_CURSOS + "?" + Constantes.PAR_CODIGO +"=" + curso.getCodigo() + "'>Curso " + i + ": " + curso.getNombre() + "</a>" + formulario + " </div>");
					i++;
				}
			}
		%>
</main>

<%@ include file="/includes/footer.jsp" %>