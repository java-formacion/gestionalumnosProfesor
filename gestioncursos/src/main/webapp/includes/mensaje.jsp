<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Mensaje"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
Mensaje mensaje = (Mensaje) request.getAttribute(Constantes.ERROR_LOGIN);

if(mensaje==null){
	mensaje= (Mensaje) session.getAttribute(Constantes.ERROR_LOGIN);
}

if(mensaje!=null){ %>

<div class="alert alert-alert-dismissible">
	<button type="button" class="close"></button>
	<%=mensaje.getMsg() %>
</div>

<% }%>