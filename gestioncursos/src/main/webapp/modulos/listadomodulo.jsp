<%@page import="com.ipartek.formacion.control.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
<main>
	<% 
	List<Modulo> modulos;
	String formulario;
	modulos = (List<Modulo>) request.getAttribute(Constantes.ATT_LISTADO_MODULOS);
	if(modulos!=null){
		int i=1;
	
		for (Modulo modulo: modulos)
		{
			//inicio el form del boton para borrar
			formulario ="<form action='"+Constantes.SERVLET_MODULOS+"' method='post'>";
			//Aqui variables para borrar 
			formulario += "<input type='hidden' name='"+Constantes.PAR_OPERACION+"' value='"+Constantes.OP_DELETE+"'/>";
			//Aqui codigo para borrar
			formulario += "<input type='hidden' name='"+Constantes.PAR_CODIGO+"' value='"+modulo.getCodigo()+"'/>";
			//Aqui el boton
			formulario += "<button type='submit' class='btn btn-danger'><span class='fa fa-trash-o' aria-hidden='true'></span> Borrar</button>";
			//El cierre del form
			formulario +="</form>";
			%>
			<div class="row">
				<a class="col-xs-10 btn btn-success" href="<%=Constantes.SERVLET_MODULOS+"?"+Constantes.PAR_CODIGO+"="+modulo.getCodigo()%>">"<%=modulo.getNombre() %>" </a><%=formulario %>
			</div>
			<%i++;
		}
		
	}%> 
	
	<%-- Scriptlets --%>

	<a class="btn btn-success" href="<%=Constantes.SERVLET_MODULOS+"?"+Constantes.PAR_CODIGO+"="+Modulo.CODIGO_MODULO%>"><span class="fa fa-plus fblack" aria-hidden="true"></span> Modulo</a>
	<a class="btn btn-info" href="index.jsp">Página principal</a>
</main>
<%@ include file="../includes/footer.jsp" %><!-- Aqui no se compila previamente, no tiene codigo java -->