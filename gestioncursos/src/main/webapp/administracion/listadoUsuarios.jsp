<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<jsp:include page="../includes/header.jsp" />
	
<!--  BOOTSTRAP JS LIBS -->
<script src="js/bootstrap.min.js"></script>
		
<!-- JQUERY LIBRARY 1.11.3  -->
<script src="js/jquery.min.js"></script>	
	
<main>
		<a href="index.jsp" class="btn btn-warning">
			<span class="fa fa-arrow-left" aria-hidden="true"></span>
			Atras
		</a>
		
		<br/>

<section>
	<header><h2>Listado de Usuarios Conectados</h2></header>
	<div>		
		
		<jsp:include page="../includes/mensaje.jsp" />
		
		<%			
			ServletContext context = session.getServletContext();
			List<Usuario> usuarios = (List<Usuario>)context.getAttribute(Constantes.ATT_LIST_USUARIOS);
			
			if(usuarios!=null){
				%>
				<form action="<%= Constantes.SERVLET_USUARIOS %>" method="post">
				<table class="table table-hover table-bordered">
					<tr>
						<td>Session Id</td>
						<td>UserName</td>
						<td>NickName</td>
						<td>Language</td>
						<td></td>
					</tr>
				<%
				
				for(Usuario user: usuarios){
					
					%>
					<tr>
						<td>
							<%=user.getSessionid() %>
						</td>
						
						<td>
							<%=user.getUserName() %>
						</td>
						
						<td>
							<%=user.getNickname() %>
						</td>
						
						<td>
							
						</td>
						
						<td>
							<a class="btn btn-info" href="<%=Constantes.SERVLET_USUARIOS %>?<%= Constantes.PAR_SESSIONID %>=<%= user.getSessionid()%>">Expulsar Usuario</a>
						</td>
					</tr>
					
					<%
				}
				
				%>
				</table>
				</form>
				<%
			}else{
				%>
				<p >No hay usuarios conectados</p>
				<%
			}
		%>
	</div>
</section>
		
<%@ include file="/includes/footer.jsp" %>