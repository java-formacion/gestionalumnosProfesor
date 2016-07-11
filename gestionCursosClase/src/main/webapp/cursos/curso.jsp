<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
	<%
		Curso curso = (Curso) request.getAttribute(Constantes.ATT_CURSO);
		int op = -1;
		if(curso!=null)
		{
			op = Constantes.OP_UPDATE;		
 	%>
 	<title>Curso <% out.write(curso.getNombre()); %></title>
 	<%
		}
		else
		{
			%>
				<title>Curso  - Curso nuevo</title>
			<%
				op = Constantes.OP_CREATE;
				curso = new Curso();
				
		//out.write("<p>Curso ID:" + curso.getCodigo() + "</p> <p> Curso Nombre: " + curso.getNombre() + "</p>");
		}
		%>
	<main>
		<a class = "btn btn-warning" href="<%=Constantes.SERVLET_CURSOS %>">Atras</a>
		<%
		if(curso!=null){
		%>
		<%=Constantes.SERVLET_CURSOS%>
			<form class="form-inline" name="formcurso" id="formcurso" method='post' 
				action="<%=Constantes.SERVLET_CURSOS%>">
				<input type="hidden" 
					id="<%=Constantes.PAR_OPERACION %>"
					name="<%=Constantes.PAR_OPERACION %>"  
					value="<%=op %>"/>
				<input type="hidden" 
					id="<%=Constantes.PAR_CODIGO %>" 
					name="<%=Constantes.PAR_CODIGO %>" 
					value="<%=curso.getCodigo()%>"/>
				<div class="form-group">
				<label class="sr-only" for="<%=Constantes.PAR_NOMBRE%>"></label>
				<input class="form-control" type="text" 
					name="<%=Constantes.PAR_NOMBRE%>" 
					id="<%=Constantes.PAR_NOMBRE%>" 
					value="<%=curso.getNombre() %>"
					/>
				</div>
				<input class = "btn btn-success" type="submit" />
			</form>			
	<%	}
		%>
		</main>

<%@ include file="../includes/footer.jsp"%>