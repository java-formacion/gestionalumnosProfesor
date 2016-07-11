<%@page import="com.ipartek.formacion.pojo.Genero"%>
<%@page import="java.util.GregorianCalendar"%>
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
		String tGuardar = "";
		
		if(alumno!=null)
		{
			tGuardar = "guardar";
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
		tGuardar = "crear nuevo";
		alumno = new Alumno();
		
		}
%>
	<div>
	<a class = "btn btn-warning" href="<%=Constantes.SERVLET_ALUMNOS %>">Atras</a>

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
			<form class="form-horizontal" name="formalumno" id="formalumno" method='post' 
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
					<input class="form-control" type="text" pattern="((([A-Z]|[a-z])\d{8})|(\d{8}([A-Z]|[a-z])))"
						name="<%=Constantes.PAR_DNI%>" 
						id="<%=Constantes.PAR_DNI%>" 
						value="<%=alumno.getDni() %>"/>
				</div>
				<div class="form-group">
				<label for="<%=Constantes.PAR_FECHA%>">Fecha:</label>
					<%
						GregorianCalendar calendar = new GregorianCalendar();
						calendar.setTime(alumno.getfNacimiento());
					%>
					
					<input class="form-control" placeholder = "Dia" value = "<%=calendar.get(GregorianCalendar.DAY_OF_MONTH)%>" min= "1" max = "31" step = "1" type="number" name="<%=Constantes.PAR_DIA%>" id="<%=Constantes.PAR_DIA%>"/>
					<input class="form-control" placeholder = "Mes" value ="<%=calendar.get(GregorianCalendar.MONTH)+1 %>" min= "1" max = "12" step = "1" name="<%=Constantes.PAR_MES%>" id="<%=Constantes.PAR_MES%>"/>
					<input class="form-control" placeholder = "Año" type="number" value="<%=calendar.get(GregorianCalendar.YEAR) %>" name="<%=Constantes.PAR_YEAR%>" id="<%=Constantes.PAR_YEAR%>"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2">Genero:</label>
					<div class="col-xs-10">
						<input type="radio" name="<%=Constantes.PAR_GENERO %>" id="" <%= Genero.MASCULINO == alumno.getGenero() ? "checked" : "" %> value="<%=Genero.MASCULINO.getCodigo() %>"/> <%=Genero.MASCULINO.getValor() %>
						<input type="radio" name="<%=Constantes.PAR_GENERO %>" id="" <%= Genero.FEMENINO == alumno.getGenero() ? "checked" : "" %> value="<%=Genero.FEMENINO.getCodigo() %>"/> 	<%=Genero.FEMENINO.getValor() %>
						<input type="radio" name="<%=Constantes.PAR_GENERO %>" id="" <%= Genero.OTROS == alumno.getGenero() ? "checked" : "" %>	value="<%=Genero.OTROS.getCodigo() %>"/> <%=Genero.OTROS.getValor() %>															
					</div>
				</div>
				<div class="form-group">
				<button class = "btn btn-success" type="submit"> Guardar </button>
				</div>
			</form>			
	<%	}
		%>
		</div>
<%@ include file="/includes/footer.jsp"%>