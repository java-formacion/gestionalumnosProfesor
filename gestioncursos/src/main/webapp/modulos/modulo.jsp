<%@page import="com.ipartek.formacion.pojo.Horas"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main>
<%
	Modulo modulo = (Modulo)request.getAttribute(Constantes.ATT_MODULO);
	int op = -1;
	if(modulo!=null){
		op = Constantes.OP_UPDATE;
	%>	
	<title>Modulo <% out.write(modulo.getNombre()); %></title>
	<% 
	}else{
		%>
		<title>Modulo - Modulo nuevo</title>
		<%
		op = Constantes.OP_CREATE;
		modulo = new Modulo();
		}
		%>

	<a href="<%=Constantes.SERVLET_MODULOS%>">Listado de modulos</a>
	<div id="wrapper">
	
	
	<%
	
	if(modulo!=null){
	%>
		<form class="form-horizontal"  name="" method="post" action="<%=Constantes.SERVLET_MODULOS%>">
			<input type="hidden" 
				id="<%=Constantes.PAR_OPERACION%>" 
				name="<%=Constantes.PAR_OPERACION%>" 
				value="<%=op%>"/>
			<input type="hidden" 
				id="<%=Constantes.PAR_CODIGO%>" 
				name="<%=Constantes.PAR_CODIGO%>"
				value="<%=modulo.getCodigo()%>"/>
			<div class="form-group">
			<div  class= col-xs-1>	
				<label for="<%=Constantes.PAR_NOMBRE%>">NOMBRE:</label>
				</div>
				<div class= col-xs-2>
				<input type="text" class="form-control" 
					id="<%=Constantes.PAR_NOMBRE%>" 
					name="<%=Constantes.PAR_NOMBRE%>" 
					value="<%=modulo.getNombre()%>"/>
					</div>
			</div>
			<div class="form-group">
			<div  class= col-xs-1>	
				<label for="<%=Constantes.PAR_REFERENCIA%>">REFERENCIA:</label>
				</div>
				<div class= col-xs-2>
				<input type="text" class="form-control" 
					id="<%=Constantes.PAR_REFERENCIA%>" 
					name="<%=Constantes.PAR_REFERENCIA%>" 
					value="<%=modulo.getReferencia()%>"/>
					</div>
			
			</div>
			
			<div class="form-group">
				<div class= col-xs-1>
			    <label>Duración:</label>
			    </div>
			   <div class= col-xs-2>
			    <select class="form-control" name="<%=Constantes.PAR_HORAS %>">
			     		<%
			     		for(Horas hora: Horas.values()){
			     		%>
			     		<option <%= modulo.getHoras() == hora ? "selected" : "" %>
			     		value="<%=hora.getCodigo() %>"> <%=hora.getDuracion() + " " + Constantes.PAR_HORAS %></option>
			     		<% 	
			     		}
	
			     	%>
			    </select>	
			    </div>
			   
			</div>  
			
		    <button type="submit" class="btn btn-success">Guardar</button>
		</form>
	<% }
		%>
	</div>
</main>
<%@ include file="../includes/footer.jsp" %>