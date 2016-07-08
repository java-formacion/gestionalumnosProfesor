<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/> 
<main>
<!-- 
warning -> Naranja
danger  -> Rojo
info    -> Azul claro
succes  -> verde
default -> blanco
primary -> Azul oscuro
 -->
		<a class="btn btn-warning" href="index.jsp">Atras</a>
		<a class="btn btn-success" href="<%=Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO+"="+Curso.CODIGO_CURSO%>">
			<span class="fa fa-plus fblack" aria-hidden="true"></span>
			Curso
		</a> 

		<%		
			List<Curso> cursos = (List<Curso>) request.getAttribute("listado_cursos");
			if(cursos!=null){
				int i=1;
				String formulario ="";
				for(Curso curso: cursos){
					formulario = "<form class='col-xs-2 col-md-6' action='"+Constantes.SERVLET_CURSOS
							+"' method='post'>";
					//la variable opercion
					formulario +="<input type='hidden' "+
							"name='"+Constantes.PAR_OPERACION+
							"' value='"+Constantes.OP_DELETE+"'/>";
					//la variable del codigo del curso
					formulario +="<input type='hidden' "+
							"name='"+Constantes.PAR_CODIGO+
							"' value='"+curso.getCodigo()+"'/>";
					//el boton de borrar
					formulario +="<button type='submit' class='btn btn-danger'>Borrar</button>";
					formulario +="</form>";
					%>
					<div class="row">
						<a class="col-xs-10 col-md-6" href='<%=Constantes.SERVLET_CURSOS %>
							?<%=Constantes.PAR_CODIGO%>
							=<%=curso.getCodigo()  %>'>
							<%=curso.getNombre() %>
						</a>
						<%=formulario %>						
					</div>
					<%
					i++;
				}
			}
	%>
</main>
<%@ include file="../includes/footer.jsp" %>