<%@page import="com.ipartek.formacion.controller.Constantes"%>

<!DOCTYPE html >
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset = "UTF-8">
		<link rel="stylesheet" href="./css/bootstrap.min.css" >
		<link rel="stylesheet" href="./css/font-awesome.css" >
		<link rel="stylesheet" href="./css/styles.css" >
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    	<!-- BOOTSTRAP JS -->
    	<script src="./js/bootstrap.min.js"></script>
			<title>Página de inicio</title>
			
	<!--[if lt IE 9]>
	   <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	   <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	</head>
	<body class= "container-fluid">
	<header class="row"><h1 class = "col-xs-12">Gestion de cursos Ipartek</h1></header>
	
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
		    <a class="navbar-brand" href="index.jsp">Inicio</a>
		  </div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
    			<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="<%=Constantes.SERVLET_CURSOS%>"> Cursos</a>
						<ul class="dropdown-menu">
	          				<li><a href="<%=Constantes.SERVLET_CURSOS%>">Ver todos los cursos.</a></li>
	          			</ul>
	          		</li>
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="<%=Constantes.SERVLET_ALUMNOS%>">Alumnos</a>
						<ul class="dropdown-menu">
	          				<li><a href="<%=Constantes.SERVLET_ALUMNOS%>">Ver todos los Alumnos</a></li>
	          			</ul>
					</li>
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="<%=Constantes.SERVLET_MODULOS%>"> Modulos</a>
						<ul class="dropdown-menu">
	          				<li><a href="<%=Constantes.SERVLET_MODULOS%>"> Ver todos los Modulos</a></li>
	          			</ul>
					</li>
				</ul>
				
			</div>
	</nav>