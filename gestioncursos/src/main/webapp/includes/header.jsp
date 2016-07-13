<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<title>Gestion de Cursos </title>
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
		<h1 class="col-xs-8">Ipartek - Gestion de Cursos</h1>
	
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
		    <a class="navbar-brand" href="<%=Constantes.JSP_INDEX%>">Página Principal</a>
		  </div>
		  <div class="collapse navbar-collapse navbar-ex1-collapse">
    		<ul class="nav navbar-nav">
    		
    			<li class="dropdown">
					<a class="dropdown-toggle" href="<%=Constantes.SERVLET_CURSOS%>">Cursos</a>
					<ul>
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
					<a class="dropdown-toggle" href="<%=Constantes.SERVLET_ALUMNOS%>">Alumnos</a>
					<ul>
						<li>
							<a href="<%=Constantes.SERVLET_ALUMNOS%>"> Todos los alumnos </a>
						</li>
						<li>
							<a href="<%=Constantes.SERVLET_ALUMNOS%>?<%=Constantes.PAR_CODIGO%>=<%=Alumno.CODIGO_ALUMNO%>">Crear Alumno Nuevo</a>
						</li>
					</ul>
				</li>
				
				<li class="dropdown">
					<a class="dropdown-toggle" href="<%=Constantes.SERVLET_MODULOS%>">Modulos</a>
					<ul>
						<li>
							<a href="<%=Constantes.SERVLET_MODULOS%>">Ver Modulos</a>
						</li>
						<li>
							<a href="<%=Constantes.SERVLET_MODULOS%>?<%=Constantes.PAR_CODIGO%>=<%=Modulo.CODIGO_MODULO%>">Crear Modulo Nuevo</a>
						</li>
					</ul>
				</li>
					<%session = request.getSession();
					if(session != null && session.getAttribute(Constantes.ATT_USUARIO)!=null){%>
				<li>
					<a href="<%=Constantes.SERVLET_ADMIN %>">Admin</a>
				</li>
				<li>
					<a href="<%=Constantes.SERVLET_LOGOUT%>">Logout</a>
				</li>
				<%}%>
			</ul>
			</div>
		</nav>