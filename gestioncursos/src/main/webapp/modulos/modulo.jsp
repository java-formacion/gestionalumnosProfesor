<%@page import="com.ipartek.formacion.pojo.Duracion"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
		<%
		Modulo modulo = (Modulo) request.getAttribute(Constantes.ATT_MODULO);
		int op = -1;
		if(modulo!=null){
			op = Constantes.OP_UPDATE;
		}else{
			modulo = new Modulo();
			op = Constantes.OP_CREATE;
		}
		%>
<main class="row">
		<a class="btn btn-warning" href="<%=Constantes.SERVLET_MODULOS %>">Atras</a>
		<%
		if(modulo!=null){
			%>
			
				<form name="" id="" method='post' 
					action="<%=Constantes.SERVLET_MODULOS%>">
					<input type="hidden" 
						id="<%=Constantes.PAR_OPERACION %>"
						name="<%=Constantes.PAR_OPERACION %>"  
						value="<%=op %>"/>
					<input type="hidden" 
						id="<%=Constantes.PAR_CODIGO %>" 
						name="<%=Constantes.PAR_CODIGO %>" 
						value="<%=modulo.getCodigo()%>"/>
					<div class="form-group">
						<label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
						<input type="text" 
							name="<%=Constantes.PAR_NOMBRE%>" 
							id="<%=Constantes.PAR_NOMBRE%>" 
							value="<%=modulo.getNombre() %>" />
					</div>
					<div class="form-group">				
					<label for="<%=Constantes.PAR_REFERENCIA%>">Referencia:</label>
					<input type="text" 
						name="<%=Constantes.PAR_REFERENCIA%>" 
						id="<%=Constantes.PAR_REFERENCIA%>" 
						value="<%=modulo.getRefModulo()%>"/>
					</div>
					<div class="form-group">
						<label>Duración Modulo</label>
						<select name="<%=Constantes.PAR_DURACION%>">
						<%
						Duracion[] duraciones = (Duracion[])request.getAttribute(Constantes.ATT_LISTA_DURACION_MODULO);
						if(duraciones!=null){
							for(Duracion duracion: duraciones){
								%>
							<option <%= duracion.equals(modulo.getDurModulo()) ? "selected" : ""%> value="<%=duracion.getCodigo()%>"><%=duracion.getValor() %></option>
								<%
							}
						}
						
						%>
						</select>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-success">
							Guardar
						</button>
					</div>
				</form>	
		<%	
		}
			%>
	</main>