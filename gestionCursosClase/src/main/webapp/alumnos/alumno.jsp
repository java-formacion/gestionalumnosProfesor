<%@page import="com.ipartek.formacion.controller.exception.AlumnoError"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<title>Insert title here</title>
<%
		Alumno alumno = (Alumno) request.getAttribute(Constantes.ATT_ALUMNO);
		
		int op = -1;
		if(alumno!=null)
		{
			op = Constantes.OP_UPDATE;		
%>
		<title>Curso <% out.write(alumno.getNombre()); %></title>
<%
		}
		else
		{
%>
		<title>Alumno  - Alumno nuevo</title>
<%		op = Constantes.OP_CREATE;
		alumno = new Alumno();
		
		}
%>
	<div>
	<a class = "btn btn-warning"href="<%=Constantes.SERVLET_ALUMNOS %>">Atras</a>

		<%
			if(alumno instanceof AlumnoError)
			{
				AlumnoError aux = (AlumnoError) alumno;
				out.write(aux.getMensaje());
			}
		%>
		<%
		if(alumno!=null){
		%>
		<%=Constantes.SERVLET_ALUMNOS%>
			<form class="horizontal" name="formalumno" id="formalumno" method='post' 
				action="<%=Constantes.SERVLET_ALUMNOS%>">
				<input type="hidden" 
					id="<%=Constantes.PAR_OPERACION %>"
					name="<%=Constantes.PAR_OPERACION %>"  
					value="<%=op %>"/>
				<input type="hidden" 
					id="<%=Constantes.PAR_CODIGO %>" 
					name="<%=Constantes.PAR_CODIGO %>" 
					value="<%=alumno.getCodigo()%>"/>
				<div class="form-group">
					<label class = "col-xs-2" for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
					<input class="form-control" type="text" 
						name="<%=Constantes.PAR_NOMBRE%>" 
						id="<%=Constantes.PAR_NOMBRE%>" 
						value="<%=alumno.getNombre() %>"/>
				</div>
				<div class="form-group">
					<label class = "col-xs-2" for="<%=Constantes.PAR_APELLIDOS%>">Apellidos:</label>
					<input class="form-control" type="text" 
						name="<%=Constantes.PAR_APELLIDOS%>" 
						id="<%=Constantes.PAR_APELLIDOS%>" 
						value="<%=alumno.getApellidos() %>"/>
				</div>
				<div class="form-group">
					<label class = "col-xs-2" for="<%=Constantes.PAR_DNI%>">Dni:</label>
					<input class="form-control" type="text" 
						name="<%=Constantes.PAR_DNI%>" 
						id="<%=Constantes.PAR_DNI%>" 
						value="<%=alumno.getDni() %>"/>
				</div>
				<div class="form-group">
				<label for="<%=Constantes.PAR_FECHA%>">Fecha:</label>
					<%
						
						
					%>
					<input class="form-control" placeholder = "Dia" value = "2" min= "1" max = "12" step = "1" type="number" name="<%=Constantes.PAR_DIA%>" id="<%=Constantes.PAR_DIA%>"/>
					<input class="form-control" placeholder = "Mes" min= "1" max = "12" step = "1" type="number" name="<%=Constantes.PAR_MES%>" id="<%=Constantes.PAR_MES%>"/>
					<input class="form-control" placeholder = "Año" type="number" name="<%=Constantes.PAR_YEAR%>" id="<%=Constantes.PAR_YEAR%>"/>
				</div>
				<div class="form-group">
				<button class = "btn btn-success" type="submit"> Guardar </button>
				</div>
			</form>			
	<%	}
		%>
		</div>
<%@ include file="/includes/footer.jsp"%>