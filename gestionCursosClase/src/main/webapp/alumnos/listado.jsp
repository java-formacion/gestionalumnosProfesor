<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>

<a class= "btn btn-warning" href="index.jsp"> Página principal</a>
<a class = "btn btn-success" href="<%=Constantes.SERVLET_ALUMNOS+"?"+Constantes.PAR_CODIGO+"="+Alumno.CODIGO_ALUMNO%>"> Añadir Alumno Nuevo</a>

<!-- SCIPTLET para poder poner codigo java se usa <% %>-->

<%
	List<Alumno> alumnos = (List<Alumno>) request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
	if (alumnos!=null)
	{
		String formulario = "";
		for(Alumno alumno: alumnos)
		{
			
			formulario =  "<form name='formAl' class='col-xs-2 col-md-6' id='formAl' action= '" + Constantes.SERVLET_ALUMNOS + "' method = 'post'>";
			//variable operacion
			formulario +="<input type='hidden' "+ "name='" + Constantes.PAR_OPERACION + "' value = '" + Constantes.OP_DELETE + "'/>";
			//variable del codigo
			formulario +="<input type='hidden' "+ "name='"+Constantes.PAR_CODIGO+ "' value='"+alumno.getCodigo()+"'/>";
			//el boton de borrar
			formulario +="<input class='btn btn-danger' type='submit' value='Borrar'/>";
			formulario +="</form>";
			//out.write("<p>Curso: <a href='alumnos.do?"+ Constantes.PAR_CODIGO+"=" + alumno.getCodigo() + "'> " + alumno.getNombre() + "</a></p>");
			%>
			
			<div class = "col">
					<a class="col-xs-10 col-md-6" href = '<%=Constantes.SERVLET_ALUMNOS%>?<%=Constantes.PAR_CODIGO%>=<%=alumno.getCodigo()%>'><%=alumno.getNombre()%></a> 
				
			<%=formulario %>
			</div>
			<%
		}
	}
%>

<%@ include file="../includes/footer.jsp"%>