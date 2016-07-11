<%@page import="com.ipartek.formacion.pojo.Mensaje"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Mensaje mensaje = (Mensaje) request
			.getAttribute(Constantes.MSG_ERROR);

	if (mensaje == null) {
		mensaje = (Mensaje) session.getAttribute(Constantes.MSG_ERROR);
	}
	if (mensaje != null) {
%>
<div class="alert alert-<%=mensaje.getTipo()%> alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<%=mensaje.getMsg()%>
</div>
<%
	}
%>