<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>

<a class= "btn btn-warning" href="index.jsp"> Página principal</a>

	<%
	
		List<Usuario> users = (List<Usuario>) request.getAttribute(Constantes.ATT_LIST_USUARIO);
		//List<Usuario> cont = (List<Usuario>) getServletContext().getAttribute(Constantes.ATT_LIST_USUARIO);
		String formulario = "";
		
		for(Usuario user: users)
		{
			formulario =  "<form name='formAl' class='col-xs-2 col-md-6' id='formAl' action= '" + Constantes.SERVLET_ADMINISTRACION + "?" + Constantes.PAR_ID_SESION + "=" + user.sessionID  + "' method = 'post'>";
			formulario +="<input type='hidden' "+ "name='" + Constantes.PAR_OPERACION + "' value = '" + Constantes.OP_DELETE + "'/>";
			formulario +="<input class='btn btn-danger' type='submit' value='Logout'/>";
			formulario +="</form>";
			
			
			%>
				<div class = "col">
					<a class="col-xs-10 col-md-6" href = ""><%=user.getUserName()%></a> <%=formulario %>
				</div>
			<%
			
		}
	
	
	%>
	
	
<%@ include file="../includes/footer.jsp"%>