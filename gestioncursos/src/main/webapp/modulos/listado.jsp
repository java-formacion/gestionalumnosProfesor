<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/> 
<main>
	<a class="btn btn-warning" href="index.jsp">Atras</a>
	<a class="btn btn-success" href="<%=Constantes.SERVLET_MODULOS%>?<%=Constantes.PAR_CODIGO%>=<%=Modulo.CODIGO_MODULO%>">Añadir Modulo Nuevo</a> 
		<%		
			List<Modulo> modulos = (List<Modulo>) request.getAttribute(Constantes.ATT_LISTADO_MODULOS);
			if(modulos!=null){
				int i=1;
				String formulario ="";
				for(Modulo modulo: modulos){
					formulario = "<form action='"+Constantes.SERVLET_MODULOS
							+"' method='post'>";
					//la variable opercion
					formulario +="<input type='hidden' "+
							"name='"+Constantes.PAR_OPERACION+
							"' value='"+Constantes.OP_DELETE+"'/>";
					//la variable del codigo del curso
					formulario +="<input type='hidden' "+
							"name='"+Constantes.PAR_CODIGO+
							"' value='"+modulo.getCodigo()+"'/>";
					//el boton de borrar
formulario +="<button type='submit' class='btn btn-danger'>Borrar</button>";
					formulario +="</form>";
					%>
				<div class="row">
					<a class="col-xs-10 col-md-6" href='<%=Constantes.SERVLET_MODULOS %>
						?<%=Constantes.PAR_CODIGO%>=<%=modulo.getCodigo() %>'>
						<%=modulo.getNombre() %>
					</a>
					<%=formulario %>						
				</div>
				<%
				}
			}
		%>
</main>
<%@ include file="../includes/footer.jsp" %>