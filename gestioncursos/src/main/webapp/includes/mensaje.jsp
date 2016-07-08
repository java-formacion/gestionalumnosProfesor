<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Mensaje"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Mensaje mensaje = (Mensaje) request.getAttribute(Constantes.ATT_MENSAJE);
%>
<% if (mensaje != null) { %>
<div class="alert alert- alert-<%=mensaje.getType() %>" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  	<%=mensaje.getMsg() %>
</div>
<% } %>