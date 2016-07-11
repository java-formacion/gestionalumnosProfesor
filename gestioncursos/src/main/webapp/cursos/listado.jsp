<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main class=row>
	<a class="btn btn-info" href="index.jsp"><span class="glyphicon glyphicon-arrow-left">Atrás</span></a>
	<a class="btn btn-success" href="<%=Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO+"="+Curso.CODIGO_CURSO%>">
		<span class="glyphicon glyphicon-plus">Nuevo curso</span>
	</a>
	
	
	<%
	
	List<Curso> cursos = (List<Curso>)request.getAttribute("listado_cursos"); 
	
	if(cursos!=null){ //si la lista no es nula
		int i=1;
	String formulario ="";
		for(Curso curso: cursos){
			formulario = "<form class='col-xs-2 col-md-6' action='"+Constantes.SERVLET_CURSOS+"' method='post'>";
			//variable operacion
			formulario +="<input type='hidden' "+
			"name='"+Constantes.PAR_OPERACION+
			"'value='"+Constantes.OP_DELETE+"'/>";
			//variable del codigo de curso
			formulario +="<input type='hidden' "+
			"name='"+Constantes.PAR_CODIGO+
			"'value='"+curso.getCodigo()+"'/>";
			//el botón borrar
			formulario +="<button type='submit' class='btn btn-danger'>Borrar</button>";
			formulario +="</form>";
			%>
			
			<div class="row">
						<a class="col-xs-10 col-md-2" href='<%=Constantes.SERVLET_CURSOS %>
							?<%=Constantes.PAR_CODIGO%>
							=<%=curso.getCodigo()  %>'>
							<%=curso.getNombre() %>
						</a>
						<%=formulario %>						
					</div>
			
			<% 
		}
	}
	
	
	%>
</main>
<%@ include file="../includes/footer.jsp" %>