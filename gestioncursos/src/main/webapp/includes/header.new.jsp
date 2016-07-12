<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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
		<!--  NUEVO HEADER -->
		
		<div class="navbar navbar-fixed-top navbar-default">
	  	<div class="container">
	  		
	  		<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
				        data-target=".navbar-collapse">
					<span class="sr-only">Desplegar navegación</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%= Constantes.JSP_INDEX %>">Inicio</a>
			</div>
	  		
	        <div class="navbar-collapse">
				<ul class="nav navbar-nav">					
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="<%= Constantes.SERVLET_CURSOS %>">
							Cursos <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%= Constantes.SERVLET_CURSOS %>">
									Ver todos los cursos
								</a>
							</li>
						
							<li>
								<a href="<%= Constantes.SERVLET_CURSOS+"?"+Constantes.PAR_CODIGO + "=" + Curso.CODIGO_CURSO %>">
									<span class="fa fa-plus fblack" aria-hidden="true"></span>
									Curso
								</a>
							</li>
						</ul>
					</li>
					
					<li>
						<a href="<%= Constantes.SERVLET_ALUMNOS %>">Alumnos</a>
					</li>
					
					<li>
						<a href="<%= Constantes.SERVLET_MODULOS %>">Modulos</a>
					</li>
				</ul>
	        </div>
	        <!--/.navbar-collapse -->
	    </div>
	</div>