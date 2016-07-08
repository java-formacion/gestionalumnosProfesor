<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.pojo.TipoCurso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.service.ModuloService"%>
<%@page import="com.ipartek.formacion.service.ModuloServiceImp"%>
<%@page import="com.ipartek.formacion.service.AlumnoService"%>
<%@page import="com.ipartek.formacion.service.AlumnoServiceImp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<main> <%
 	int op = -1;
 	Curso curso = (Curso) request.getAttribute("curso");
 	String tGuardar = "";
 	Map<Integer, Modulo> modulos = new HashMap<Integer, Modulo>();
 	ModuloService mService = ModuloServiceImp.getInstance();
 	AlumnoService aService = AlumnoServiceImp.getInstance();
 	List<Alumno> lAlumnos = aService.getAll();
 	List<Modulo> lModulos = mService.getAll();
 	int nHoras = 0;

 	for (Modulo m : lModulos)
 		modulos.put(m.getCodigo(), m);

 	if (curso != null) {
 		op = Constantes.OP_UPDATE;
 		tGuardar = "guardar";

 	} else {
 		curso = new Curso();
 		op = Constantes.OP_CREATE;
 		tGuardar = "crear";

 	}
 %> <a class='btn btn-warning' href="<%=Constantes.SERVLET_CURSOS%>">Atras</a>

<%
	if (curso != null) {
%> <%=Constantes.SERVLET_CURSOS%>
<form name="id" method="post" action="<%=Constantes.SERVLET_CURSOS%>">
	<input type="hidden" id="<%=Constantes.PAR_OPERACION%>"
		name="<%=Constantes.PAR_OPERACION%>" value="<%=op%>" /> 
	<input type="hidden" id="<%=Constantes.PAR_CODIGO%>"
		name="<%=Constantes.PAR_CODIGO%>" value="<%=curso.getCodigo()%>" />

	<div class="form-group">
		<label class="sr-only" for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
		<input type="text" class="form-control "
			placeholder="Indrodruzca el nombre del Curso"
			name="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>"
			value="<%=curso.getNombre()%>" />
		<div class="hidden"></div>
	</div>

	<div class="form-group">
					<label class="col-xs-2">Tipo Curso:</label>
					<div class="col-xs-10">
						<select name="<%=Constantes.PAR_TIPOCURSO%>">
						<% TipoCurso[] tipos = TipoCurso.values(); 
						for(int i = 0; i < tipos.length; i++){
							%>
							<option <%=curso.getTipoCurso().equals(tipos[i]) ? "selected" : "" %> value="<%=tipos[i].getCodigo()%>">
							<%=tipos[i].getValor() %>
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
				for (Modulo modulo : lModulos) {
						Map<Integer, Modulo> mods = curso.getModulos();
						boolean exists = false;
						if (mods.containsKey(modulo.getCodigo())) {
							exists = true;
							nHoras += modulo.getDurModulo().getValor();
						}
			%>
			<input type="checkbox" name="<%="Modulo"%>" id=""
				<%=exists ? "checked" : ""%> value="<%=modulo.getCodigo()%>" />

			<%=modulo.getNombre()%>


			<%
				}
			%>

		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-success">
				<%=tGuardar%>
			</button>
		</div>
		<div class="form-group">
			<label class="col-xs-2">Alumnos:</label>
			<div class="col-xs-10">

				<%
					for (Alumno alumno : lAlumnos) {
							Map<String, Alumno> als = curso.getAlumnos();
							boolean exists = false;
							if (als.containsKey(alumno.getDni())) {
								exists = true;
							}
				%>
				<input type="checkbox" name="<%="Modulo"%>" id=""
					<%=exists ? "checked" : ""%> value="<%=alumno.getCodigo()%>" />

				<%=alumno.getNombre()%>
				<%=alumno.getApellidos()%>


				<%
					}
				%>

			</div>
</form>

<%
	}
%> </main>
<%@ include file="../includes/footer.jsp"%>