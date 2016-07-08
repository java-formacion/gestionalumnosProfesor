<%@page import="com.ipartek.formacion.control.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>
	<% 
	List<Curso> cursos;
	String formulario;
	cursos = (List<Curso>) request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
	if(cursos!=null){
		int i=1;
		for (Curso curso: cursos)
		{
			//inicio el form del boton para borrar
			formulario ="<form action='"+Constantes.SERVLET_CURSOS+"' method='post'>";
			//Aqui variables para borrar 
			formulario += "<input type='hidden' name='"+Constantes.PAR_OPERACION+"' value='"+Constantes.OP_DELETE+"'/>";
			//Aqui codigo para borrar
			formulario += "<input type='hidden' name='"+Constantes.PAR_CODIGO+"' value='"+curso.getCodigo()+"'/>";
			//Aqui el boton
			formulario += "<button type='submit' class='btn btn-danger'><span class='fa fa-trash-o' aria-hidden='true'></span> Borrar</button>";
			//El cierre del form
			formulario +="</form>";
			%>
			<div class="row">
				<a class="col-xs-10 btn btn-success" href="<%=Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO+"="+curso.getCodigo() %>">"<%=curso.getNombre() %>" </a><%=formulario %>
			</div>
		<%}
	}%>
<!-- 
Warning	-> naranja
Info	-> azul claro
danger	-> rojo
success	-> verde
default	-> blanco
primary	-> azul mas oscuro
 -->	
	
	
	<!-- Scriptlets -->
	<a class="btn btn-success" href="<%=Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO+"="+Curso.CODIGO_CURSO%>"><span class="fa fa-plus fblack" aria-hidden="true"></span> Curso</a>
	<a class="btn btn-info" href="index.jsp">Página principal</a>
	
</main>		
<%@ include file="../includes/footer.jsp" %><!-- Aqui no se compila previamente, no tiene codigo java -->