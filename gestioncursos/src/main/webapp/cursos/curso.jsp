<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.pojo.TipoCurso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<%
	Curso curso = (Curso) request.getAttribute(Constantes.ATT_CURSO);
	int op = -1;
	if (curso != null) {
		op = Constantes.OP_UPDATE;

	} else {

		curso = new Curso();
		op = Constantes.OP_CREATE;
	}
%>
<main>
<div class="row">

	<div class="col-xs-12">
		<form name="formulario" id="formulario" method="POST" action="<%=Constantes.SERVLET_CURSOS%>">
			<div class="col-xs-6">
				<input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>"
					value="<%=curso.getCodigo()%>" /> <input type="hidden" id="<%=Constantes.PAR_OPERACION%>"
					name="<%=Constantes.PAR_OPERACION%>" value="<%=op%>" />
				<div class="form-group">
					<label for="<%=Constantes.PAR_NOMBRE%>">NOMBRE: </label> <input type="text"
						class="form-control" name="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>"
						value="<%=curso.getNombre()%>" />
				</div>
				<div class="form-group">
					<label for="<%=Constantes.PAR_TIPO%>">TIPO CURSO: </label> <select class="form-control"
						name="<%=Constantes.PAR_TIPO%>" id="<%=Constantes.PAR_TIPO%>">
						<option value="<%=Curso.CODIGO_CURSO%>">-- Seleccione un tipo --</option>
						<%
							for (TipoCurso tipo : TipoCurso.values()) {
						%>
						<option <%=(curso.getTipo() == tipo) ? "selected" : ""%> value="<%=tipo.getCodigo()%>"><%=tipo.getTipo()%>
						</option>
						<%
							}
						%>
					</select>
				</div>
				<div class="form-group">
					<label>ALUMNOS: </label>
					<div class="form-inline">
						<%
							List<Alumno> alumnos = (List<Alumno>) request
									.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);

							if (alumnos != null) {
								for (Alumno alumno : alumnos) {
						%>
						<div class="col-xs-6">
							<div class="checkbox">
								<label> <input type="checkbox" name="<%=Constantes.PAR_ALUMNOS%>"
									id="<%=Constantes.PAR_ALUMNOS%>" value="<%=alumno.getCodigo()%>"
									<%=curso.getAlumnos().containsKey(alumno.getDni()) ? "checked"
							: ""%> /> <%=alumno.getNombre()%>
								</label>
							</div>
						</div>
						<%
							}
							}
						%>
					</div>

				</div>
				<div class="col-xs-12">
					<div class="form-group">
						<p>
							TOTAL ALUMNOS: <span class="label label-primary"><%=curso.getAlumnos().size()%></span>
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-6">

				<div class="row">
					<label>MODULOS: </label>
					<div class="form-inline">
						<%
							int nHoras = 0;
							List<Modulo> modulos = (List<Modulo>) request
									.getAttribute(Constantes.ATT_LISTADO_MODULOS);
							if (modulos != null)
								for (Modulo modulo : modulos) {
									boolean esta = false;
									if (curso.getModulos().containsKey(modulo.getCodigo())) {
										esta = true;
										nHoras += modulo.getDuracion();
									}
						%>

						<div class="col-xs-6">
							<div class="checkbox">
								<label> <input type="checkbox" name="<%=Constantes.PAR_MODULOS%>"
									id="<%=Constantes.PAR_MODULOS%>" value="<%=modulo.getCodigo()%>" <%=esta ? "checked" : ""%> />
									<%=modulo.getNombre()%>
								</label>
							</div>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<div class="form-group">
					<p>
						TOTAL MODULOS: <span class="label label-primary"><%=curso.getModulos().size()%></span>
					</p>
				</div>
				<div class="row">
					<div class="form-group">
						<label>Nº HORAS:</label>
						<div class="col-xs-12"><%=nHoras%></div>
					</div>
				</div>

			</div>
			<div class="col-xs-12">
				<div class="form-inline">
					<div class="form-group">
						<a href="<%=Constantes.SERVLET_CURSOS%>" class="btn btn-danger">Cancelar</a>
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