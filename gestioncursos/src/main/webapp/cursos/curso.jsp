<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.pojo.TipoCurso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp" /> 

<!--  BOOTSTRAP JS LIBS -->
<script src="js/bootstrap.min.js"></script>
		
<!-- JQUERY LIBRARY 1.11.3  -->
<script src="js/jquery.min.js"></script>

<%
	Curso curso = (Curso) request.getAttribute("curso");
	int op = -1;
	String tGuardar ="";
	
	if(curso!=null){
		op = Constantes.OP_UPDATE;
		tGuardar = "Actualizar";
	}else{
		curso = new Curso();
		op = Constantes.OP_CREATE;
		tGuardar = "Crear";
	}
%>		
	
<div id="wrapper">
	<a href="<%= Constantes.SERVLET_CURSOS %>" class="btn btn-warning">
		<span class="fa fa-arrow-left" aria-hidden="true"></span>
		Atras
	</a>
	<br/>
	
	<%
	
	if(curso!=null){
		//out.write(curso.getCodigo() + " - " + curso.getNombre());
		
	%>
		<form class="form-inline" method="post" action="<%= Constantes.SERVLET_CURSOS %>">
			<input type="hidden"
				name="<%= Constantes.PAR_OPERACION %>"
				id="<%= Constantes.PAR_OPERACION %>"
				value="<%= op %>" />
				
			<input type="hidden"
				name="<%= Constantes.PAR_CODIGO %>"
				id="<%= Constantes.PAR_CODIGO %>"
				value="<%= curso.getCodigo() %>" />
				
			<div class="form-group">
				<label for="<%= Constantes.PAR_NOMBRE %>">Curso: </label>
				
				<input class="form-control" type="text" placeholder="Introduzca el nombre del curso"
					name="<%= Constantes.PAR_NOMBRE %>" 
					id="<%= Constantes.PAR_NOMBRE %>"
					value="<%= curso.getNombre() %>"
					size="80" />
			</div>
			
			<br/>
			
			<div class="form-group">
				<label for="<%= Constantes.PAR_TIPO_CURSO %>">Tipo de Curso: </label>					
					<select name="<%= Constantes.PAR_TIPO_CURSO %>">
					
						<%
							TipoCurso[] tipos = TipoCurso.values();
						
							for(int i=0; i<tipos.length; i++){
								%>
									<option 
										
										value="<%= tipos[i].getCodigo() %>"
										>
										<%= tipos[i].getValor() %>
									</option>
								<%
							}
						%>
					</select>
			</div>
			
			<!-- 					
					Curso.jso
						* Nº Curso
						* Referencia
						* Tipo de Curso (lanbide, hobetuz, formacion tripartita)
						* Checkboxs: modulos
						* Checkbox: alumnos
						* Total horas Modulos
						* Total Alumnos
						
					Modulo.jsp
						* Nº Modulo
						* Nombre
						* Duracion
			 -->
			
			<br/>
			
			<div class="form-group form-inline">
				<label class="">Modulos: </label>
				<div class="">						
					<%
						List<Modulo> modulos = (List<Modulo>) request.getAttribute(Constantes.ATT_LISTADO_MODULOS);
						int nHoras = 0;
						if(modulos!=null){
							for(Modulo modulo: modulos){
								Map<Integer,Modulo> mods = curso.getModulos();
								boolean presente =  false;
								if(mods.containsKey(modulo.getCodigo())){
									presente=true;
									nHoras += modulo.getDuracion().getValor();
								}
					%>
								<input type="checkbox" name="<%= Constantes.PAR_LISTADO_MODULOS %>" id="" 
										value="<%= modulo.getCodigo() %>" 
										<%= presente ? "checked" : ""  %>
										/> 
										<%= modulo.getNombre() %>
					<%
							}
						} else{
							System.out.println("Prueba 2");
						}
					%>
				</div>
			</div>
			
			<br/>
			
			<div class="form-group form-inline">
				<label class="">Alumnos: </label>
				<div class="">
					<%
						List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
						if(alumnos!=null && alumnos.size()>0){
							for(Alumno alumno: alumnos){
								%>
									<input type="checkbox" name="" 
										<%= curso.getAlumnos().containsKey(alumno.getDni()) ? "checked" : "" %>
										value="<%= alumno.getCodigo() %>"
									/>
									<%= alumno.getNombre() + " " + alumno.getApellidos() %>
								<%
							}
						} else{
							
						}
					%>
				</div>
			</div>
			
			<br/>
			
			<div class="form-group">
				<label for="<%= Constantes.PAR_NOMBRE %>">Referencia Curso: </label>
				
				<input class="form-control" type="text" placeholder="Referencia"
					name="<%= Constantes.PAR_REFERENCIA %>" 
					id="<%= Constantes.PAR_REFERENCIA %>"
					value="<%= curso.getReferencia() %>"
					size="10" />
			</div>
			
			<br/>
			
			<div class="form-group">
				<label for="<%= Constantes.PAR_NOMBRE %>">Total Alumnos: </label>
				<%= curso.getAlumnos().size() %>
			</div>
			
			<br/>
			
			<div class="form-group">
				<label for="">Total Horas: </label>
				<%= nHoras %>
			</div>
			
			<br/>
			
			<div class="form-group">
				<input type="submit" value="<%= tGuardar %>" class="btn btn-success" />
			</div>
			
		</form>
	<%
	}
	%>
	
</div>

<%@ include file="/includes/footer.jsp" %>