<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main class=row>

	<a class="btn btn-info" href="index.jsp"><span class="glyphicon glyphicon-arrow-left">Atrás</span></a>
	<a class="btn btn-success" href="<%=Constantes.SERVLET_MODULOS+"?"+Constantes.PAR_CODIGO+"="+Modulo.CODIGO_MODULO%>">
	<span class="glyphicon glyphicon-plus">Nuevo modulo</span>
	</a>
	
	<%
	
	List<Modulo> modulos = (List<Modulo>)request.getAttribute(Constantes.ATT_LISTADO_MODULOS); 
	
	if(modulos!=null){ //si la lista no es nula
		String formulario ="";
		for(Modulo modulo: modulos){
			formulario = "<form action='"+Constantes.SERVLET_MODULOS+"' method='post'>";
			//variable operacion
			formulario +="<input type='hidden' "+
			"name='"+Constantes.PAR_OPERACION+
			"'value='"+Constantes.OP_DELETE+"'/>";
			//variable del codigo de modulo
			formulario +="<input type='hidden' "+
			"name='"+Constantes.PAR_CODIGO+
			"'value='"+modulo.getCodigo()+"'/>";
			//el botón borrar
			formulario +="<button type='submit' class='btn btn-danger'>Borrar</button>";
			formulario +="</form>";
			
		%>
			
			<div class="row">
						<a class="col-xs-10 col-md-2" href='<%=Constantes.SERVLET_MODULOS %>
							?<%=Constantes.PAR_CODIGO%>
							=<%=modulo.getCodigo()  %>'>
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