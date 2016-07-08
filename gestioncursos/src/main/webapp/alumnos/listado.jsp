<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
		<a href="index.jsp">Atras</a>
		
		<a href="<%=Constantes.SERVLET_ALUMNOS+"?"+Constantes.PAR_CODIGO+"="+Alumno.CODIGO_ALUMNO %>">Añadir alumno nuevo</a>
		<%
		List <Alumno> alumnos= (List<Alumno>) request.getAttribute("listado_alumnos");
		if(alumnos !=null){
			int i=1;
			for(Alumno alumno : alumnos){
				out.write("<p><a href='"+Constantes.SERVLET_ALUMNOS+"?"+Constantes.PAR_CODIGO+"="+alumno.getCodigo()+"'>Curso "+i+":"
					+ alumno.getNombre()+"</a></p>");
					i++;
			}
		}
		
		
		%>


<%@ include file="../includes/footer.jsp" %>