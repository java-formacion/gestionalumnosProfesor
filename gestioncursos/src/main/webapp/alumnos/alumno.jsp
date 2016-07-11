<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.service.Idiomas"%>
<%@page import="com.ipartek.formacion.service.Genero"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.controller.exception.AlumnoError"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main>
<%
	Alumno alumno = (Alumno)request.getAttribute(Constantes.ATT_ALUMNO);
	int op = -1;
	if(alumno!=null){
		op = Constantes.OP_UPDATE;
	%>	
	<title>Alumno <% out.write(alumno.getNombre()); %></title>
	<% 
	}else{
		%>
		<title>Alumno - Alumno nuevo</title>
		<%
		op = Constantes.OP_CREATE;
		alumno = new Alumno();
		}
		%>

	<a href="<%=Constantes.SERVLET_ALUMNOS %>">Listado de alumnos</a>
	<div id="wrapper">
	<%
	if(alumno instanceof AlumnoError){ //si alumno es instancia de AlumnoError
		AlumnoError aux = (AlumnoError) alumno; //cast in
		out.write(aux.getMensaje());
	}
	%>
	
	
	<%
	
	if(alumno!=null){
	%>
		<form class="form-horizontal" name="" method="post" action="<%=Constantes.SERVLET_ALUMNOS%>">
			<input type="hidden" 
				id="<%=Constantes.PAR_OPERACION%>" 
				name="<%=Constantes.PAR_OPERACION%>" 
				value="<%=op%>"/>
			<input type="hidden" 
				id="<%=Constantes.PAR_CODIGO%>" 
				name="<%=Constantes.PAR_CODIGO%>"
				value="<%=alumno.getCodigo()%>"/>
			<div class="form-group">
			<div class= col-xs-1>
				<label for="<%=Constantes.PAR_NOMBRE%>">NOMBRE:</label>
				</div>
				<div class= col-xs-2>
				<input type="text" class="form-control"
					id="<%=Constantes.PAR_NOMBRE%>" 
					name="<%=Constantes.PAR_NOMBRE%>" 
					value="<%=alumno.getNombre()%>"/>
					
					</div>
			</div>	
			<div class="form-group">
			<div class= col-xs-1>
				<label for="<%=Constantes.PAR_APELLIDOS%>">APELLIDOS:</label>
				</div>
				<div class= col-xs-2>
				<input type="text" class="form-control"
					id="<%=Constantes.PAR_APELLIDOS%>" 
					name="<%=Constantes.PAR_APELLIDOS%>" 
					value="<%=alumno.getApellidos()%>"/>
				</div>	
			</div>	
			<div class="form-group">
			<div class= col-xs-1>
			    <label for="<%=Constantes.PAR_DNI%>">DNI:</label>
			    </div>
			    <div class= col-xs-2>
			    <input type="text" class="form-control"
					id="<%=Constantes.PAR_DNI%>" 
					name="<%=Constantes.PAR_DNI%>" 
					value="<%=alumno.getDni()%>"
					pattern="(\d{8})([-]?)([A-Z]{1})"/>
				</div>
				</div>
			<%
			Date date = alumno.getfNacimiento();
			GregorianCalendar calendar= new GregorianCalendar();
			calendar.setTime(date);
			
			%>
				
			<div class="form-group">
				<div class= col-xs-1>
			    <label>Fecha:</label>
			    </div>
			    <div class="row">
				    <div class= col-xs-2>
					    <input type="number" value="<%=calendar.get(GregorianCalendar.DAY_OF_MONTH) %>"
					     min="1" max="31" class="form-control" name="<%=Constantes.PAR_DIA%>" />
					</div>
					<div class= col-xs-2>
					    <input type="number" value="<%=calendar.get(GregorianCalendar.MONTH)+1%>" 
					    min="1" max="12" class="form-control" name="<%=Constantes.PAR_MES%>" />
					 </div>
					 <div class= col-xs-2>
					    <input type="number" value="<%=calendar.get(GregorianCalendar.YEAR)%>" 
					    class="form-control"  name="<%=Constantes.PAR_ANYO%>" />
				     </div>
				</div>
			</div>	
			
			<div class="form-group">
				<div class= col-xs-1>
			    <label>Género:</label>
			    </div>
			     <div class="row">
				    <div class= col-xs-1>
					    <input type="radio" name="<%=Constantes.PAR_GENERO%>" 
					    id="<%=Constantes.PAR_CODIGO%>" <%=Genero.MASCULINO == alumno.getGenero() ? "checked" : "" %> 
					    value="<%=Genero.MASCULINO.getCodigo() %>" />
					    <%=Genero.MASCULINO.getValor()%>
					</div>
				
				    <div class= col-xs-1>
					    <input type="radio"  name="<%=Constantes.PAR_GENERO%>" 
					    id="<%=Constantes.PAR_CODIGO%>" <%=Genero.FEMENINO == alumno.getGenero() ? "checked" : "" %>
					    value="<%=Genero.FEMENINO.getCodigo() %>" />
					    <%=Genero.FEMENINO.getValor()%>
					</div>
					
					<div class= col-xs-1>
					    <input type="radio"  name="<%=Constantes.PAR_GENERO%>" 
					    id="<%=Constantes.PAR_CODIGO%>" <%=Genero.OTROS == alumno.getGenero() ? "checked" : "" %>
					    value="<%=Genero.OTROS.getCodigo() %>" />
					    <%=Genero.OTROS.getValor()%>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class= col-xs-1>
			    <label>Idiomas:</label>
			    </div>
			     <div class="row">
				    <div class= col-xs-1>
					    <input type="checkbox" name="<%=Constantes.PAR_IDIOMA%>" 
					    id="<%=Constantes.PAR_CODIGO%>" 
					     <%=alumno.getIdiomas().contains(Idiomas.CASTELLANO) ? "checked" : "" %>
					    value="<%=Idiomas.CASTELLANO.getCodigo() %>" />
					    <%=Idiomas.CASTELLANO.getNombre() %>
					</div>
					
					<div class= col-xs-1>
					    <input type="checkbox" name="<%=Constantes.PAR_IDIOMA%>" 
					    id="<%=Constantes.PAR_CODIGO%>" 
					     <%=alumno.getIdiomas().contains(Idiomas.EUSKERA) ? "checked" : "" %>
					    value="<%=Idiomas.EUSKERA.getCodigo() %>" />
					    <%=Idiomas.EUSKERA.getNombre() %>
					</div>
				
				    <div class= col-xs-1>
					    <input type="checkbox" name="<%=Constantes.PAR_IDIOMA%>" 
					    id="<%=Constantes.PAR_CODIGO%>" 
					     <%=alumno.getIdiomas().contains(Idiomas.INGLES) ? "checked" : "" %>
					    value="<%=Idiomas.INGLES.getCodigo() %>" />
					    <%=Idiomas.INGLES.getNombre() %>
					</div>
					
				</div>
			</div>
			
			<div class="form-group">
				<div class= col-xs-10>
			    <label>Curso:</label>
			    <select name="<%=Constantes.PAR_CURSO %>">
			    <!-- 
			    	<option value="<%=Curso.CODIGO_CURSO %>">Seleccione un curso</option>
			     -->
			     	<%
			     	List<Curso> cursos = (List<Curso>)request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
			     	if(cursos!=null){
			     		for(Curso curso: cursos){
			     		%>
			     		<option <%=alumno.getCurso().equals(curso)   ? "selected" : "" %>
			     		value="<%=curso.getCodigo() %>"> <%=curso.getNombre() %></option>
			     		<% 	
			     		}
			     	}
			     	%>
			    </select>	
			    </div>
			   
			</div>    
			
			
		    <button type="submit" class="btn btn-success">Guardar</button>
		</form>
	<% }
		%>
	</div>
</main>
<%@ include file="../includes/footer.jsp" %>