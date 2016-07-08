<%@page import="com.ipartek.formacion.control.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
<main>
	<% 
	List<Alumno> alumnos;
	String formulario;
	alumnos = (List<Alumno>) request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
	if(alumnos!=null){
		int i=1;
		for (Alumno alumno: alumnos)
		{
			//inicio el form del boton para borrar
			formulario ="<form action='"+Constantes.SERVLET_ALUMNOS+"' method='post'>";
			//Aqui variables para borrar 
			formulario += "<input type='hidden' name='"+Constantes.PAR_OPERACION+"' value='"+Constantes.OP_DELETE+"'/>";
			//Aqui codigo para borrar
			formulario += "<input type='hidden' name='"+Constantes.PAR_CODIGO+"' value='"+alumno.getCodigo()+"'/>";
			//Aqui el boton
			formulario += "<button type='submit' class='btn btn-danger'><span class='fa fa-trash-o' aria-hidden='true'></span> Borrar</button>";
			//El cierre del form
			formulario +="</form>";
			%>
			<div class="row">
				<a class="col-xs-10 btn btn-success" href="<%=Constantes.SERVLET_ALUMNOS+"?"+Constantes.PAR_CODIGO+"="+alumno.getCodigo() %>">"<%=alumno.getNombre() %>" </a><%=formulario %>
			</div>
	<%
		}
	}	else{
		out.write("falla listado alumno");
		
	}
	%> 
	
	<%-- Scriptlets --%>
	<a class="btn btn-success" href="<%=Constantes.SERVLET_ALUMNOS+"?"+Constantes.PAR_CODIGO+"="+Alumno.CODIGO_ALUMNO%>"><span class="fa fa-plus fblack" aria-hidden="true"></span> Alumno</a>	
	<a class="btn btn-info" href="index.jsp">Página principal</a>
</main>
<%@include file="../includes/footer.jsp" %>