<%@page import="com.ipartek.formacion.pojo.Mensaje"%>
<%@page import="com.ipartek.formacion.control.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
Mensaje mensaje = (Mensaje) request.getAttribute(Constantes.ATT_MENSAJE);
if (mensaje==null){
	mensaje = (Mensaje) session.getAttribute(Constantes.ATT_MENSAJE);
}

%>    
  <% if (mensaje!=null){%>
	  <div class="alert alert-<%=mensaje.getType() %>" role="alert">
		<button class="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;<%=mensaje.getMsg() %></span></button>
	</div>	   
 <% }%> 
