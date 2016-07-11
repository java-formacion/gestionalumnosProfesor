<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main class=row>
	<a class="btn btn-info" href="index.jsp"><span class="glyphicon glyphicon-arrow-left">Atrás</span></a>
	<a class="btn btn-success" href="<%=Constantes.SERVLET_ALUMNOS+"?"+Constantes.PAR_CODIGO+"="+Alumno.CODIGO_ALUMNO%>">
	<span class="glyphicon glyphicon-plus">Nuevo Alumno</span>
	</a>
	
	<%
	
	List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS); 
	
	if(alumnos!=null){ //si la lista no es nula
		String formulario ="";
		for(Alumno alumno: alumnos){
			formulario = "<form action='"+Constantes.SERVLET_ALUMNOS+"' method='post'>";
			//variable operacion
			formulario +="<input type='hidden' "+
			"name='"+Constantes.PAR_OPERACION+
			"'value='"+Constantes.OP_DELETE+"'/>";
			//variable del codigo de curso
			formulario +="<input type='hidden' "+
			"name='"+Constantes.PAR_CODIGO+
			"'value='"+alumno.getCodigo()+"'/>";
			
			//el botón borrar
			//el botón borrar
			formulario +="<button type='submit' class='btn btn-danger'>Borrar</button>";
			formulario +="</form>";
			
			/*out.write("<p><a href='alumnos.do?"+Constantes.PAR_CODIGO+"=" 
			+ alumno.getCodigo() + "'>"  
		    + alumno.getNombre() + " " 
			+ alumno.getApellidos() + " " 
		    +alumno.getDni() 
		    +"</a>"
		    +formulario+"</p>");*/
			
			%>
			
			<div class="row">
						<a class="col-xs-10 col-md-2" href='<%=Constantes.SERVLET_ALUMNOS %>
							?<%=Constantes.PAR_CODIGO%>
							=<%=alumno.getCodigo()  %>'>
							<%=alumno.getNombre() 
							+ " " + alumno.getApellidos() 
							+ " " +alumno.getDni() 
							%>
						</a>
						<%=formulario %>						
					</div>
			
			<% 
			
		}
		
	}
	
	
	%>

</main>
<%@ include file="../includes/footer.jsp" %>