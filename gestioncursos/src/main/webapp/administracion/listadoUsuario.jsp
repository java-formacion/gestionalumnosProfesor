<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
<main>

<%
ServletContext contexto = session.getServletContext(); 

 List<Usuario> usuarios = (List<Usuario>) contexto.getAttribute(Constantes.ATT_LISTADO_USUARIOS);

 if(usuarios != null){ %>
 
 <jsp:include page ="../includes/mensaje.jsp"/>
 
	 <div class="row"> Usuarios conectados </div> <%
  for(Usuario usu: usuarios){ 
    
  %>

		<div class="row"> 
		
			<div class="col-xs-2"> <%= usu.getUserName()%></div> 
			<div class="col-xs-2"> <%=usu.getNickname()%> </div> 
			<div class="col-xs-2"> <%=usu.getSessionId()%> </div>
			<div class="col-xs-2"> <a class="btn btn-info" href="<%=Constantes.SERVLET_ADMINISTRACION %>?<%=Constantes.PAR_SESIONID %>=<%=usu.getSessionId()%>">Expulsar Usuario</a> </div>
		</div> <%
	}
}
 else{
	 %>
	<div class="col-xs-4"> No hay usuarios conectados </div> <%
 }
%>

</main>
<%@include file="../includes/footer.jsp" %>