<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.pojo.Idioma"%>
<%@page import="com.ipartek.formacion.pojo.Genero"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.controller.exception.AlumnoError"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<jsp:include page="../includes/header.jsp" />  


<!--  BOOTSTRAP JS LIBS -->
<script src="js/bootstrap.min.js"></script>
		
<!-- JQUERY LIBRARY 1.11.3  -->
<script src="js/jquery.min.js"></script>

<%
	Alumno alumno = (Alumno) request.getAttribute(Constantes.ATT_ALUMNO);
	int op = -1;
	String tGuardar ="";
	
	if(alumno!=null){
		op = Constantes.OP_UPDATE;
		tGuardar = "Actualizar";
	} else{
		op = Constantes.OP_CREATE;
		alumno = new Alumno();
		tGuardar = "Crear";
	}
%>

<div id="wrapper">
	<a href="<%= Constantes.SERVLET_ALUMNOS %>" class="btn btn-warning">
		<span class="fa fa-arrow-left" aria-hidden="true"></span>
		Atras
	</a>
	<br/>
	
	<%
	
	if(alumno instanceof AlumnoError){
		AlumnoError aux = (AlumnoError) alumno;
		out.write(aux.getMensaje());
	}
	
	if(alumno!=null){
		//out.write(curso.getCodigo() + " - " + curso.getNombre());
		
	%>
		<form class="form-horizontal col-xs-5" method="post" action="<%= Constantes.SERVLET_ALUMNOS %>">
			<input type="hidden"
				name="<%= Constantes.PAR_OPERACION %>"
				id="<%= Constantes.PAR_OPERACION %>"
				value="<%= op %>" />
				
			<input type="hidden"
				name="<%= Constantes.PAR_CODIGO %>"
				id="<%= Constantes.PAR_CODIGO %>"
				value="<%= alumno.getCodigo() %>" />
				
			<div class="form-group">
				<label for="<%= Constantes.PAR_NOMBRE %>">Nombre: </label>
				
				<input type="text" 
					name="<%= Constantes.PAR_NOMBRE %>" 
					id="<%= Constantes.PAR_NOMBRE %>"
					value="<%= alumno.getNombre() %>"
					size="20"
					class="form-control"
					placeholder="Nombre del alumno"
					 />
				
				<div class="alert alert-danger alert-dismissible fade in hide" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button> 
					<strong>Holy guacamole!</strong> 
					Best check yo self, you're not looking too good. 
				</div>
					
			</div>
			
			<div class="form-group">
				<label for="<%= Constantes.PAR_APELLIDOS %>">APELLIDOS: </label>
					
				<input type="text" 
					name="<%= Constantes.PAR_APELLIDOS %>" 
					id="<%= Constantes.PAR_APELLIDOS %>"
					value="<%= alumno.getApellidos() %>"
					size="20"
					class="form-control"
					placeholder="Apellidos del alumno"
					 />
			</div>		
				
			<div class="form-group">
				<label for="<%= Constantes.PAR_DNI %>">DNI: </label>
				
				<input type="text" pattern="((([A-Z]|[a-z])\d{8})|(\d{8}([A-Z]|[a-z])))"
					name="<%= Constantes.PAR_DNI %>" 
					id="<%= Constantes.PAR_DNI %>"
					value="<%= alumno.getDni() %>"
					size="20"
					class="form-control"
					placeholder="DNI del alumno"
					 />
			</div>
			
			<div class="form-group">
				<label for="<%= Constantes.PAR_EMAIL %>">Email: </label>
					
				<input type="text" 
					name="<%= Constantes.PAR_EMAIL %>" 
					id="<%= Constantes.PAR_EMAIL %>"
					value="<%= alumno.getEmail() %>"
					size="20"
					class="form-control"
					placeholder="Email del alumno"
					 />
			</div>	
			
			<div class="form-group">
				<label for="<%= Constantes.PAR_TELEFONO %>">Telefono: </label>
					
				<input type="text" 
					name="<%= Constantes.PAR_TELEFONO %>" 
					id="<%= Constantes.PAR_TELEFONO %>"
					value="<%= alumno.getTelefono() %>"
					size="20"
					class="form-control"
					placeholder="Telefono del alumno"
					 />
			</div>
			
			<div class="form-group form-inline">
				<label for="<%= Constantes.PAR_DNI %>">Fecha: </label>
				
				<%
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.setTime(alumno.getfNacimiento());
					
				%>
				
				<input type="number"
					value="<%= calendar.get(GregorianCalendar.DAY_OF_MONTH) %>"
					min="1"
					max="31"
					name="<%= Constantes.PAR_DIA %>"
					class="form-control"
					placeholder="Dia"
					 />
					 
				<input type="number"
					value="<%= calendar.get(GregorianCalendar.MONTH)+1 %>"
					min="1"
					max="12"
					name="<%= Constantes.PAR_MES %>"
					class="form-control"
					placeholder="Mes"
					 />
				
				<input type="text" 
					value="<%= calendar.get(GregorianCalendar.YEAR) %>"
					name="<%= Constantes.PAR_ANYO %>"
					size="12"
					class="form-control"
					placeholder="A&ntilde;o"
					 />
			</div>
			
			<div class="form-group form-inline">
				<label class="">Genero: </label>
				<div class="">
					<input type="radio" name="<%= Constantes.PAR_GENERO %>" id=""
						<%= Genero.MASCULINO == alumno.getGenero() ? "checked" : "" %>
						value="<%= Genero.MASCULINO.getCodigo() %>" /> 
						<%= Genero.MASCULINO.getValor() %>
						
					<input type="radio" name="<%= Constantes.PAR_GENERO %>" id="" 
						<%= Genero.FEMENINO == alumno.getGenero() ? "checked" : "" %>
						value="<%= Genero.FEMENINO.getCodigo() %>" /> 
						<%= Genero.FEMENINO.getValor() %>
						
					<input type="radio" name="<%= Constantes.PAR_GENERO %>" id=""
						<%= Genero.OTRO == alumno.getGenero() ? "checked" : "" %>
						value="<%= Genero.OTRO.getCodigo() %>" /> 
						<%= Genero.OTRO.getValor() %>
					
				</div>
			</div>
			
			<div class="form-group form-inline">
				<label class="">Idiomas: </label>
				<div class="">						
					<input type="checkbox" name="<%= Constantes.PAR_IDIOMA %>" id="" 
						<%= alumno.getIdiomas().contains(Idioma.CASTELLANO) ? "checked" : "" %>
						value="<%= Idioma.CASTELLANO.getCodigo() %>" /> 
						<%= Idioma.CASTELLANO.getNombre() %>
						
					<input type="checkbox" name="<%= Constantes.PAR_IDIOMA %>" id="" 
						<%= alumno.getIdiomas().contains(Idioma.EUSKERA) ? "checked" : "" %>
						value="<%= Idioma.EUSKERA.getCodigo() %>" /> 
						<%= Idioma.EUSKERA.getNombre() %>
						
					<input type="checkbox" name="<%= Constantes.PAR_IDIOMA %>" id=""
						<%= alumno.getIdiomas().contains(Idioma.INGLES) ? "checked" : "" %>
						value="<%= Idioma.INGLES.getCodigo() %>" /> 
						<%= Idioma.INGLES.getNombre() %>
					
				</div>
			</div>
			
			<div class="form-group form-inline">
				<label class="col-xs-2">Curso: </label>
				<div class="col-xs-10">						
					<select name="<%= Constantes.PAR_CURSO %>">
						<%
							List<Curso> cursos = (List<Curso>)request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
							
							if(cursos!=null){
								for(Curso curso: cursos){
									%>
										<option 
											value="<%= curso.getCodigo() %>"
											<%= alumno.getCurso().equals(curso) %>
											>
												<%= curso.getNombre() %>
										</option>
									<%
								}
							}
						%>
					</select>
				</div>
			</div>
				
			<div class="form-group">
				<input type="submit" value="<%= tGuardar %>" class="btn btn-success" />
			</div>
		</form>
	<%
	}
	%>
	
</div>

<%@ include file="/includes/footer.jsp" %>