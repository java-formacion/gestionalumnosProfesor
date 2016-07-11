<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<title>Gestión de Cursos</title>
	<!-- BOOTSTRAP BASE STYLES-->
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<!-- FONT-AWESOME -->
	<link rel="stylesheet" href="css/font-awesome.min.css" />
	<!-- MY THEME STYLES -->
	<link rel="stylesheet" href="css/styles.css" />
	<!--JQUERY 1.13-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	 <!--[if lt IE 9]>
	   <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	   <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	 <![endif]-->
</head>
<body class="container-fluid"> 
	<header class="row"> 
		<h1 class="col-xs-12">IPARTEK - GESTION DE CURSOS</h1> 
		<!--  <nav>
			<ul class="row">
				<li class="btn btn-default col-xs-4 col-md-2" ><a href="<%=Constantes.SERVLET_CURSOS%>">ver todos los cursos</a></li>
				<li class="btn btn-default col-xs-4 col-md-2"><a href="<%=Constantes.SERVLET_ALUMNOS %>">ver todos los alumnos</a></li>
				<li class="btn btn-default col-xs-4 col-md-2"><a href="<%=Constantes.SERVLET_MODULOS %>">ver todos los modulos</a></li>
			</ul>
		</nav>
		-->
		<nav class="navbar navbar-inverse" role="navigation">
  			<div class="navbar-header">
   				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			      <span class="sr-only">Desplegar navegación</span>
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
		    	</button>
   			 	<a class="navbar-brand" href="#">Ipartek</a>
  			</div>
  			
		<div class="collapse navbar-collapse navbar-ex1-collapse">
   	  		<ul class="nav navbar-nav">
			    <li class="dropdown">
        			<a href="<%=Constantes.SERVLET_ALUMNOS %>" class="dropdown-toggle" data-toggle="dropdown">ALUMNOS<b class="caret"></b></a>
        			<ul class="dropdown-menu">
        				<li >
        					<a href="<%=Constantes.SERVLET_ALUMNOS %>" >ver alumnos</a>
        				</li>
        			</ul>
        		</li>
        		<li class="dropdown">
        			<a href="<%=Constantes.SERVLET_CURSOS %>" class="dropdown-toggle" data-toggle="dropdown">CURSOS<b class="caret"></b>
        			</a>
        			<ul class="dropdown-menu">
        				<li >
        					<a href="<%=Constantes.SERVLET_CURSOS %>" >ver cursos</a>
        				</li>
        			</ul>
        		</li>
        		<li class="dropdown">
        			<a href="<%=Constantes.SERVLET_MODULOS %>" class="dropdown-toggle" data-toggle="dropdown">MODULOS<b class="caret"></b>
        			</a>
        			<ul class="dropdown-menu">
        				<li >
        					<a href="<%=Constantes.SERVLET_MODULOS %>" >ver modulos</a>
        				</li>
        			</ul>
        		</li>
    		</ul>
    	<%
	if(session.getAttribute("usuario")!=null){
	%>	
	 	<div class="row">
				<div class="pull-right">
					<button type="submit" class="btn btn-success">Salir</button>
				</div>
	 	</div>
	<% 
	}
	%> 	
    	</div>
    	</nav>	
	</header>