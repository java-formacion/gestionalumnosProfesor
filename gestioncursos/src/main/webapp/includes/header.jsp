<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.ipartek.formacion.service.i18n.I18n"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="language" value="en_EN"/>
<c:set var="language" value="<%=I18n.getBrowserLocale(response.getLocale()) %>"/>

<c:set var="language" value="${sessionScope.usuario.idioma.locale}" scope="page"/>

<c:set var="localeCode" value="${response.locale}"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.ipartek.formacion.service.i18n.i18nmesages"/>

<!DOCTYPE html>
<html lang="${language}">
	<head>
		<meta charset="UTF-8">
		<title>Ipartek - Gestion de Cursos</title>
		
		<!-- BOOTSTRAP BASE STYLES -->		
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		
		<!--  BOOTSTRAP JS LIBS -->
	 	<script src="js/bootstrap.min.js"></script>
		
		<!-- FONTAWESOME -->	
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		
		<!-- OWN STYLES -->	
		<link rel="stylesheet" href="css/style.css" />
		
		<!-- JQUERY LIBRARY 1.11.3  -->
	 	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	 	<script src="js/jquery.min.js"></script>

		<!--[if lt IE 9]>
		   	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	
	<body class="container-fluid">
		<header class="row"><h1 class="col-xs-12">Ipartek - Gestion de Cursos</h1></header>
			
			<!-- <nav class="navbar navbar-inverse" role="navigation"> -->
			<nav class="navbar navbar-default" role="navigation">
				<!-- El logotipo y el icono que despliega el menú se agrupan
				para mostrarlos mejor en los dispositivos móviles -->
				
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
					        data-target=".navbar-ex1-collapse">
						<span class="sr-only">Desplegar navegación</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<%= Constantes.JSP_INDEX %>"><fmt:message key="header.inicio"/></a>
				</div>
			
				<div class="collapse navbar-collapse navbar-ex1-collapse">
			   		<ul class="nav navbar-nav">					
						<%
							session = request.getSession();
							if(session != null && session.getAttribute(Constantes.ATT_USUARIO)!=null){
							%>
								<li class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" href="<%= Constantes.SERVLET_CURSOS %>">
										<fmt:message key="header.cursos"/> <b class="caret"></b>
									</a>
									<ul class="dropdown-menu">
										<li>
											<a href="<%= Constantes.SERVLET_CURSOS %>">
												<fmt:message key="header.vercursos"/>
											</a>
										</li>
									
										<li>
											<a href="<%= Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO + "=" + Curso.CODIGO_CURSO %>">
												<span class="fa fa-plus fblack" aria-hidden="true"></span>
												<fmt:message key="header.curso"/>
											</a>
										</li>
									</ul>
								</li>
								
								<li>
									<a href="<%= Constantes.SERVLET_ALUMNOS %>"><fmt:message key="header.alumnos"/></a>
								</li>
								
								<li>
									<a href="<%= Constantes.SERVLET_MODULOS %>"><fmt:message key="header.modulos"/></a>
								</li>
								
								<li class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" href="">
										<fmt:message key="header.administracion"/> <b class="caret"></b>
									</a>
									<ul class="dropdown-menu">
										<li>
											<a href="<%= Constantes.SERVLET_USUARIOS %>"><fmt:message key="header.verusuarios"/></a>
										</li>
									</ul>
								</li>
							
								<li>
									<%
										String username = "";
						        		try{
						        			Usuario usuario = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
						        			username = usuario.getUserName();
						        		} catch(Exception e){
						        			username = "Invitado";
						        		}
									%>
									<a href="<%= Constantes.SERVLET_LOGOUT %>"><fmt:message key="header.desconectar"/> (<%= username %>)</a>
								</li>
							<%
							} 
						%>
					</ul>
				</div>
			</nav>