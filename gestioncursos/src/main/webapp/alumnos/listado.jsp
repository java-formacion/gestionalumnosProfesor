<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main>
		<a class="btn btn-warning" href="index.jsp">Atras</a> 
		<a class="btn btn-success"
				href="<%=Constantes.SERVLET_ALUMNOS+"?"+
						Constantes.PAR_CODIGO+"="+
						Alumno.CODIGO_ALUMNO%>">
				Añadir Alumno nuevo
				</a>
		
		
		<%		
			List<Alumno> alumnos = (List<Alumno>) request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
		
			if(alumnos!=null){
				int i=1;
				String formulario ="";
				for(Alumno alumno: alumnos){
					formulario = "<form class='col-xs-2 col-md-6' action='"+Constantes.SERVLET_ALUMNOS
							+"' method='post'>";
					//la variable opercion
					formulario +="<input type='hidden' "+
							"name='"+Constantes.PAR_OPERACION+
							"' value='"+Constantes.OP_DELETE+"'/>";
					//la variable del codigo del curso
					formulario +="<input type='hidden' "+
							"name='"+Constantes.PAR_CODIGO+
							"' value='"+alumno.getCodigo()+"'/>";
					//el boton de borrar
					formulario +="<button type='submit' class='btn btn-danger'>Borrar</button>";
					formulario +="</form>";
					%> 
					<div class="row">
						<a class="col-xs-10 col-md-6" href="<%=Constantes.SERVLET_ALUMNOS%>?<%=Constantes.PAR_CODIGO%>=<%=alumno.getCodigo() %> "><%=alumno.getNombre()%> <%=alumno.getApellidos() %></a>
						<%=formulario %>
					</div>
				<%
				}
			}
		%>
</main>
<%@include file="../includes/footer.jsp" %>