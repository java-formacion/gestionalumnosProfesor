<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main>

<!-- 
warning 	-> naranja
danger 		-> rojo
info 		-> azul claro
success 	-> verde
default		-> blanco
primary 	-> azul oscuro
 -->
		<a class="btn btn-warning" href="index.jsp">Página principal</a>
		
		<a class="btn btn-success" href="<%=Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO+"="+Curso.CODIGO_CURSO %>">Añadir curso nuevo</a>
		
		<%
		List<Curso> cursos = (List<Curso>) request.getAttribute("listado_cursos");

		if (cursos != null) {
			int i = 1;
			String formulario="";
			for (Curso curso : cursos) {
				formulario="<form action='"+Constantes.SERVLET_CURSOS+"'method='post'>";
				//la variable operacion
				formulario +="<input type = 'hidden' name='"+Constantes.PAR_OPERACION+"'value='"+Constantes.OP_DELETE+"'/>";
				
				//la variable del codigo del curso
				
				formulario +="<input type = 'hidden' name='"+Constantes.PAR_CODIGO+"'value='"+curso.getCodigo()+"'/>";
				formulario += "<button type='submit' class='btn btn-danger'>Borrar</button>";
				formulario +="</form>";
				
				out.write("<div><a href='cursos.do?"+Constantes.PAR_CODIGO+"=" + curso.getCodigo() + "'>Curso " + i + ": "
						+ curso.getNombre() + "</a>"+formulario+"</div>");
				i++;
			}
		}
	%>
	</main>
<%@ include file="../includes/footer.jsp" %>