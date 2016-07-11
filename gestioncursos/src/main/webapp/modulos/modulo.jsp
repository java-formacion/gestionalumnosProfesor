<%@page import="com.ipartek.formacion.pojo.Duracion"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<%
	Modulo modulo = (Modulo) request
			.getAttribute(Constantes.ATT_MODULO);
	int op = -1;
	if (modulo != null) {
		op = Constantes.OP_UPDATE;
%><title>Modulo <%=modulo.getNombre()%></title>
<%
	} else {
%><title>Modulo - Modulo nuevo</title>
<%
	modulo = new Modulo();
		op = Constantes.OP_CREATE;
	}
%>

<main>
<div class="row">
	<div class="col-xs-12">
		<form name="formulario" id="formulario" method="POST" action="<%=Constantes.SERVLET_MODULOS%>">
			<div class="col-xs-6">
				<input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>"
					value="<%=modulo.getCodigo()%>" /> <input type="hidden" id="<%=Constantes.PAR_OPERACION%>"
					name="<%=Constantes.PAR_OPERACION%>" value="<%=op%>" />
				<div class="form-group">
					<label for="<%=Constantes.PAR_REFERENCIA%>">REFERENCIA: </label> <input type="text"
						class="form-control" name="<%=Constantes.PAR_REFERENCIA%>" id="<%=Constantes.PAR_REFERENCIA%>"
						value="<%=modulo.getReferencia()%>" />
				</div>
				<div class="form-group">
					<label for="<%=Constantes.PAR_NOMBRE%>">NOMBRE: </label> <input type="text"
						class="form-control" name="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>"
						value="<%=modulo.getNombre()%>" />
				</div>
				<div class="form-group">
					<label for="<%=Constantes.PAR_DURACION%>">DURACIÓN: </label> <select class="form-control"
						name="<%=Constantes.PAR_DURACION%>" id="<%=Constantes.PAR_DURACION%>">
						<option value="<%=Modulo.CODIGO_MODULO%>">-- Seleccione la duración --</option>
						<%
							// 						DuracionModulo[] duraciones = request.getAttribute(Constante.ATT_LISTADO_DURACIONES);
							for (Duracion duracion : Duracion.values()) {
						%>
						<option <%=(modulo.getDuracion() == duracion) ? "selected" : ""%>
							value="<%=duracion.getCodigo()%>"><%=duracion.getDuracion()%> horas
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-inline">
					<div class="form-group">
						<a href="<%=Constantes.SERVLET_MODULOS%>" class="btn btn-danger">Cancelar</a>
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