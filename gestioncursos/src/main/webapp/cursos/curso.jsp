<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.services.TipoCurso"%>
<%@page import="com.ipartek.formacion.control.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>	
<main>
		<%
		Curso curso = (Curso) request.getAttribute(Constantes.ATT_CURSO);
		int op = -1;
		String tGuardar = "";
		if(curso!=null){
			op = Constantes.OP_UPDATE;
			tGuardar ="guardar";
		}else{
			curso = new Curso();
			op = Constantes.OP_CREATE;
			tGuardar = "Crear";
		}
		%>
		<a class="btn btn-warning" href="<%=Constantes.SERVLET_CURSOS %>">Atras</a>

		
		<%
		if(curso!=null){
		%>
			<form name="formcurso" class="" id="formcurso" method='post' 
				action="<%=Constantes.SERVLET_CURSOS%>">
				<input type="hidden" 
					id="<%=Constantes.PAR_OPERACION %>"
					name="<%=Constantes.PAR_OPERACION %>"  
					value="<%=op %>"/>
				<input type="hidden" 
					id="<%=Constantes.PAR_CODIGO %>" 
					name="<%=Constantes.PAR_CODIGO %>" 
					value="<%=curso.getCodigo()%>"/>
				<div class="form-group">
					<label class="sr-only" for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
					<input  type="text" class="form-control "
					placeholder="Indrodruzca el nombre del Curso"
						name="<%=Constantes.PAR_NOMBRE%>" 
						id="<%=Constantes.PAR_NOMBRE%>" 
						value="<%=curso.getNombre() %>"
						/>				
				</div>
				<div class="form-group">				
				<label for="<%=Constantes.PAR_REFERENCIA%>">Referencia:</label>
				<input type="text" 
					name="<%=Constantes.PAR_REFERENCIA%>" 
					id="<%=Constantes.PAR_REFERENCIA%>" 
					value="<%=curso.getReferencia()%>"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2">Tipo de curso</label>
					<select name="<%=Constantes.PAR_TIPOCURSO%>">
					<%
					TipoCurso[] tipos = TipoCurso.values();
					if(tipos!=null){
						for(int i=0;i<tipos.length;i++){
							%>
						<option <%= tipos[i].equals(curso.getTc()) ? "selected" : "" %> 
						value="<%=tipos[i].getCodigo()%>"><%=tipos[i].getNombre() %></option>
							<%
						}
					}
					
					%>
					</select>
				</div>
				<div class="form-group">
						<label class="col-xs-2">Seleccione modulos:</label>
						<div class="col-xs-10">
								<%
								List<Modulo>modulos = (List<Modulo>) request.getAttribute(Constantes.ATT_LISTADO_MODULOS);
								int nHoras = 0;
								if (modulos!=null){
									for (Modulo m: modulos){
										Map <Integer,Modulo> mods =curso.getModulos();
										boolean presente = false;
										if (mods.containsKey(m.getCodigo())){
											presente = true;
											nHoras += m.getDuracion().getDuracion();
										}%>
										<input type="checkbox" name="<%=Constantes.PAR_LISTADO_MODULOS %>" id=""
											<%= presente ? "checked" : "" %> 
											 value="<%=m.getCodigo() %>"/> 
											<%=m.getNombre() %>
										<%
									}
								}else{%>
									No hay modulos.
								<%}
								%>
																			
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2">Seleccione alumnos:</label>
						<div class="col-xs-10">
							
								
								<%
								List<Alumno>alumnos = (List<Alumno>) request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
								
								if (alumnos!=null && alumnos.size()>0){
									for (Alumno a: alumnos){
										Map <String,Alumno> usuarios =curso.getAlumnos();
										%>
										<input type="checkbox" name="<%=Constantes.PAR_LISTADO_ALUMNOS %>" id=""
											<%=curso.getAlumnos().containsKey(a.getDni()) ? "checked" : "" %> 
											 value="<%=a.getCodigo() %>"/> 
											<%=a.getNombre()+" "+ a.getApellidos() %>
										<%
									}
								}else{%>
									No hay alumnos.
								<%}
								%>
																		
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2">Total alumnos:</label>
						<div class="col-xs-10">
						<%=curso.getAlumnos().size() %>
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2">Total horas:</label>
						<div class="col-xs-10">
						<%=nHoras %>
						</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-success">
						<%=tGuardar %>
					</button>
				</div>
			</form>

			
	<%	}
		%>
</main>
<%@ include file="../includes/footer.jsp" %>