<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main>

<a class ="btn btn-warning" href="index.jsp"> Página principal</a>
<a class ="btn btn-success" href="<%=Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO+"="+Curso.CODIGO_CURSO%>"><span class="fa fa-plus" aria-hidden="true"></span>Añadir Curso Nuevo</a> 
<!-- SCIPTLET para poder poner codigo java se usa <% %>-->

<%
	List<Curso> cursos = (List<Curso>) request.getAttribute("listado_cursos");
	if (cursos!=null)
	{
		String formulario = "";
		for(Curso curso: cursos)
		{
			formulario =  "<form name='formCurso' class='col-xs-2 col-md-6' id='formCurso' action= '" + Constantes.SERVLET_CURSOS + "' method = 'post'>";
			//variable operacion
			formulario +="<input type='hidden' "+ "name='" + Constantes.PAR_OPERACION + "' value = '" + Constantes.OP_DELETE + "'/>";
			//variable del codigo
			formulario +="<input type='hidden' "+ "name='"+Constantes.PAR_CODIGO+ "' value='"+curso.getCodigo()+"'/>";
			//el boton de borrar
			formulario +="<input class='btn btn-danger' type='submit' value='Borrar'/>";
			formulario +="</form>";
			%>
			<div class="row">
			<%
			//out.write("<a class='col-xs-10 col-md-6' href='"+Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO+"=" + curso.getCodigo() + "'> " + curso.getNombre() + "</a>  "+formulario);
			%>
			
						<a class="col-xs-10 col-md-6" href='<%=Constantes.SERVLET_CURSOS %>?<%=Constantes.PAR_CODIGO%>=<%=curso.getCodigo()%>'><%=curso.getNombre()%></a>
						<%=formulario %>	
			</div>
			<%
		}
	}

%></main>
<%@ include file="../includes/footer.jsp"%>