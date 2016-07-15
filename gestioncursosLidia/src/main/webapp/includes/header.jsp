<%@page import="com.ipartek.formacion.service.i18n.I18n"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="en_EN" />
<c:set var="language" value="<%=I18n.getBrowserLocale(response.getLocale()) %>"/><!-- recoger de la sesión no del navegador -->
<c:set var="language" value="${sessionScope.usuario.idioma.locale}" scope="page"/><!-- va buscando la variable por pág,request,session y app(en este orden) scope,ámbito-->
<c:set var="localeCode" value="${response.locale}"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.ipartek.formacion.service.i18n.i18nmesages"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<title>Gestión de Cursos </title>
	<!-- BOOSTRAP BASE STYLES -->
	<link rel="stylesheet" href="css/bootstrap.min.css"/>
	<!-- FONTAWASONE -->
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
	<!-- MY THEME STYLES -->
	<link rel="stylesheet" href="css/styles.css"/>
	<!-- jQuery 1.13 -->
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	 <!--  BOOTSTRAP JS LIBS -->
	 <script src="js/bootstrap.min.js"></script>
	 <!--[if lt IE 9]>
	   <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	   <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	 <![endif]-->
</head>
<body class="container-fluid">
	<header class="row">
		<h1 class="col-xs-8">Ipartek - Gestión de Cursos</h1>
		<!--  <a class="btn col-xs-offset-2 col-xs-2 btn-info" href="#">
			<span class="fa fa-sign-out" aria-hidden="true"></span>
			Desconectar
		</a>-->
	</header>
	
	<nav class="navbar navbar-inverse" role="navigation">
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
		    <a class="navbar-brand" href="index.jsp">Página Principal</a>
		  </div>
		  <div class="collapse navbar-collapse navbar-ex1-collapse">
    		<ul class="nav navbar-nav">
    			<li class="dropdown">
					<a class="dropdown-toggle" href="<%=Constantes.SERVLET_CURSOS%>">
						Cursos
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="<%=Constantes.SERVLET_CURSOS%>">
							Ver Cursos
							</a>						
						</li>
						<li>
							<a  href="<%=Constantes.SERVLET_CURSOS%>?<%=Constantes.PAR_CODIGO%>=<%=Curso.CODIGO_CURSO%>">
							Crear Curso Nuevo
							</a>						
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="<%=Constantes.SERVLET_ALUMNOS%>">
						Alumnos
						<span class="caret"></span><!-- flecha invertida menú dropdown -->
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="<%=Constantes.SERVLET_ALUMNOS%>">
								Todos los alumnos
							</a>
						</li>
						<li>
							<a href="<%=Constantes.SERVLET_ALUMNOS%>?<%=Constantes.PAR_CODIGO%>=<%=Alumno.CODIGO_ALUMNO%>">Crear Alumno Nuevo</a></li>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="<%=Constantes.SERVLET_MODULOS%>">
						Modulos
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="<%=Constantes.SERVLET_MODULOS%>">
								Ver Modulos
							</a>
						</li>
						<li><a href="<%=Constantes.SERVLET_MODULOS%>?<%=Constantes.PAR_CODIGO%>=<%=Modulo.CODIGO_MODULO%>">Crear Modulo Nuevo</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="<%=Constantes.SERVLET_MODULOS%>">
						Admin
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="<%=Constantes.SERVLET_ADMINISTRACION%>">
								Ver Usuarios Conectados
							</a>
						</li>
						
					</ul>
				</li>
			</ul>
			<%
				Usuario user = (Usuario)session.getAttribute(Constantes.ATT_USUARIO);//si no estas logueado que no aparezca el botón de logout 
				if(user!= null){
			%>
			<ul class="nav navbar-nav navbar-right" align="right">
        		<li><a href="index.jsp"><i class="glyphicon glyphicon-off"><fmt:message key="header.desconectar"/></i></a></li>
        		
      		</ul>
      		<%
	       		}
			%>
			</div>
		</nav>