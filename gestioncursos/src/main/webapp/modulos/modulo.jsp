<%@page import="com.ipartek.formacion.pojo.DuracionModulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp" />  

<!--  BOOTSTRAP JS LIBS -->
<script src="js/bootstrap.min.js"></script>
		
<!-- JQUERY LIBRARY 1.11.3  -->
<script src="js/jquery.min.js"></script>

<%
	Modulo modulo = (Modulo) request.getAttribute(Constantes.ATT_MODULO);

	int op = -1;
	String tGuardar ="";
	
	if(modulo!=null){
		op = Constantes.OP_UPDATE;
		tGuardar = "Actualizar";
	}else{
		modulo = new Modulo();
		op = Constantes.OP_CREATE;
		tGuardar = "Crear";
	}
%>		

<div id="wrapper">
	<a href="<%= Constantes.SERVLET_MODULOS %>" class="btn btn-warning">
		<span class="fa fa-arrow-left" aria-hidden="true"></span>
		Atras
	</a>
	<br/>
	
	<%
	
	if(modulo!=null){
		//out.write(modulo.getCodigo() + " - " + modulo.getNombre());
		
	%>
		<form class="form-horizontal col-xs-5" method="post" action="<%= Constantes.SERVLET_MODULOS %>">
			<input type="hidden"
				name="<%= Constantes.PAR_OPERACION %>"
				id="<%= Constantes.PAR_OPERACION %>"
				value="<%= op %>" />
				
			<input type="hidden"
				name="<%= Constantes.PAR_CODIGO %>"
				id="<%= Constantes.PAR_CODIGO %>"
				value="<%= modulo.getCodigo() %>" />
			
			<div class="form-group">
				<label for="<%= Constantes.PAR_NOMBRE %>">Modulo: </label>
				
				<input type="text" 
					name="<%= Constantes.PAR_NOMBRE %>" 
					id="<%= Constantes.PAR_NOMBRE %>"
					value="<%= modulo.getNombre() %>"
					size="80"
					class="form-control"
					placeholder="Nombre del modulo"
					 />
			</div>
			
			<div class="form-group">
				<label for="<%= Constantes.PAR_NOMBRE %>">Referencia: </label>
				
				<input type="text" 
					name="<%= Constantes.PAR_REFERENCIA %>" 
					id="<%= Constantes.PAR_REFERENCIA %>"
					value="<%= modulo.getReferencia() %>"
					size="10"
					class="form-control"
					placeholder="Referencia del modulo"
					 />
			</div>
			
			<div class="form-group form-inline">
					<label>Duración curso: </label>
					<select name="<%=Constantes.PAR_DURACION%>">
					<%
					DuracionModulo[] duraciones = (DuracionModulo[])request.getAttribute(Constantes.ATT_LISTA_DURACION_MODULO);
					if(duraciones!=null){
						for(DuracionModulo duracion: duraciones){
							%>
								<option 
									<%= duracion.equals(modulo.getDuracion()) ? "selected" : ""%> 
									value="<%=duracion.getCodigo()%>"
									>
									<%=duracion.getValor() %>
								</option>
							<%
						}
					}
					
					%>
					</select>
				</div>
			
			<div class="form-group">
				<input type="submit" value="<%= tGuardar %>" class="btn btn-success" />
			</div>
		</form>
	<%
	}
	%>
	
</div>

<%@ include file="/includes/footer.jsp" %>