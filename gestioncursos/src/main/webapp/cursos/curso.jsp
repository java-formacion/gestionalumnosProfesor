<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.pojo.TipoCurso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
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
		<%=Constantes.SERVLET_CURSOS%>
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
					<label class="col-xs-2">Tipo Curso:</label>
					<div class="col-xs-10">
						<select name="<%=Constantes.PAR_TIPOCURSO%>">
						<% TipoCurso[] tipos = TipoCurso.values(); 
						for(int i = 0; i < tipos.length; i++){
							%>
							<option <%=curso.getTipo().equals(tipos[i]) ? "selected" : "" %> value="<%=tipos[i].getCodigo()%>">
							<%=tipos[i].getNombre() %>
							</option>
							<%
						}
						
						%>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2">Modulos:</label>
					<div class="col-xs-10">
					<%
					List<Modulo> modulos = (List<Modulo>)request.getAttribute(Constantes.ATT_LISTADO_MODULOS);
					int nHoras = 0;
					if(modulos!=null){
						for(Modulo m: modulos){
							Map<Integer,Modulo> mods = curso.getModulos();
							String checked ="";
							if(mods.containsKey(m.getCodigo())){
								//esta = true;
								checked= "checked";
								nHoras += m.getDuracion().getDuracionHoras();
							}
							
							%>
						<input type="checkbox" <%= checked %>name="<%=Constantes.PAR_LISTADO_MODULOS %>" value="<%=m.getCodigo()%>"/> <%=m.getNombre() %>
						
							<%
						}
					}
					%>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2">Alumnos:</label>
					<div class="col-xs-10">
					<%
					List<Alumno>alumnos =  (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
					if(alumnos!=null && alumnos.size()>0){
						for(Alumno a: alumnos){
							%>
							<input <%= curso.getAlumnos().containsKey(a.getDni()) ? "checked" :""%> 
								type="checkbox" name="<%=Constantes.PAR_LISTADO_ALUMNOS%>" 
								value="<%=a.getCodigo()%>"
								/> <%=a.getNombre() +" "+a.getApellidos() %>
							<%
						}
					}else{
						%>
						No hay alumnos.
						<%
					}
					%>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2">Total Alumnos:</label>
					<div class="col-xs-10">
					<%=curso.getAlumnos().size() %> alumnos
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2">Total Horas:</label>
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