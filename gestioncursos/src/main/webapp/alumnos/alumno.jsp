<%@page import="java.util.Properties"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.pojo.Idioma"%>
<%@page import="com.ipartek.formacion.service.Genero"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.controller.exception.AlumnoError"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<%
  Alumno alumno = (Alumno) request.getAttribute(Constantes.ATT_ALUMNO);
  int op = -1;
  if (alumno != null) {
    op = Constantes.OP_UPDATE;

  } else {

    alumno = new Alumno();
    op = Constantes.OP_CREATE;
  }
%>
<main>

<div class="row">
	<div class="col-xs-12">
		<form name="formulario" id="formulario" method="POST" action="<%=Constantes.SERVLET_ALUMNOS%>">
			<div class="col-xs-6">
				<input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>"
					value="<%=alumno.getCodigo()%>" /> <input type="hidden" id="<%=Constantes.PAR_OPERACION%>"
					name="<%=Constantes.PAR_OPERACION%>" value="<%=op%>" />
				<fieldset>
					<legend>Datos Personales</legend>
					<div class="form-group">
						<label for="${properties.parNombre}">NOMBRE: </label> <input type="text" class="form-control"
							name="${properties.parNombre}" id="${properties.parNombre}" value="<%=alumno.getNombre()%>" />

					</div>
					<div class="form-group">
						<label for="<%=Constantes.PAR_APELLIDOS%>">APELLIDOS: </label> <input type="text"
							class="form-control" name="<%=Constantes.PAR_APELLIDOS%>" id="<%=Constantes.PAR_APELLIDOS%>"
							value="<%=alumno.getApellidos()%>" />
					</div>
					<div class="form-group">
						<label for="<%=Constantes.PAR_DNI%>">DNI: </label> <input type="text" required
							class="form-control"
							pattern="(([X-Z]{1})([-]?)(\d{7})([-]?)([A-Z]{1}))|((\d{8})([-]?)([A-Za-z]{1}))"
							name="<%=Constantes.PAR_DNI%>" id="<%=Constantes.PAR_DNI%>" value="<%=alumno.getDni()%>" />
					</div>
					<%
					  Date date = alumno.getfNacimiento();
					  GregorianCalendar calendar = new GregorianCalendar();
					  calendar.setTime(date);
					%>
					<div class="form-group">
						<label for="<%=Constantes.PAR_MES%>">FECHA NACIMIENTO: </label>
						<div class="form-inline">
							<input type="number" class="form-control" min="1" max="31" name="<%=Constantes.PAR_DIA%>"
								value="<%=calendar.get(GregorianCalendar.DAY_OF_MONTH)%>" />/ <input type="number"
								class="form-control" min="1" max="12" name="<%=Constantes.PAR_MES%>"
								value="<%=calendar.get(GregorianCalendar.MONTH) + 1%>" /> / <input type="number"
								class="form-control" min="1950" max="2016" name="<%=Constantes.PAR_ANYO%>"
								value="<%=calendar.get(GregorianCalendar.YEAR)%>" />
						</div>
					</div>
					<div class="form-group">
						<div class="form-inline">
							<label>GENERO: </label> <input type="radio" class="form-control"
								name="<%=Constantes.PAR_GENERO%>" value="<%=Genero.FEMENINO.getCodigo()%>"
								<%=Genero.FEMENINO == alumno.getGenero() ? "checked" : ""%> />
							<%=Genero.FEMENINO.getValor()%>
							<input type="radio" class="form-control" name="<%=Constantes.PAR_GENERO%>"
								value="<%=Genero.MASCULINO.getCodigo()%>"
								<%=Genero.MASCULINO == alumno.getGenero() ? "checked" : ""%> />
							<%=Genero.MASCULINO.getValor()%>
							<input type="radio" class="form-control" name="<%=Constantes.PAR_GENERO%>"
								value="<%=Genero.OTROS.getCodigo()%>"
								<%=Genero.OTROS == alumno.getGenero() ? "checked" : ""%> />
							<%=Genero.OTROS.getValor()%>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="col-xs-6">
				<fieldset>
					<legend>Datos de Contacto</legend>
					<div class="form-group">
						<label for="${properties.parEmail}">E-MAIL: </label>
						<div class="input-group">
							<span class="input-group-addon" id="sizing-addon1"><i class="fa fa-envelope"
								aria-hidden="true"></i></span> <input type="email" id="${properties.parEmail}"
								name="${properties.parEmail}" class="form-control" placeholder="example@mail.com"
								aria-describedby="sizing-addon1">
						</div>

					</div>
					<div class="form-group">
						<label for="${properties.parTelefono}">TELEFONO: </label>
						<div class="input-group">
							<span class="input-group-addon" id="sizing-addon2"><i class="fa fa-phone"
								aria-hidden="true"></i></span> <input type="text" id="${properties.parTelefono}"
								name="${properties.parTelefono}" class="form-control" aria-describedby="sizing-addon2">
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>Datos Adicionales</legend>
					<div class="form-group">
						<div class="form-inline">
							<label>IDIOMAS: </label>
							<%
							  for (Idioma idioma : Idioma.values()) {
							%><input type="checkbox" class="form-control" name="<%=Constantes.PAR_IDIOMA%>"
								value="<%=idioma.getCodigo()%>" <%if (alumno.getIdiomas().contains(idioma)) {%> checked
								<%}%> />
							<%=idioma.getNombre()%>
							<%
							  }
							%>
						</div>
					</div>
<!-- 					<div class="form-group"> -->
<%-- 						<label for="<%=Constantes.PAR_CURSO%>">CURSO: </label> <select class="form-control" --%>
<%-- 							name="<%=Constantes.PAR_CURSO%>" id="<%=Constantes.PAR_CURSO%>"> --%>
<%-- 							<option value="<%=Curso.CODIGO_CURSO%>">-- Seleccione un Curso--</option> --%>
<%-- 							<% --%>
<!-- // 							  List<Curso> cursos = (List<Curso>) request.getAttribute(Constantes.ATT_LISTADO_CURSOS); -->
<!-- // 							  if (cursos != null) -->
<!-- // 							    for (Curso curso : cursos) { -->
<%-- 							%> --%>
<%-- 							<option <%=(curso.equals(alumno.getCurso())) ? "selected" : ""%> --%>
<%-- 								value="<%=curso.getCodigo()%>"><%=curso.getNombre()%></option> --%>
<%-- 							<% --%>
<!-- // 							  } -->
<%-- 							%> --%>
<!-- 						</select> -->
<!-- 					</div> -->
				</fieldset>
				<%
				  if (alumno instanceof AlumnoError) {
				    AlumnoError aux = (AlumnoError) alumno;
				%>
				<div class="form-group">
					<span class="alert alert-danger"><%=aux.getMensaje()%></span>
				</div>
				<%
				  }
				%>
			</div>
			<div class="col-xs-12">
				<div class="form-inline">
					<div class="form-group">
						<a href="<%=Constantes.SERVLET_ALUMNOS%>" class="btn btn-danger">Cancelar</a>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Enviar</button>
					</div>
				</div>
			</div>


		</form>

	</div>
</div>
</main>
<%@ include file="../includes/footer.jsp"%>