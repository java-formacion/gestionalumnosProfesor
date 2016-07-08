<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/>
<main>
		
		Bienvenidos a la página de Gestión de Alumnos de Ipartek
		<% String tEnviar = "Enviar";%>
		<form action="<%=Constantes.SERVLET_LOGIN %>">
		<jsp:include page="includes/mensaje.jsp"/>
		<div class="form-group">
					
					
					<div class="row">
					<label class="col-xs-4"></label>
					<label class="col-xs-1" for="<%=Constantes.PAR_USER%>">Usuario:</label>
					<label class="col-xs-3">
					<input type="text" class="form-control"
						name="<%=Constantes.PAR_USER%>" 
						id="<%=Constantes.PAR_USER%>" 
						value=""
						/>
					
					</label>
					</div>
					<div class="row">
					
					<label class="col-xs-4"></label>
					<label class="col-xs-1" for="<%=Constantes.PAR_PASSWORD%>">Contraseña:</label>
					<label class="col-xs-3">					
					<input type="text"  class="form-control"
						name="<%=Constantes.PAR_PASSWORD%>" 
						id="<%=Constantes.PAR_PASSWORD%>" 
						value=""
						/>
						</label>
					
					</div>
					<div class ="row">
					<label class="col-xs-5">
						</label>
					<labelclass="col-xs-3"><button type="submit" class="btn btn-success col-xs-4">
							<%=tEnviar %>
						</button></label>
					
					
					
					</div>
					
					
				</div>
		
		
		</form>
		
					
		
	
</main>
<%@ include file="includes/footer.jsp" %>